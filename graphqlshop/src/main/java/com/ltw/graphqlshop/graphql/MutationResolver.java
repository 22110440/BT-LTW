package com.ltw.graphqlshop.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import com.ltw.graphqlshop.repository.*;
import com.ltw.graphqlshop.entity.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class MutationResolver {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final UserRepository userRepo;

    public MutationResolver(ProductRepository productRepo, CategoryRepository categoryRepo, UserRepository userRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    // ==================== AUTHENTICATION ====================
    @MutationMapping
    public LoginResponse login(@Argument LoginInput input) {
        User user = userRepo.findByEmail(input.email()).orElse(null);

        if (user == null) {
            return new LoginResponse(false, "Email không tồn tại!", null);
        }

        if (!user.getPassword().equals(input.password())) {
            return new LoginResponse(false, "Mật khẩu không đúng!", null);
        }

        return new LoginResponse(true, "Đăng nhập thành công!", user);
    }

    // ==================== USER ====================
    @MutationMapping
    public User createUser(@Argument CreateUserInput input) {
        User u = new User();
        u.setFullname(input.fullname());
        u.setEmail(input.email());
        u.setPassword(input.password());
        u.setPhone(input.phone());
        u.setRole(input.role() != null ? input.role() : "USER");
        u.setCategories(new HashSet<>());

        if (input.categoryIds() != null) {
            u.getCategories().addAll(findCategories(input.categoryIds()));
        }
        return userRepo.save(u);
    }

    @MutationMapping
    public User updateUser(@Argument UpdateUserInput input) {
        User u = userRepo.findById(input.id()).orElseThrow();
        if (input.fullname() != null) u.setFullname(input.fullname());
        if (input.email() != null) u.setEmail(input.email());
        if (input.password() != null) u.setPassword(input.password());
        if (input.phone() != null) u.setPhone(input.phone());
        if (input.role() != null) u.setRole(input.role());

        if (input.categoryIds() != null) {
            u.getCategories().clear();
            u.getCategories().addAll(findCategories(input.categoryIds()));
        }
        return userRepo.save(u);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        if (!userRepo.existsById(id)) return false;
        userRepo.deleteById(id);
        return true;
    }

    // ==================== CATEGORY ====================
    @MutationMapping
    public Category createCategory(@Argument CreateCategoryInput input) {
        Category c = new Category();
        c.setName(input.name());
        c.setImages(input.images());
        c.setUsers(new HashSet<>());
        c.setProducts(new HashSet<>());
        return categoryRepo.save(c);
    }

    @MutationMapping
    public Category updateCategory(@Argument UpdateCategoryInput input) {
        Category c = categoryRepo.findById(input.id()).orElseThrow();
        if (input.name() != null) c.setName(input.name());
        if (input.images() != null) c.setImages(input.images());
        return categoryRepo.save(c);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        if (!categoryRepo.existsById(id)) return false;
        categoryRepo.deleteById(id);
        return true;
    }

    // ==================== PRODUCT ====================
    @MutationMapping
    public Product createProduct(@Argument CreateProductInput input) {
        Product p = new Product();
        p.setTitle(input.title());
        p.setQuantity(input.quantity());
        p.setDesc(input.desc());
        p.setPrice(input.price());
        p.setCategories(new HashSet<>());
