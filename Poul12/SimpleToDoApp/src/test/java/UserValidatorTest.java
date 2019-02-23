import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.NotValidUserEmailException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.TooShortPasswordLengthException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.TooShortUsernameLengthException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.UserValidator;

public class UserValidatorTest
{
    private UserValidator userValidator = UserValidator.getInstance();
    private static final User DEFAULT_USER = User.builder().username("Poul12").password("myPassword").build();

    @BeforeEach
    public void setUp()
    {

    }

    /*@Test
    public void testIncorrectLogin()
    {
        final User incorrectUserTest = User.builder()
                                            .username("Pablo")
                                            .password("wrongPass")
                                            .build();
        Assertions.assertThrows(IncorrectLoginException.class, () -> userValidator.isLoginCorrect(DEFAULT_USER, incorrectUserTest));
    }*/

    @Test
    public void testTooShortUsername()
    {
        final User userWithTooShortUsernameTest = User.builder()
                                                        .username("Po")
                                                        .password("password")
                                                        .build();
        Assertions.assertThrows(TooShortUsernameLengthException.class, () -> userValidator.isUserValid(userWithTooShortUsernameTest));
    }

    @Test
    public void testTooShortPassword()
    {
        final User userWithTooShortPasswordTest = User.builder()
                                                        .username("Poul")
                                                        .password("pass")
                                                        .build();
        Assertions.assertThrows(TooShortPasswordLengthException.class, () -> userValidator.isUserValid(userWithTooShortPasswordTest));
    }

    @Test
    public void testNotProperEmail()
    {
        final User userWithNotProperEmailTest = User.builder()
                                                    .username("Poul")
                                                    .password("password")
                                                    .email("poul#gmailcomn")
                                                    .build();

        Assertions.assertThrows(NotValidUserEmailException.class, () -> userValidator.isUserValid(userWithNotProperEmailTest));
    }


}
