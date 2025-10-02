package com.ltw.bt06.service;

import com.ltw.bt06.model.Category;
import com.ltw.bt06.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> getAll() {
        return repo.findAll();
    }

    public Category getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Category save(Category category) {
        return repo.save(category);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Category> search(String keyword) {
        return repo.findByNameContainingIgnoreCase(keyword);
    }
}
