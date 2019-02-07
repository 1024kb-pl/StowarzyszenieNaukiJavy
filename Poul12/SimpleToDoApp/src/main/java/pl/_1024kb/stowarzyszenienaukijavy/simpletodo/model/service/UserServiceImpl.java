package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.UserService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc.UserDbUtil;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService
{
    private static UserServiceImpl instance;
    private UserDbUtil jdbcDao = UserDbUtil.getInstance();
    private UserValidator validator = UserValidator.getInstance();
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
        String messageInfo = "Pomyślnie udało się zapisać użytkownika do bazy :)";
        try
        {
            if(validator.isUserValid(user) && isUsernameAlreadyExist(user.getUsername()))
            {
                try
                {
                    jdbcDao.saveUser(user, messageInfo);

                }catch(SQLException e)
                {
                    e.printStackTrace();
                    String messageError = "Nie udało się zapisać użytkownika do bazy";
                    logger.error(messageError + " - " + user.getUsername());
                    return messageError;
                }
            }

        }catch(TooShortUsernameLengthException | TooShortPasswordLengthException | NotValidUserEmailException | NotTheSamePasswordException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
            return e.getMessage();
        }

        logger.error(messageInfo + " - " + user.getUsername());
        return messageInfo;
    }
    
    @Override
    public Optional<User> getUserByUsername(String username)
    {
        try
        {
            return Optional.of(jdbcDao.getUserByUsername(username));

        } catch (SQLException e)
        {
            e.printStackTrace();
            logger.error("Nie udało się pobrać użytkownika {} z bazy", username);
        }

        logger.debug("Pomyślnie udało się pobrać użytkownika {} z bazy", username);
        return Optional.empty();
    }
    
    @Override
    public List<User> getAllUsers()
    {
        return jdbcDao.getAllUsers();
    }
    
    @Override
    public boolean isUsernameAlreadyExist(String username)
    {
        Optional<User> userFound = getUserByUsername(username);

        return userFound.map(user1 -> user1.getUsername()
                .equals(username))
                .orElse(false);
    }
    
    @Override
    public boolean loginVerification(String username, String password)
    {
        Optional<User> expectedUser = getUserByUsername(username);

        boolean isLoginCorrect = false;
        try
        {
            if(expectedUser.isPresent())
                isLoginCorrect = validator.isLoginCorrect(expectedUser.get(), new User(username, password));

            logger.info("Weryfikacja użytkownika {} przeszła pomyślnie", username);

        } catch (IncorrectLoginException e)
        {
            e.printStackTrace();
            logger.error("Logowanie na użytkownika {} nie przeszło weryfikacji", username);
        }

        return isLoginCorrect;
    }
}


