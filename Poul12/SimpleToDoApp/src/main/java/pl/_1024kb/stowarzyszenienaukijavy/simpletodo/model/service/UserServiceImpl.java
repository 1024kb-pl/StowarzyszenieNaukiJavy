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
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

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
            if(validator.isUserValid(user) && !isUsernameAlreadyExist(user.getUsername()))
            {
                try
                {
                    jdbcDao.saveUser(user);
                    logger.info(messageInfo + " - " + user.getUsername());

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

        return messageInfo;
    }

    @Override
    public String changeUser(User user, String username)
    {
        String message = "Pomyślnie zmieniono dane użytownika";
        try
        {
            if(validator.isUserValid(user))
            {
                jdbcDao.updateUser(user, username);
                logger.info(message);
            }

        } catch (SQLException | TooShortUsernameLengthException | TooShortPasswordLengthException | NotValidUserEmailException | NotTheSamePasswordException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return message;
    }

    @Override
    public String removeUser(String username)
    {
        String message = "Pomyślnie usunięto użytkownika";
        try
        {
            jdbcDao.deleteUser(username);
            logger.info(message);
        } catch (SQLException e)
        {
            e.printStackTrace();
            message = "Nie udało się usunąć użytkownika";
            logger.error(message);
        }

        return message;
    }

    @Override
    public Optional<User> getUserByUsername(String username)
    {
        Optional<User> user;
        try
        {
            logger.debug("Pomyślnie udało się pobrać użytkownika {} z bazy", username);
            user = jdbcDao.getUserByUsername(username);

        } catch (SQLException e)
        {
            e.printStackTrace();
            logger.error("Nie udało się pobrać użytkownika {} z bazy", username);
            return Optional.empty();
        }

        return user;
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

        return userFound.map(user -> user.getUsername()
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


