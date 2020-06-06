package pl._1024kb.stowarzyszenienaukijavy.simpletodo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.api.UserService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception.*;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.UserRole;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.TaskRepository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.UserRepository;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService
{
    private static final String DEFAULT_ROLE = "ROLE_USER";
    private UserRepository userRepo;
    private TaskRepository taskRepo;
    private UserRoleRepository userRoleRepo;
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepo, TaskRepository taskRepo, UserRoleRepository userRoleRepo)
    {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    public void createUser(User user) throws Exception
    {
        String messageInfo = "Successfully saved the user to the database";
        try
        {
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

            UserRole userRole = userRoleRepo.findByRole(DEFAULT_ROLE);
            user.getRoles().add(userRole);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
            userRoleRepo.delete(userRole);
            logger.debug(messageInfo + " - " + user.getUsername());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void editUser(User user) throws Exception
    {
        String message = "User data was successfully changed";

        try
        {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                User userToUpdate = new User();
                userToUpdate.setId(user.getId());
                userToUpdate.setUsername(user.getUsername());
                userToUpdate.setPassword(user.getPassword());
                userToUpdate.setEmail(user.getEmail());
                userRepo.save(userToUpdate);
                logger.debug(message);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void removeUser(String username) throws Exception
    {
        String message = "User was removed";
        Long userId = getUserId(username);
        User user = getUserByUsername(username).orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException);
        try {
            userRepo.deleteById(userId);
            taskRepo.deleteAllByUser(user);
            logger.debug(message);
        } catch (Exception e) {
            e.printStackTrace();
            message = "The user could not be deleted";
            logger.error(message);
            throw new Exception(message);
        }
    }

    private long getUserId(String username) {
        Long userId = 0L;

        Optional<User> user = getUserByUsername(username);
        if (user.isPresent())
            userId = user.orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException).getId();

        return userId;
    }

    @Override
    public Optional<User> getUserByUsername(String username)
    {
        Optional<User> user;

        try {
            logger.debug("Pomyślnie udało się pobrać użytkownika {} z bazy", username);
            user = Optional.ofNullable(userRepo.findUserByUsername(username));//userDao.read(username));

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
        return userRepo.existsUserByUsername(username);
    }

    @Override
    public boolean isEmailAlreadyExist(String email)
    {
        return userRepo.existsUserByEmail(email);
    }
}


