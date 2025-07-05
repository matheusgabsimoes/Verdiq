package com.verdiq.controller;

import com.verdiq.entity.Category;
import com.verdiq.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/verdiq/category")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @PostMapping()
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/{id}")
    public Category getByCategoryId(@PathVariable Long id) {
        Optional<Category> optCategory = categoryService.findById(id);
        return optCategory.orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteByCategoryId(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
