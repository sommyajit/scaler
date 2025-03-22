package scalerproject.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import scalerproject.entity.Cart.Cart;
import scalerproject.repo.CartRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final RestTemplate restTemplate;
    private final CartRepository cartRepository;
    private static final String API_URL = "https://fakestoreapi.com/carts";

    public CartService(RestTemplate restTemplate, CartRepository cartRepository) {
        this.restTemplate = restTemplate;
        this.cartRepository = cartRepository;
    }

    // Fetch carts from Fake Store API
    public List<Cart> fetchCartsFromApi() {
        Cart[] carts = restTemplate.getForObject(API_URL, Cart[].class);
        return Arrays.asList(carts);
    }

    // Fetch and save Fake Store API carts to local DB
    public List<Cart> fetchAndSaveCarts() {
        List<Cart> carts = fetchCartsFromApi();
        return cartRepository.saveAll(carts);
    }

    // Get all local carts
    public List<Cart> getAllLocalCarts() {
        return cartRepository.findAll();
    }

    // Get a local cart by ID
    public Optional<Cart> getLocalCartById(Long id) {
        return cartRepository.findById(id);
    }

    // Get carts by user ID
    public List<Cart> getCartsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    // Save a new cart
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // Update a cart
    public Cart updateCart(Long id, Cart updatedCart) {
        return cartRepository.findById(id)
                .map(existingCart -> {
                    existingCart.setUserId(updatedCart.getUserId());
                    existingCart.setDate(updatedCart.getDate());
                    existingCart.setProducts(updatedCart.getProducts());
                    return cartRepository.save(existingCart);
                })
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    // Delete a cart
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
