package com.ltw.graphqlshop.graphql;

import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.ltw.graphqlshop.entity.*;
import com.ltw.graphqlshop.repository.*;


import java.util.List;

@Controller
public class QueryResolver {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final UserRepository userRepo;

    public QueryResolver(ProductRepository productRepo, CategoryRepository categoryRepo, UserRepository userRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.userRepo = userRepo;
    }

    @QueryMapping
    public List<Product> productsByPriceAsc() {
        return productRepo.findAllOrderByPriceAsc();
    }

    @QueryMapping
    public List<Product> productsByCategory(@Argument Long categoryId) {
        return productRepo.findDistinctByCategories_Id(categoryId);
    }

    @QueryMapping public List<Product> products() { return productRepo.findAll(); }
    @QueryMapping public List<Category> categories() { return categoryRepo.findAll(); }
    @QueryMapping public List<User> users() { return userRepo.findAll(); }

    @QueryMapping public Product product(@Argument Long id) { return productRepo.findById(id).orElse(null); }
    @QueryMapping public Category category(@Argument Long id) { return categoryRepo.findById(id).orElse(null); }
    @QueryMapping public User user(@Argument Long id) { return userRepo.findById(id).orElse(null); }
}
