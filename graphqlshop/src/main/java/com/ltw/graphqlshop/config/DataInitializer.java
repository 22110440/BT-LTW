package com.ltw.graphqlshop.config;

import com.ltw.graphqlshop.entity.Category;
import com.ltw.graphqlshop.entity.Product;
import com.ltw.graphqlshop.entity.User;
import com.ltw.graphqlshop.repository.CategoryRepository;
import com.ltw.graphqlshop.repository.ProductRepository;
import com.ltw.graphqlshop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DataInitializer(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only initialize if database is empty
        if (userRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        // Create Categories
        Category electronics = new Category();
        electronics.setName("Electronics");
        electronics.setImages("https://via.placeholder.com/150/0066CC/FFFFFF?text=Electronics");
        electronics.setUsers(new HashSet<>());
        electronics.setProducts(new HashSet<>());
        
        Category clothing = new Category();
        clothing.setName("Clothing");
        clothing.setImages("https://via.placeholder.com/150/FF6600/FFFFFF?text=Clothing");
        clothing.setUsers(new HashSet<>());
        clothing.setProducts(new HashSet<>());
        
        Category books = new Category();
        books.setName("Books");
        books.setImages("https://via.placeholder.com/150/009900/FFFFFF?text=Books");
        books.setUsers(new HashSet<>());
        books.setProducts(new HashSet<>());

        categoryRepository.save(electronics);
        categoryRepository.save(clothing);
        categoryRepository.save(books);

        // Create Users
        User admin = new User();
        admin.setFullname("Admin User");
        admin.setEmail("admin@graphqlshop.com");
        admin.setPassword("admin123");
        admin.setPhone("0123456789");
        admin.setCategories(Set.of(electronics, clothing, books));

        User seller1 = new User();
        seller1.setFullname("John Seller");
        seller1.setEmail("john@graphqlshop.com");
        seller1.setPassword("john123");
        seller1.setPhone("0987654321");
        seller1.setCategories(Set.of(electronics));

        User seller2 = new User();
        seller2.setFullname("Jane Smith");
        seller2.setEmail("jane@graphqlshop.com");
        seller2.setPassword("jane123");
        seller2.setPhone("0555666777");
        seller2.setCategories(Set.of(clothing, books));

        userRepository.save(admin);
        userRepository.save(seller1);
        userRepository.save(seller2);

        // Create Products
        Product laptop = new Product();
        laptop.setTitle("Gaming Laptop");
        laptop.setDesc("High-performance gaming laptop with RTX graphics");
        laptop.setPrice(new BigDecimal("1299.99"));
        laptop.setQuantity(5);
        laptop.setOwner(seller1);
        laptop.setCategories(Set.of(electronics));

        Product smartphone = new Product();
        smartphone.setTitle("Smartphone Pro");
        smartphone.setDesc("Latest smartphone with advanced camera system");
        smartphone.setPrice(new BigDecimal("899.99"));
        smartphone.setQuantity(10);
        smartphone.setOwner(seller1);
        smartphone.setCategories(Set.of(electronics));

        Product tshirt = new Product();
        tshirt.setTitle("Cotton T-Shirt");
        tshirt.setDesc("Comfortable 100% cotton t-shirt");
        tshirt.setPrice(new BigDecimal("29.99"));
        tshirt.setQuantity(50);
        tshirt.setOwner(seller2);
        tshirt.setCategories(Set.of(clothing));

        Product jeans = new Product();
        jeans.setTitle("Denim Jeans");
        jeans.setDesc("Classic blue denim jeans");
        jeans.setPrice(new BigDecimal("79.99"));
        jeans.setQuantity(25);
        jeans.setOwner(seller2);
        jeans.setCategories(Set.of(clothing));

        Product book1 = new Product();
        book1.setTitle("Spring Boot Guide");
        book1.setDesc("Complete guide to Spring Boot development");
        book1.setPrice(new BigDecimal("49.99"));
        book1.setQuantity(15);
        book1.setOwner(admin);
        book1.setCategories(Set.of(books));

        Product book2 = new Product();
        book2.setTitle("GraphQL Essentials");
        book2.setDesc("Learn GraphQL from basics to advanced");
        book2.setPrice(new BigDecimal("39.99"));
        book2.setQuantity(20);
        book2.setOwner(admin);
        book2.setCategories(Set.of(books));

        Product tablet = new Product();
        tablet.setTitle("Tablet Pro");
        tablet.setDesc("Professional tablet for work and creativity");
        tablet.setPrice(new BigDecimal("599.99"));
        tablet.setQuantity(8);
        tablet.setOwner(seller1);
        tablet.setCategories(Set.of(electronics));

        Product dress = new Product();
        dress.setTitle("Summer Dress");
        dress.setDesc("Light and comfortable summer dress");
        dress.setPrice(new BigDecimal("59.99"));
        dress.setQuantity(30);
        dress.setOwner(seller2);
        dress.setCategories(Set.of(clothing));

        productRepository.save(laptop);
        productRepository.save(smartphone);
        productRepository.save(tshirt);
        productRepository.save(jeans);
        productRepository.save(book1);
        productRepository.save(book2);
        productRepository.save(tablet);
        productRepository.save(dress);

        System.out.println("Sample data initialized successfully!");
        System.out.println("Users: " + userRepository.count());
        System.out.println("Categories: " + categoryRepository.count());
        System.out.println("Products: " + productRepository.count());
    }
}
