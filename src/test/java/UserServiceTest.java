import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.User;
import com.example.UserRepository;
import com.example.UserService;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testAddUser_UserDoesNotExist() {

        User user = new User("Rafael", "user@example.com");

        when(userRepository.userExists(user.getEmail())).thenReturn(false);

        String resultado = userService.addUser(user);

        assertEquals("User added successfully.", resultado);
        verify(userRepository).addUser(user);
    }

    @Test
    public void testAddUser_UserExists() {
       User user = new User("Rafael", "user@example.com");

       when(userRepository.userExists(user.getEmail())).thenReturn(true);

       String resultado = userService.addUser(user);

       assertEquals("User already exists.", resultado);
       verify(userRepository, never()).addUser(user);
    }
}
