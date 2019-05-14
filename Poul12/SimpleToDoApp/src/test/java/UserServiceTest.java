import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.api.UserService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception.NotFoundDesiredDataRuntimeException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.TaskRepository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.UserRepository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.UserRoleRepository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.service.UserServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest
{

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private UserRoleRepository mockRoleRepository;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private PasswordEncoder mockPasswordEncoder;

    private UserService userServiceUnderTest;
    private User user;

    @Before
    public void setUp() {
        initMocks(this);
        userServiceUnderTest = new UserServiceImpl(mockPasswordEncoder, mockUserRepository, taskRepository, mockRoleRepository);

        user = User.builder()
                .id(1L)
                .username("Poul")
                .password("strongpass")
                .email("test@test.com")
                .build();

        Mockito.when(mockUserRepository.save(any())).thenReturn(user);
        Mockito.when(mockUserRepository.findUserByUsername(anyString())).thenReturn(user);
    }

    @Test
    public void testFindUserByUsername() {
        // Setup
        final String username = "Poul";

        // Run the test
        final User result = userServiceUnderTest.getUserByUsername(username).orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException);

        // Verify the results
        assertEquals(username, result.getUsername());
    }
}