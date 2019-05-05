package pl._1024kb.stowarzyszenienaukijavy.simpletodo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.api.TaskDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.api.UserDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.api.UserService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception.*;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.TaskRepository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.UserRepository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.util.PBKDF2Hash;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//@Component
@Service
public class UserServiceImpl implements UserService
{
    private UserDao userDao;
    //private TaskDao taskDao;
    private Validator validatorB;
    private UserRepository userRepo;
    private TaskRepository taskRepo;
    private UserValidator validator = UserValidator.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserDao userDao, TaskDao taskDao, UserRepository userRepo, TaskRepository taskRepo, Validator validatorB)
    {
        this.userDao = userDao;
        //this.taskDao = taskDao;
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
        this.validatorB = validatorB;
    }

    @Override
    public void createUser(User user) throws Exception {
        String messageInfo = "Pomyślnie udało się zapisać użytkownika do bazy :)";
        StringBuilder messageError = new StringBuilder();
        try {
            //if (validator.isUserValid(user))
            //if(isValid(user))
            //{
                if(isUsernameAlreadyExist(user.getUsername()))
                {
                    String message = "This username is already exist!";
                    logger.error(message);
                    throw new UsernameIsAlreadyExistException(message);
                }

                if(isEmailAlreadyExist(user.getEmail()))
                {
                    String message = "This email is already exist!";
                    logger.error(message);
                    throw new EmailIsAlreadyExistException(message);
                }

                try
                {
                    user.setPassword(encodePassword(user));
                    //userDao.create(user);
                    userRepo.save(user);
                    logger.info(messageInfo + " - " + user.getUsername());

                } catch (ConstraintViolationException e)
                {
                    /*e.printStackTrace();
                    String messageError = "Nie udało się zapisać użytkownika do bazy";
                    logger.error(messageError + " - " + user.getUsername());
                    throw new Exception(messageError);*/
                    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
                    violations.forEach(violation -> messageError.append(violation.getMessage()).append("\n"));
                    logger.error(messageError.toString());
                    throw new Exception(messageError.toString());
                }
            //}

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void editUser(User user) throws Exception {
        String message = "Pomyślnie zmieniono dane użytownika";

        try {
            if (validator.isUserValid(user)) {
                user.setPassword(encodePassword(user));
                //userDao.update(user);
                User userToUpdate = new User();
                userToUpdate.setUserId(user.getUserId());
                userToUpdate.setUsername(user.getUsername());
                userToUpdate.setPassword(user.getPassword());
                userToUpdate.setEmail(user.getEmail());
                userRepo.save(userToUpdate);
                logger.info(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void removeUser(String username) throws SQLException {
        String message = "Pomyślnie usunięto użytkownika";
        Long userId = getUserId(username);
        User user = getUserByUsername(username).orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException);
        try {
            //userDao.delete(userId);
            userRepo.deleteById(userId);
            //taskDao.deleteAllTasks(user);
            taskRepo.deleteAllByUser(user);
            logger.info(message);
        } catch (Exception e) {
            e.printStackTrace();
            message = "Nie udało się usunąć użytkownika";
            logger.error(message);
            throw new SQLException(message);
        }
    }

    private long getUserId(String username) {
        Long userId = 0L;

        Optional<User> user = getUserByUsername(username);
        if (user.isPresent())
            userId = user.orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException).getUserId();

        return userId;
    }

    @Override
    public Optional<User> getUserByUsername(String username)
    {
        Optional<User> user;

        try {
            logger.debug("Pomyślnie udało się pobrać użytkownika {} z bazy", username);
            user = Optional.ofNullable(userDao.read(username));

        } catch (NotFoundDesiredDataRuntimeException e) {
            e.printStackTrace();
            logger.error("Nie udało się pobrać użytkownika {} z bazy", username);
            throw new UserNotFoundException(e.getMessage());
        }

        return user;
    }

    @Override
    public List<User> getAllUsers()
    {
        //return userDao.getAllUsers();
        return  userRepo.findAll();
    }

    public User getUserByEmail(String email)
    {
        return getAllUsers().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException);
    }

    @Override
    public boolean isUsernameAlreadyExist(String username)
    {
        try
        {
            getUserByUsername(username);
            return true;

        }catch (UserNotFoundException e)
        {
            return false;
        }
    }

    @Override
    public boolean isEmailAlreadyExist(String email)
    {
        try
        {
            getUserByEmail(email);
            return true;

        }catch (UserNotFoundException e)
        {
            return false;
        }
    }

    @Override
    public boolean loginVerification(String username, String password) throws IncorrectLoginException, IncorrectPasswordException
    {
        boolean isLoginCorrect = false;

        try
        {
            Optional<User> loggingUser = getUserByUsername(username);
            if (loggingUser.isPresent())
            {
                isLoginCorrect = validator.isLoginCorrect(username, password, loggingUser.orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException));
                logger.info("Weryfikacja użytkownika {} przeszła pomyślnie", username);
            }

        } catch (UserNotFoundException e) {
            e.printStackTrace();
            logger.error("Logowanie na użytkownika {} nie przeszło weryfikacji - zły login", username);
            throw new IncorrectLoginException(e.getMessage());
        } catch (IncorrectPasswordException e) {
            e.printStackTrace();
            logger.error("Logowanie na użytkownika {} nie przeszło weryfikacji - złe hasło", username);
            throw new IncorrectPasswordException(e.getMessage());
        }

        return isLoginCorrect;
    }

    private String encodePassword(User user) {
        return PBKDF2Hash.encode(user.getPassword());
    }
}


