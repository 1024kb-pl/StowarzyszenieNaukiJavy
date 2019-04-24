package pl._1024kb.stowarzyszenienaukijavy.simpletodo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.api.TaskDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.api.TaskService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception.UserNotFoundException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskServiceImpl implements TaskService
{
    //private static TaskServiceImpl instance;
    //private DaoFactory factory = DaoFactory.getDaoFactory(FactoryType.MYSQL_DAO);
    //private TaskDao dao = factory.getTaskDao();
    private TaskDao taskDao;
    private UserServiceImpl userService;// = UserServiceImpl.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    /*private TaskServiceImpl() {
        if (instance != null)
            throw new IllegalStateException("Cannot create new instance, use getInstance");
    }*/

    /*public static TaskServiceImpl getInstance() {
        if (instance == null) {
            instance = new TaskServiceImpl();
        }

        return instance;
    }*/

    @Autowired
    public TaskServiceImpl(UserServiceImpl userService, TaskDao taskDao)
    {
        this.userService = userService;
        this.taskDao = taskDao;
    }

    @Override
    public void createTask(Task task, String username) throws SQLException {
        String messageInfo = "Pomyślnie zapisano zadanie ;)";

        if (userService.getUserByUsername(username).isPresent())
        {
            User user = userService.getUserByUsername(username).orElseThrow(this::newRunTimeException);
            task.setUser(user);
        }

        try {
            taskDao.create(task);
            logger.info(messageInfo + " - " + task.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            String messageError = "Nie udało się zapisać zadania!";
            logger.error(messageError + " - " + task.getTitle());
            throw new SQLException(messageError);
        }
    }

    @Override
    public List<Task> getAllTasksByUsername(String username) {
        Optional<User> user = Optional.empty();

        if (userService.getUserByUsername(username).isPresent())
        {
            user = userService.getUserByUsername(username);
        }

        try {
            return taskDao.getAllByUserId(user.orElseThrow(this::newRunTimeException));

        } catch (SQLException e) {
            e.printStackTrace();
            String messageError = "Nie udało się pobrać wszystich zadań!";
            logger.error(messageError);
        }

        return Collections.emptyList();
    }

    @Override
    public void changeTask(Task task, String username) throws SQLException {
        String messageInfo = "Pomyślnie zaktualizowano zadanie :)";

        if (userService.getUserByUsername(username).isPresent())
        {
            User user = userService.getUserByUsername(username).orElseThrow(this::newRunTimeException);
            task.setUser(user);
        }

        try
        {
            taskDao.update(task);
            logger.info(messageInfo + " - " + task.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            String messageError = "Nie udało się zaktualizować zadania";
            logger.error(messageError + " - " + task.getTitle());
            throw new SQLException(messageError);
        }
    }

    @Override
    public void deleteTaskById(Long taskId) throws SQLException {
        String messageInfo = "Pomyślnie usunięto zadanie";
        try {
            taskDao.delete(taskId);
            logger.info(messageInfo + " - id: " + taskId);

        } catch (Exception e) {
            e.printStackTrace();
            String messageError = "Nie udało się usunąć zadania";
            logger.error(messageError + " - id: " + taskId);
            throw new SQLException(messageError);
        }
    }

    @Override
    public void deleteAllTasks(String username) {
        Optional<User> user = Optional.empty();

        if (userService.getUserByUsername(username).isPresent()) {
            user = userService.getUserByUsername(username);
        }

        try {
            taskDao.deleteAllTasks(user.orElseThrow(this::newRunTimeException));
            logger.info("Usunięto wszystkie zadania użytkownika {}", username);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Nie udało się usunąć wszystkich zadań użytkownika {}", username);
        }
    }

    @Override
    public List<Task> getAllTasksByDate(String username, LocalDate dateFilter) {
        return getAllTasksByUsername(username).stream()
                .filter(task -> task.getDate().equals(dateFilter))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksByTaskDone(String username, boolean doneFilter) {
        return getAllTasksByUsername(username).stream()
                .filter(task -> task.getTaskDone() == doneFilter)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksOrderedByTitle(String username) {
        return getAllTasksByUsername(username).stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksOrderedByDate(String username) {
        return getAllTasksByUsername(username).stream()
                .sorted(Comparator.comparing(Task::getDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksOrderedByStatus(String username) {
        return getAllTasksByUsername(username).stream()
                .sorted(Comparator.comparing(Task::getTaskDone).reversed())
                .collect(Collectors.toList());
    }

    private UserNotFoundException newRunTimeException() {
        return new UserNotFoundException("Not found any desired user");
    }
}
