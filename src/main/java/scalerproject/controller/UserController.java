package scalerproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import scalerproject.entity.User.User;
import scalerproject.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "User management API")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Fetch users from Fake Store API", description = "Fetches users from Fake Store API without saving to the database")
    @GetMapping("/external")
    public List<User> getExternalUsers() {
        return userService.fetchUsersFromApi();
    }

    @Operation(summary = "Fetch and save users", description = "Fetches users from Fake Store API and saves them to the database")
    @PostMapping("/fetch-save")
    public List<User> fetchAndSaveUsers() {
        return userService.fetchAndSaveUsers();
    }

    @Operation(summary = "Get all users", description = "Retrieves all users from the local database")
    @GetMapping
    public List<User> getAllLocalUsers() {
        return userService.getAllLocalUsers();
    }

    @Operation(summary = "Get user by ID", description = "Retrieves a specific user from the local database")
    @GetMapping("/{id}")
    public Optional<User> getLocalUserById(@PathVariable Long id) {
        return userService.getLocalUserById(id);
    }

    @Operation(summary = "Create a new user", description = "Saves a new user to the local database")
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @Operation(summary = "Update a user", description = "Updates an existing user in the local database")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @Operation(summary = "Delete a user", description = "Deletes a user from the local database")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
