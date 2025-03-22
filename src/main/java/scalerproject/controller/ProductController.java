package scalerproject.controller;

import org.springframework.web.bind.annotation.*;
import scalerproject.entity.Product;
import scalerproject.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Fetch products from Fake Store API (Not saved in local DB)
    @GetMapping("/external")
    public List<Product> getExternalProducts() {
        return productService.fetchAllProductsFromApi();
    }

    // Fetch and save Fake Store API data to local DB
    @PostMapping("/fetch-save")
    public List<Product> fetchAndSaveProducts() {
        return productService.fetchAndSaveProducts();
    }

    // Get products from local database
    @GetMapping
    public List<Product> getAllLocalProducts() {
        return productService.getAllLocalProducts();
    }

    // Get a product from local database
    @GetMapping("/{id}")
    public Optional<Product> getLocalProductById(@PathVariable Long id) {
        return productService.getLocalProductById(id);
    }

    // Save a new product to local DB
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // Update a local product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // Delete a local product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
