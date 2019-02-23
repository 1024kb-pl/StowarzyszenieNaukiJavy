package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.TaskDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.TaskService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc.DaoFactory;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc.FactoryType;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception.UserNotFoundException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService
{
    private static TaskServiceImpl instance;
    private DaoFactory factory = DaoFactory.getDaoFactory(FactoryType.MYSQL_DAO);
    private TaskDao dao = factory.getTaskDao();
    private UserServiceImpl userService = UserServiceImpl.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private TaskServiceImpl(){
        if(instance != null)
            throw new IllegalStateException("Cannot create new instance, use getInstance");
    }

    public static TaskServiceImpl getInstance()
    {
        if(instance == null) {
            instance = new TaskServiceImpl();
        }

        return instance;
    }

    @Override
    public String createTask(Task task, String username)
    {
        String messageInfo = "Pomyślnie zapisano zadanie ;)";
        Long userId = 0L;

        if(userService.getUserByUsername(username).isPresent()) {
            userId = userService.getUserByUsername(username)
                                .orElseThrow(this::newRunTimeException)
                                .getUserId();
        }

        task.setUserId(userId);

        try
        {
            dao.create(task);
            logger.info(messageInfo + " - " + task.getTitle());
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            String messageError = "Nie udało się zapisać zadania!";
            logger.error(messageError + " - " + task.getTitle());
            return messageError;
        }

        return messageInfo;
    }

    @Override
    public List<Task> getAllTasksByUserId(String username)
    {
        Long userId = 0L;

        if(userService.getUserByUsername(username).isPresent()) {
            userId = userService.getUserByUsername(username)
                                .orElseThrow(this::newRunTimeException)
                                .getUserId();
        }

        return dao.read(userId);
    }

    @Override
    public String changeTask(Task task)
    {
        String messageInfo = "Pomyślnie zaktualizowano zadanie :)";
        try
        {
            dao.update(task);
            logger.info(messageInfo + " - " + task.getTitle());
        } catch (SQLException e)
        {
            e.printStackTrace();
            String messageError = "Nie udało się zaktualizować zadania";
            logger.error(messageError + " - " + task.getTitle());
            return messageError;
        }

        return messageInfo;
    }

    @Override
    public String deleteTaskById(Long taskId)
    {
        String messageInfo = "Pomyślnie usunięto zadanie";
        try
        {
            dao.delete(taskId);
            logger.info(messageInfo + " - id: " + taskId);

        } catch (SQLException e)
        {
            e.printStackTrace();
            String messageError = "Nie udało się usunąć zadania";
            logger.error(messageError + " - id: " + taskId);
            return messageError;
        }
        return messageInfo;
    }

    @Override
    public void deleteAllTasks(String username)
    {
        Long userId = 0L;

        if(userService.getUserByUsername(username).isPresent())
        {
            userId = userService.getUserByUsername(username)
                                .orElseThrow(this::newRunTimeException)
                                .getUserId();
        }

        try
        {
            dao.deleteAllTasks(userId);
            logger.info("Usunięto wszystkie zadania użytkownika {}", username);
        } catch (SQLException e)
        {
            e.printStackTrace();
            logger.error("Nie udało się usunąć wszystkich zadań użytkownika {}", username);
        }
    }

    @Override
    public List<Task> getAllTasksByDate(String username, LocalDate dateFilter)
    {
        return getAllTasksByUserId(username).stream()
                .filter(task -> task.getDate().equals(dateFilter))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksByTaskDone(String username, boolean doneFilter)
    {
        return getAllTasksByUserId(username).stream()
                .filter(task -> task.getTaskDone() == doneFilter)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksOrderedByTitle(String username)
    {
        return getAllTasksByUserId(username).stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksOrderedByDate(String username)
    {
        return getAllTasksByUserId(username).stream()
                .sorted(Comparator.comparing(Task::getDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksOrderedByStatus(String username)
    {
        return getAllTasksByUserId(username).stream()
                .sorted(Comparator.comparing(Task::getTaskDone).reversed())
                .collect(Collectors.toList());
    }

    private UserNotFoundException newRunTimeException()
    {
        return new UserNotFoundException("Not found any desired user");
    }
}
