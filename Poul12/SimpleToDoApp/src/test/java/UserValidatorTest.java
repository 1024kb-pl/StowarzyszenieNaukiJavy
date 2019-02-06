import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.IncorrectLoginException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.NotValidUserEmailException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.TooShortPasswordLengthException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.TooShortUsernameLengthException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service.UserValidator;

public class UserValidatorTest
{
    private UserValidator userValidator = UserValidator.getInstance();
    private static final User DEFAULT_USER = new User("Poul12", "MyPassword");

    @BeforeEach
    public void setUp()
    {

    }

    @Test
    public void testIncorrectLogin()
    {
        final User incorrectUserTest = new User("Pablo", "wrongPass");
        Assertions.assertThrows(IncorrectLoginException.class, () -> userValidator.isLoginCorrect(DEFAULT_USER, incorrectUserTest));
    }

    @Test
    public void testTooShortUsername()
    {
        final User userWithTooShortUsernameTest = new User("Po", "password", "poul@gmail.com");
        Assertions.assertThrows(TooShortUsernameLengthException.class, () -> userValidator.isUserValid(userWithTooShortUsernameTest));
    }

    @Test
    public void testTooShortPassword()
    {
        final User userWithTooShortPasswordTest = new User("Poul", "pass", "poul@gmail.com");
        Assertions.assertThrows(TooShortPasswordLengthException.class, () -> userValidator.isUserValid(userWithTooShortPasswordTest));
    }

    @Test
    public void testNotProperEmail()
    {
        final User userWithNotProperEmailTest = new User("Poul", "password", "poul#gmailcom");
        Assertions.assertThrows(NotValidUserEmailException.class, () -> userValidator.isUserValid(userWithNotProperEmailTest));
    }


}
