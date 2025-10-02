package com.ltw.graphqlshop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private Integer quantity;

    @Column(name = "descr", columnDefinition = "TEXT")
    private String desc;

    private BigDecimal price;

    // owner
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    // categories
    @ManyToMany
    @JoinTable(name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    // Constructors
    public Product() {}

    public Product(Long id, String title, Integer quantity, String desc, BigDecimal price, User owner, Set<Category> categories) {
        this.id = id;
        this.title = title;
        this.quantity = quantity;
        this.desc = desc;
        this.price = price;
        this.owner = owner;
        this.categories = categories != null ? categories : new HashSet<>();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }

    public Set<Category> getCategories() { return categories; }
    public void setCategories(Set<Category> categories) { this.categories = categories; }
}
