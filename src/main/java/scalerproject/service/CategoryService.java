package scalerproject.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import scalerproject.entity.Category;
import scalerproject.repo.CategoryRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final RestTemplate restTemplate;
    private final CategoryRepository categoryRepository;
    private static final String API_URL = "https://fakestoreapi.com/products/categories";

    public CategoryService(RestTemplate restTemplate, CategoryRepository categoryRepository) {
        this.restTemplate = restTemplate;
        this.categoryRepository = categoryRepository;
    }

    // Fetch categories from Fake Store API
    public List<String> fetchCategoriesFromApi() {
        String[] categories = restTemplate.getForObject(API_URL, String[].class);
        return Arrays.asList(categories);
    }

    // Fetch from API and save locally
    public List<Category> fetchAndSaveCategories() {
        List<String> categories = fetchCategoriesFromApi();
        List<Category> categoryEntities = categories.stream()
                .map(name -> new Category(null, name))
                .collect(Collectors.toList());

        return categoryRepository.saveAll(categoryEntities);
    }

    // Get all local categories
    public List<Category> getAllLocalCategories() {
        return categoryRepository.findAll();
    }

    public Category getLocalCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(updatedCategory.getName());
                    return categoryRepository.save(existingCategory);
                })
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
