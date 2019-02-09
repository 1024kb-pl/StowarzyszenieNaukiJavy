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
        long userId = 0L;

        if(userService.getUserByUsername(username).isPresent())
            userId = userService.getUserByUsername(username).get().getId();

        try
        {
            jdbcDao.saveTask(task, userId);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            String messageError = "Nie udało się zapisać zadania!";
            logger.error(messageError + " - " + task.getTitle());
            return messageError;
        }

        String messageInfo = "Pomyślnie zapisano zadanie ;)";
        logger.info(messageInfo + " - " + task.getTitle());
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
    public String setCheckTask(String checkTask, long taskId)
    {
        try
        {
            jdbcDao.updateCheckTask(checkTask, taskId);
        } catch (SQLException e)
        {
            e.printStackTrace();
            String messageError = "Nie udało się zmienić statusu zadania";
            logger.error(messageError + " - id: " + taskId);
            return messageError;
        }

        String messageInfo = "Pomyślnie zmieniono status zadania";
        logger.info(messageInfo + " - id: " + taskId);
        return messageInfo;
    }

    @Override
    public String changeTask(Task task)
    {
        try
        {
            jdbcDao.updateTask(task);

        } catch (SQLException e)
        {
            e.printStackTrace();
            String messageError = "Nie udało się zaktualizować zadania";
            logger.error(messageError + " - " + task.getTitle());
            return messageError;
        }

        String messageInfo = "Pomyślnie zaktualizowano zadanie :)";
        logger.info(messageInfo + " - " + task.getTitle());
        return messageInfo;
    }

    @Override
    public String deleteTaskById(long taskId)
    {
        try
        {
            jdbcDao.deleteTaskById(taskId);

        } catch (SQLException e)
        {
            e.printStackTrace();
            String messageError = "Nie udało się usunąć zadania";
            logger.error(messageError + " - id: " + taskId);
            return messageError;
        }

        String messageInfo = "Pomyślnie usunięto zadanie";
        logger.info(messageInfo + " - id: " + taskId);
        return messageInfo;
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
