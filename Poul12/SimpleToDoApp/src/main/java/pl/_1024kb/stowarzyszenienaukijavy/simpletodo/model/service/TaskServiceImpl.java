package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.TaskService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc.TaskDbUtil;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService
{
    private static TaskServiceImpl instance;
    private TaskDbUtil jdbcDao = TaskDbUtil.getInstance();
    private UserServiceImpl userService = UserServiceImpl.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private TaskServiceImpl(){
        if(instance != null)
            throw new IllegalStateException("Cannot create new instance, use getInstance");
    }

    public static TaskServiceImpl getInstance()
    {
        if(instance == null)
                instance = new TaskServiceImpl();

        return instance;
    }

    @Override
    public String createTask(Task task, String username)
    {
        String messageInfo = "Pomyślnie zapisano zadanie ;)";
        long userId = 0L;

        if(userService.getUserByUsername(username).isPresent())
            userId = userService.getUserByUsername(username).get().getId();

        try
        {
            jdbcDao.saveTask(task, userId);
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
        long userId = 0L;

        if(userService.getUserByUsername(username).isPresent())
            userId = userService.getUserByUsername(username).get().getId();

        return jdbcDao.getAllTasksByUserId(userId);
    }

    @Override
    public String changeTask(Task task)
    {
        String messageInfo = "Pomyślnie zaktualizowano zadanie :)";
        try
        {
            jdbcDao.updateTask(task);
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
    public String deleteTaskById(long taskId)
    {
        String messageInfo = "Pomyślnie usunięto zadanie";
        try
        {
            jdbcDao.deleteTaskById(taskId);
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
        long userId = 0L;

        if(userService.getUserByUsername(username).isPresent())
            userId = userService.getUserByUsername(username).get().getId();

        try
        {
            jdbcDao.deleteAllTasks(userId);
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
    public List<Task> getAllTasksByTaskDone(String username, String doneFilter)
    {
        return getAllTasksByUserId(username).stream()
                .filter(task -> task.getTaskDone().equals(doneFilter))
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
}
