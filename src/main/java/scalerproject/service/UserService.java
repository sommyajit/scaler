package scalerproject.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import scalerproject.entity.User.User;
import scalerproject.repo.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private static final String API_URL = "https://fakestoreapi.com/users";

    public UserService(RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    // Fetch users from Fake Store API
    public List<User> fetchUsersFromApi() {
        User[] users = restTemplate.getForObject(API_URL, User[].class);
        return Arrays.asList(users);
    }

    // Fetch and save Fake Store API users to local DB
    public List<User> fetchAndSaveUsers() {
        List<User> users = fetchUsersFromApi();
        return userRepository.saveAll(users);
    }

    // Get all local users
    public List<User> getAllLocalUsers() {
        return userRepository.findAll();
    }

    // Get a local user by ID
    public Optional<User> getLocalUserById(Long id) {
        return userRepository.findById(id);
    }

    // Save a new user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Update a user
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(updatedUser.getUsername());
                    existingUser.setEmail(updatedUser.getEmail());
                    existingUser.setPassword(updatedUser.getPassword());
                    existingUser.setAddress(updatedUser.getAddress());
                    existingUser.setName(updatedUser.getName());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
