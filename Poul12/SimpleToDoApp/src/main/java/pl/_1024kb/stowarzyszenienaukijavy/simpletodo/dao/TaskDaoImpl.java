package pl._1024kb.stowarzyszenienaukijavy.simpletodo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.api.TaskDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.Task;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("ALL")
@Repository
@Transactional
public class TaskDaoImpl implements TaskDao
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Task task) throws SQLException {
        entityManager.persist(task);
    }

    @Override
    public List<Task> getAllByUserId(User user) throws SQLException
    {
        TypedQuery<Task> taskList = entityManager.createNamedQuery("Task.getAllByUserId", Task.class);
        taskList.setParameter("user", user);
        return taskList.getResultList();
    }

    @Override
    public void update(Task task) throws SQLException
    {
        Task foundTask = entityManager.find(Task.class, task.getTaskId());
        if(foundTask != null)
        {
            foundTask.setDate(task.getDate());
            foundTask.setDescription(task.getDescription());
            foundTask.setTitle(task.getTitle());
            foundTask.setTaskDone(task.getTaskDone());
        }
        entityManager.merge(task);
    }

    @Override
    public void delete(Long taskId) throws SQLException {
        Task taskToRemove = entityManager.find(Task.class, taskId);
        entityManager.remove(taskToRemove);
    }

    @Override
    public void deleteAllTasks(User user) throws SQLException
    {
        Query deleteAllQuery = entityManager.createNamedQuery("Task.deleteAllTask");
        deleteAllQuery.setParameter("user", user);
        deleteAllQuery.executeUpdate();
    }
}
