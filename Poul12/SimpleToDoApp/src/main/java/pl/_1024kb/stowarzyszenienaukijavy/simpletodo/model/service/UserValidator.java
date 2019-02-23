package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.*;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.utility.MD5Hash;

import java.util.regex.Pattern;

public class UserValidator
{
    private final static String EMAIL_PATTERN = Pattern.compile("\\S+@\\w+[\\.][\\w]{2,4}").pattern();
    private final static int USERNAME_LENGTH = 3;
    private final static int PASSWORD_LENGTH = 6;

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
    
    public boolean isLoginCorrect(String username, String password, User loggingIn) throws IncorrectLoginException, IncorrectPasswordException {
        //String expectedHashPassword = loggingIn.getPassword();
        String loginHashPassword = MD5Hash.encode(password);
        System.out.println(password);

        if(!username.equals(loggingIn.getUsername()))
            throw new IncorrectLoginException("Login is incorrect!");

        if(!loginHashPassword.equals(loggingIn.getPassword()))
            throw new IncorrectPasswordException("Password is incorrect!");

        return true;
    }
    
    public boolean isUserValid(User user) throws TooShortUsernameLengthException, TooShortPasswordLengthException, NotValidUserEmailException, NotTheSamePasswordException
    {
        if(!isUsernameValid(user.getUsername()))
            throw new TooShortUsernameLengthException("User's name is too short!");

        if(!isPasswordValid(user.getPassword()))
            throw new TooShortPasswordLengthException("User's password is too short!");

        if(!isEmailValid(user.getEmail()))
         throw new NotValidUserEmailException("User's email is not valid!");

        if(!isPasswordTheSame(user.getPassword(), user.getRepeatedPassword()))
            throw new NotTheSamePasswordException("User's passwords are not the same!");

         return true;
    }

    private boolean isUsernameValid(String username)
    {
        return username.length() > USERNAME_LENGTH;
    }

    private boolean isPasswordValid(String password)
    {
        return password.length() > PASSWORD_LENGTH;
    }

    private boolean isEmailValid(String email)
    {
        return email.matches(EMAIL_PATTERN);
    }

    private boolean isPasswordTheSame(String password, String repeatedPassword)
    {
        return password.equals(repeatedPassword);
    }
}


