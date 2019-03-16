package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.UserDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.UserService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc.DaoFactory;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc.FactoryType;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.IncorrectLoginException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.IncorrectPasswordException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.UserNotFoundException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.UsernameIsAlreadyExistException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.utility.PBKDF2Hash;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService
{
    private static UserServiceImpl instance;
    private DaoFactory factory = DaoFactory.getDaoFactory(FactoryType.MYSQL_DAO);
    private UserDao dao = factory.getUserDao();
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
    public void createUser(User user) throws Exception {
        String messageInfo = "Pomyślnie udało się zapisać użytkownika do bazy :)";
        try
        {
            if(validator.isUserValid(user) && !isUsernameAlreadyExist(user.getUsername()))
            {
                try
                {
                    user.setPassword(encodePassword(user));
                    dao.create(user);
                    logger.info(messageInfo + " - " + user.getUsername());

                }catch(SQLException e)
                {
                    e.printStackTrace();
                    String messageError = "Nie udało się zapisać użytkownika do bazy";
                    logger.error(messageError + " - " + user.getUsername());
                    throw new SQLException(messageError);
                }
            }
            else
                throw new UsernameIsAlreadyExistException("This username is already exist!");

        }catch(Exception e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void editUser(User user) throws Exception {
        String message = "Pomyślnie zmieniono dane użytownika";
        Long userId = getUserId(user.getUsername());

        try
        {
            if(validator.isUserValid(user))
            {
                user.setUserId(userId);
                user.setPassword(encodePassword(user));
                dao.update(user);
                logger.info(message);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void removeUser(String username) throws SQLException
    {
        String message = "Pomyślnie usunięto użytkownika";
        Long userId = getUserId(username);

        try
        {
            dao.delete(userId);
            logger.info(message);
        } catch (SQLException e)
        {
            e.printStackTrace();
            message = "Nie udało się usunąć użytkownika";
            logger.error(message);
            throw new SQLException(message);
        }
    }

    private long getUserId(String username)
    {
        Long userId = 0L;

        Optional<User> user = getUserByUsername(username);
        if(user.isPresent())
            userId = user.orElseThrow(this::newRunTimeException).getUserId();

        return userId;
    }

    @Override
    public Optional<User> getUserByUsername(String username)
    {
        Optional<User> user = Optional.empty();

        try
        {
            logger.debug("Pomyślnie udało się pobrać użytkownika {} z bazy", username);
            user = Optional.ofNullable(dao.read(username));

        } catch (SQLException e)
        {
            e.printStackTrace();
            logger.error("Nie udało się pobrać użytkownika {} z bazy", username);
        }

        return user;
    }
    
    @Override
    public List<User> getAllUsers()
    {
        return dao.getAllUsers();
    }

    public User getUserByEmail(String email)
    {
        return getAllUsers().stream()
                            .filter(user -> user.getEmail().equals(email))
                            .findFirst()
                            .orElseThrow(this::newRunTimeException);
    }
    
    @Override
    public boolean isUsernameAlreadyExist(String username)
    {
        Optional<User> userFound = getUserByUsername(username);
        return userFound.isPresent();
    }
    
    @Override
    public boolean loginVerification(String username, String password) throws IncorrectLoginException, IncorrectPasswordException
    {
        boolean isLoginCorrect = false;

        try
        {
            Optional<User> loggingUser = getUserByUsername(username);
            if(loggingUser.isPresent())
            {
                isLoginCorrect = validator.isLoginCorrect(username, password, loggingUser.orElseThrow(this::newRunTimeException));
                logger.info("Weryfikacja użytkownika {} przeszła pomyślnie", username);
            }

        } catch (IncorrectLoginException e)
        {
            e.printStackTrace();
            logger.error("Logowanie na użytkownika {} nie przeszło weryfikacji - zły login", username);
            throw new IncorrectLoginException(e.getMessage());
        } catch (IncorrectPasswordException e)
        {
            e.printStackTrace();
            logger.error("Logowanie na użytkownika {} nie przeszło weryfikacji - złe hasło", username);
            throw new IncorrectPasswordException(e.getMessage());
        }

        return isLoginCorrect;
    }

    private String encodePassword(User user)
    {
        return PBKDF2Hash.encode(user.getPassword());
    }

    private UserNotFoundException newRunTimeException()
    {
        return new UserNotFoundException("Not found any desired user");
    }

}


