package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.UserService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc.UserDbUtil;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.IncorrectLoginException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.NotValidUserEmailException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.TooShortPasswordLengthException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.TooShortUsernameLengthException;

import java.util.List;

public class UserServiceImpl implements UserService
{
    private static UserServiceImpl instance;
    private UserDbUtil jdbcDao = UserDbUtil.getInstance();
    private UserValidator validator = UserValidator.getInstance();

    private UserServiceImpl()
    {
        if(instance != null)
            throw new IllegalStateException("Cannot create new instance, use getInstance");
    }

    public static UserServiceImpl getInstance()
    {
        if(instance == null)
            instance = new UserServiceImpl();

        return instance;
    }

    @Override
    public String createUser(User user)
    {
        String message = "User is created ;)";
        try
        {
            if(validator.isUserValid(user))
            {
                return jdbcDao.saveUser(user, message);
            }

        }catch(TooShortUsernameLengthException | TooShortPasswordLengthException | NotValidUserEmailException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }

        return message;
    }
    
    @Override
    public User getUserByUsername(String username)
    {
        return jdbcDao.getUserByUsername(username);
    }
    
    @Override
    public List<User> getAllUsers()
    {
        return jdbcDao.getAllUsers();
    }
    
    @Override
    public boolean isUsernameAlreadyExist(String username)
    {
        User user = getUserByUsername(username);
        
        return user.getUsername().equals(username);
    }
    
    @Override
    public boolean loginVerification(String username, String password)
    {
        User expectedUser = getUserByUsername(username);

        boolean isLoginCorrect = false;
        try
        {
            isLoginCorrect = validator.isLoginCorrect(expectedUser, new User(username, password));

        } catch (IncorrectLoginException e)
        {
            e.printStackTrace();
        }

        return isLoginCorrect;
    }
}


