package scalerproject.controller;

import org.springframework.web.bind.annotation.*;
import scalerproject.entity.Category;
import scalerproject.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Fetch categories from Fake Store API (Not saved in DB)
    @GetMapping("/external")
    public List<String> getExternalCategories() {
        return categoryService.fetchCategoriesFromApi();
    }

    // Fetch categories from API and save to DB
    @PostMapping("/fetch-save")
    public List<Category> fetchAndSaveCategories() {
        return categoryService.fetchAndSaveCategories();
    }

    // Get categories from local database
    @GetMapping
    public List<Category> getAllLocalCategories() {
        return categoryService.getAllLocalCategories();
    }

    // Get a category from local database
    @GetMapping("/{id}")
    public Category getLocalCategoryById(@PathVariable Long id) {
        return categoryService.getLocalCategoryById(id);
    }

    // Save a new category to local DB
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    // Update a local category
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    // Delete a local category
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
