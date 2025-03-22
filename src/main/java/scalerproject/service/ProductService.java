package scalerproject.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import scalerproject.entity.Product;
import scalerproject.repo.ProductRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final RestTemplate restTemplate;
    private final ProductRepository productRepository;
    private static final String API_URL = "https://fakestoreapi.com/products";

    public ProductService(RestTemplate restTemplate, ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.productRepository = productRepository;
    }

    // Fetch from external API
    public List<Product> fetchAllProductsFromApi() {
        Product[] products = restTemplate.getForObject(API_URL, Product[].class);
        return Arrays.asList(products);
    }

    // Save Fake Store API data to local DB
    public List<Product> fetchAndSaveProducts() {
        List<Product> products = fetchAllProductsFromApi();
        return productRepository.saveAll(products);
    }

    // Fetch from local DB
    public List<Product> getAllLocalProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getLocalProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setTitle(updatedProduct.getTitle());
                    existingProduct.setPrice(updatedProduct.getPrice());
                    existingProduct.setCategory(updatedProduct.getCategory());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    existingProduct.setImage(updatedProduct.getImage());
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
