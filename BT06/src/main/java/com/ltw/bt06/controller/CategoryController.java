package com.ltw.bt06.controller;

import com.ltw.bt06.model.Category;
import com.ltw.bt06.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    // Lấy tất cả categories
    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }

    // Lấy category theo id
    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // Tạo mới category
    @PostMapping
    public Category create(@RequestBody Category category) {
        return service.save(category);
    }

    // Cập nhật category
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        return service.save(category);
    }

    // Xóa category
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Tìm kiếm category
    @GetMapping("/search")
    public List<Category> search(@RequestParam String keyword) {
        return service.search(keyword);
    }
}
