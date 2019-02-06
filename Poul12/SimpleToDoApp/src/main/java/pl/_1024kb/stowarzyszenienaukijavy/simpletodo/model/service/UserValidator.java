package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.IncorrectLoginException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.NotValidUserEmailException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.TooShortPasswordLengthException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.TooShortUsernameLengthException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.utility.MD5Hash;

import java.util.regex.Pattern;

public class UserValidator
{
    final static String EMAIL_PATTERN = Pattern.compile("\\S+@\\w+[\\.][\\w]{2,4}").pattern();
    private static UserValidator instance;

    private UserValidator()
    {
      
    }

    public static UserValidator getInstance()
    {
        if(instance == null)
            instance = new UserValidator();

        return instance;
    }
    
    public boolean isLoginCorrect(User expected, User loggingIn) throws IncorrectLoginException
    {
        String expectedHashPassword = expected.getPassword();
        String loginHashPassword = MD5Hash.encode(loggingIn.getPassword());

        if(expected.getUsername().equals(loggingIn.getUsername()) && expectedHashPassword.equals(loginHashPassword))
            return true;

        throw new IncorrectLoginException("Login is incorrect!");
    }
    
    
    public boolean isUserValid(User user) throws TooShortUsernameLengthException, TooShortPasswordLengthException, NotValidUserEmailException
    {
        if(!isUsernameValid(user.getUsername()))
            throw new TooShortUsernameLengthException("User's name is too short!");

        if(!isPasswordValid(user.getPassword()))
            throw new TooShortPasswordLengthException("User's password is too short!");

        if(!isEmailValid(user.getEmail()))
         throw new NotValidUserEmailException("User's email is not valid!");

         return true;
    }

    private boolean isUsernameValid(String username)
    {
        return username.length() > 3;
    }

    private boolean isPasswordValid(String password)
    {
        return password.length() > 6;
    }

    private boolean isEmailValid(String email)
    {
        return email.matches(EMAIL_PATTERN);
    }
}


