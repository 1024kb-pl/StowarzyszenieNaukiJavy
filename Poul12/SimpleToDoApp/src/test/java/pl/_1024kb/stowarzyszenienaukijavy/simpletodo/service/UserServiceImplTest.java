package pl._1024kb.stowarzyszenienaukijavy.simpletodo.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.TaskRepository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.UserRepository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.UserRoleRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceImplTest
{
    private static final User VALID_USER = User.builder().username("Poul").password("password").email("poul@g.com").build();
    private static final User NOT_VALID_USER = new User(1L, "P", "mail.com", "pass", "pase");

    private UserServiceImpl userServiceImpl;
    private PasswordEncoder passEnc;
    private UserRepository uRepo;
    private TaskRepository tRepo;
    private UserRoleRepository urRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @After
    public void cleanUp()
    {
        //userRepository.deleteById(VALID_USER.getId());
        System.out.println("cleanUp");
    }


    @Before
    public void setUp() {
        passEnc = mock(PasswordEncoder.class);
        uRepo = mock(UserRepository.class);
        tRepo = mock(TaskRepository.class);
        urRepo = mock(UserRoleRepository.class);

        userServiceImpl = new UserServiceImpl(passEnc, uRepo, tRepo, urRepo);
    }

    @Test
    public void givenUserWhenCreatingNewUserThenSavedIntoDatabase() throws Exception
    {
        /*when(uRepo.save(VALID_USER)).thenReturn(VALID_USER);

        userServiceImpl.createUser(VALID_USER);
        User user = userServiceImpl.getUserByUsername(VALID_USER.getUsername()).get();

        assertEquals(VALID_USER, user);
        */
    }

    @Test
    public void givenValidUserWhenFindByNameThenReturnUser()
    {
        /*
        //given
        User alex = new User(0L, "alex", "alexpass", "alexpass", "alex@g.mail");
        userRepository.save(VALID_USER);

        // when
        User found = userRepository.findUserByUsername(VALID_USER.getUsername());
        userRepository.delete(VALID_USER);
        taskRepository.deleteAllByUser(VALID_USER);

        // then
        assertEquals(found.getUsername(), VALID_USER.getUsername());
        */
    }
}
