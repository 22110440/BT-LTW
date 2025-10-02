package com.ltw.graphqlshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String images;

    @ManyToMany(mappedBy = "categories")
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    // Constructors
    public Category() {}

    public Category(Long id, String name, String images, Set<User> users, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.users = users != null ? users : new HashSet<>();
        this.products = products != null ? products : new HashSet<>();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }

    public Set<User> getUsers() { return users; }
    public void setUsers(Set<User> users) { this.users = users; }

    public Set<Product> getProducts() { return products; }
    public void setProducts(Set<Product> products) { this.products = products; }
}