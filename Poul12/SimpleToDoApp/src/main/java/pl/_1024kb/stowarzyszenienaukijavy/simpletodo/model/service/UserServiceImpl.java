package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.UserDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.UserService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc.DaoFactory;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.*;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.utility.MD5Hash;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService
{
    private static UserServiceImpl instance;
    private DaoFactory factory = DaoFactory.getDaoFactory(DaoFactory.MYSQL_DAO);
    private UserDao dao = factory.getUserDao();
    //private MysqlUserDao jdbcDao = MysqlUserDao.getInstance();
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
                    user.setPassword(encodePassword(user));
                    dao.create(user);
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
    public String editUser(User user, String username)
    {
        String message = "Pomyślnie zmieniono dane użytownika";

        Long userId = getUserId(username);

        try
        {
            if(validator.isUserValid(user))
            {
                user.setUser_id(userId);
                user.setPassword(encodePassword(user));
                dao.update(user);
                logger.info(message);
            }

        } catch (SQLException | TooShortUsernameLengthException | TooShortPasswordLengthException | NotValidUserEmailException | NotTheSamePasswordException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return message;
    }

    private String encodePassword(User user)
    {
        return MD5Hash.encode(user.getPassword());
    }

    @Override
    public String removeUser(String username)
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
        }

        return message;
    }

    private long getUserId(String username)
    {
        Long userId = 0L;

        if(getUserByUsername(username).isPresent())
            userId = getUserByUsername(username).get().getUser_id();

        return userId;
    }

    @Override
    public Optional<User> getUserByUsername(String username)
    {
        Optional<User> user;

        try
        {
            logger.debug("Pomyślnie udało się pobrać użytkownika {} z bazy", username);
            user = dao.read(username);

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
        return dao.getAllUsers();
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


