package scalerproject.controller;

import org.springframework.web.bind.annotation.*;
import scalerproject.entity.Cart.Cart;
import scalerproject.service.CartService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Fetch carts from Fake Store API (Not saved in DB)
    @GetMapping("/external")
    public List<Cart> getExternalCarts() {
        return cartService.fetchCartsFromApi();
    }

    // Fetch and save Fake Store API carts to local DB
    @PostMapping("/fetch-save")
    public List<Cart> fetchAndSaveCarts() {
        return cartService.fetchAndSaveCarts();
    }

    // Get carts from local database
    @GetMapping
    public List<Cart> getAllLocalCarts() {
        return cartService.getAllLocalCarts();
    }

    // Get a cart from local database
    @GetMapping("/{id}")
    public Optional<Cart> getLocalCartById(@PathVariable Long id) {
        return cartService.getLocalCartById(id);
    }

    // Get carts by user ID
    @GetMapping("/user/{userId}")
    public List<Cart> getCartsByUserId(@PathVariable Long userId) {
        return cartService.getCartsByUserId(userId);
    }

    // Save a new cart to local DB
    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.saveCart(cart);
    }

    // Update a local cart
    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        return cartService.updateCart(id, cart);
    }

    // Delete a local cart
    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }
}
