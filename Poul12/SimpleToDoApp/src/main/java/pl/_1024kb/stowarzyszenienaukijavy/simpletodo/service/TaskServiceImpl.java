package pl._1024kb.stowarzyszenienaukijavy.simpletodo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.api.TaskDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.api.TaskService;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception.NotFoundDesiredDataRuntimeException;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository.TaskRepository;

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
    //private TaskDao taskDao;
    private TaskRepository taskRepo;
    private UserServiceImpl userService;
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    public TaskServiceImpl(UserServiceImpl userService, TaskDao taskDao, TaskRepository taskRepo)
    {
        this.userService = userService;
        //this.taskDao = taskDao;
        this.taskRepo = taskRepo;
    }

    @Override
    public void createTask(Task task, String username) throws SQLException {
        String messageInfo = "Pomyślnie zapisano zadanie ;)";

        if (userService.getUserByUsername(username).isPresent())
        {
            User user = userService.getUserByUsername(username).orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException);
            task.setUser(user);
            task.setTaskDone(false);
        }

        try {
            //taskDao.create(task);
            taskRepo.save(task);
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
            //return taskDao.getAllByUserId(user.orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException));
            return taskRepo.findAllByUser(user.orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException));

        } catch (Exception e) {
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
            User user = userService.getUserByUsername(username).orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException);
            task.setUser(user);
        }

        try
        {
            Task taskToUpdate = new Task();
            taskToUpdate.setTaskId(task.getTaskId());
            taskToUpdate.setTitle(task.getTitle());
            taskToUpdate.setDate(task.getDate());
            taskToUpdate.setDescription(task.getDescription());
            taskToUpdate.setTaskDone(task.getTaskDone());
            //taskDao.update(task);
            taskRepo.save(task);
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
            //taskDao.delete(taskId);
            taskRepo.deleteById(taskId);
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
        User user = userService.getUserByUsername(username).orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException);

        try {
            //taskDao.deleteAllTasks(user);
            taskRepo.deleteAllByUser(user);
            logger.info("Usunięto wszystkie zadania użytkownika {}", username);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Nie udało się usunąć wszystkich zadań użytkownika {}", username);
        }
    }

    public Task getTaskById(String username, Long taskId)
    {
        return getAllTasksByUsername(username).stream()
                    .filter(task -> task.getTaskId().equals(taskId))
                    .findFirst()
                    .orElseThrow(NotFoundDesiredDataRuntimeException::newRunTimeException);
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
}
