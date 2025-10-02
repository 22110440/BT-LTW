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

    // -------- Authentication --------
    @MutationMapping
    public LoginResponse login(@Argument LoginInput input) {
        try {
            User user = userRepo.findByEmail(input.email()).orElse(null);
            if (user == null) {
                return new LoginResponse(false, "Email không tồn tại!", null);
            }
            
            if (!user.getPassword().equals(input.password())) {
                return new LoginResponse(false, "Mật khẩu không đúng!", null);
            }
            
            return new LoginResponse(true, "Đăng nhập thành công!", user);
        } catch (Exception e) {
            return new LoginResponse(false, "Lỗi hệ thống: " + e.getMessage(), null);
        }
    }

    // -------- User --------
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
        if (input.fullname()!=null) u.setFullname(input.fullname());
        if (input.email()!=null) u.setEmail(input.email());
        if (input.password()!=null) u.setPassword(input.password());
        if (input.phone()!=null) u.setPhone(input.phone());
        if (input.role()!=null) u.setRole(input.role());
        if (input.categoryIds()!=null) {
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

    // -------- Category --------
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
        if (input.name()!=null) c.setName(input.name());
        if (input.images()!=null) c.setImages(input.images());
        return categoryRepo.save(c);
    }

    @MutationMapping
    public Boolean deleteCategory(@Argument Long id) {
        if (!categoryRepo.existsById(id)) return false;
        categoryRepo.deleteById(id);
        return true;
    }

    // -------- Product --------
    @MutationMapping
    public Product createProduct(@Argument CreateProductInput input) {
        Product p = new Product();
        p.setTitle(input.title());
        p.setQuantity(input.quantity());
        p.setDesc(input.desc());
        p.setPrice(input.price());
        p.setCategories(new HashSet<>());
        if (input.ownerId()!=null) {
            p.setOwner(userRepo.findById(input.ownerId()).orElse(null));
        }
        if (input.categoryIds()!=null) {
            p.getCategories().addAll(findCategories(input.categoryIds()));
        }
        return productRepo.save(p);
    }

    @MutationMapping
    public Product updateProduct(@Argument UpdateProductInput input) {
        Product p = productRepo.findById(input.id()).orElseThrow();
        if (input.title()!=null) p.setTitle(input.title());
        if (input.quantity()!=null) p.setQuantity(input.quantity());
        if (input.desc()!=null) p.setDesc(input.desc());
        if (input.price()!=null) p.setPrice(input.price());
        if (input.ownerId()!=null) p.setOwner(userRepo.findById(input.ownerId()).orElse(null));
        if (input.categoryIds()!=null) {
            p.getCategories().clear();
            p.getCategories().addAll(findCategories(input.categoryIds()));
        }
        return productRepo.save(p);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument Long id) {
        if (!productRepo.existsById(id)) return false;
        productRepo.deleteById(id);
        return true;
    }

    // ---- helpers ----
    private Set<Category> findCategories(Iterable<Long> ids) {
        Set<Category> set = new HashSet<>();
        for (Long id : ids) {
            categoryRepo.findById(id).ifPresent(set::add);
        }
        return set;
    }

    // -------- Record inputs (Java 17+) --------
    public record LoginInput(String email, String password) {}
    public record LoginResponse(Boolean success, String message, User user) {}
    public record CreateUserInput(String fullname, String email, String password, String phone, String role, Iterable<Long> categoryIds) {}
    public record UpdateUserInput(Long id, String fullname, String email, String password, String phone, String role, Iterable<Long> categoryIds) {}
    public record CreateCategoryInput(String name, String images) {}
    public record UpdateCategoryInput(Long id, String name, String images) {}
    public record CreateProductInput(String title, Integer quantity, String desc, java.math.BigDecimal price, Long ownerId, Iterable<Long> categoryIds) {}
    public record UpdateProductInput(Long id, String title, Integer quantity, String desc, java.math.BigDecimal price, Long ownerId, Iterable<Long> categoryIds) {}
}
