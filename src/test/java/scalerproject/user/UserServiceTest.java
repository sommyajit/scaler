package scalerproject.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import scalerproject.entity.User.User;
import scalerproject.repo.UserRepository;
import scalerproject.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User sampleUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleUser = new User(1L, "test@example.com", "testuser", "password123", null, null);
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(sampleUser));
        List<User> users = userService.getAllLocalUsers();
        assertEquals(1, users.size());
        assertEquals("testuser", users.get(0).getUsername());
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));
        Optional<User> user = userService.getLocalUserById(1L);
        assertTrue(user.isPresent());
        assertEquals("testuser", user.get().getUsername());
    }

    @Test
    void testSaveUser() {
        when(userRepository.save(sampleUser)).thenReturn(sampleUser);
        User savedUser = userService.saveUser(sampleUser);
        assertNotNull(savedUser);
        assertEquals("testuser", savedUser.getUsername());
    }

    @Test
    void testUpdateUser() {
        User updatedUser = new User(1L, "updated@example.com", "updateduser", "newpassword", null, null);
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser(1L, updatedUser);
        assertEquals("updateduser", result.getUsername());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);
        assertDoesNotThrow(() -> userService.deleteUser(1L));
        verify(userRepository, times(1)).deleteById(1L);
    }
}
