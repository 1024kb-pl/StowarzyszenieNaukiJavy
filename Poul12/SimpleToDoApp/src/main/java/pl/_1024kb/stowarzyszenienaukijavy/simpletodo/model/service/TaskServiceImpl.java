package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.service;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.TaskService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc.TaskDbUtil;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;

import java.util.List;

public class TaskServiceImpl implements TaskService
{
    private static TaskServiceImpl instance;
    private TaskDbUtil jdbcDao = TaskDbUtil.getInstance();
    private UserServiceImpl userService = UserServiceImpl.getInstance();

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
        long userId = userService.getUserByUsername(username).getId();

        return jdbcDao.saveTask(task, userId);
    }

    @Override
    public List<Task> getAllTasksByUserId(String username)
    {
        long userId = userService.getUserByUsername(username).getId();

        return jdbcDao.getAllTasksByUserId(userId);
    }

    @Override
    public void setCheckTask(String checkTask, long taskId)
    {
        jdbcDao.updateCheckTask(checkTask, taskId);
    }

    @Override
    public String changeTask(Task task)
    {
        return jdbcDao.updateTask(task);
    }

    @Override
    public void deleteTaskById(long taskId)
    {
        jdbcDao.deleteTaskById(taskId);
    }
}
