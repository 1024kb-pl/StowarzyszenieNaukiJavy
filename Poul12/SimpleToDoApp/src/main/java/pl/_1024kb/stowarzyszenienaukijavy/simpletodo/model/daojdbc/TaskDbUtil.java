package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.daojdbc;

import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.api.TaskDao;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.entity.Task;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class TaskDbUtil implements TaskDao
{
    private static TaskDbUtil instance;

    private TaskDbUtil()
    {
        if(instance != null)
            throw new IllegalStateException("Cannot create new instance, use getInstance");
    }

    public static TaskDbUtil getInstance()
    {
        if(instance == null)
            instance = new TaskDbUtil();

        return instance;
    }

    @Override
    public void saveTask(Task task, long userId) throws SQLException
    {
        String sqlQuery = "INSERT INTO tasks(title, date, description, username_id) VALUES(?, ?, ?, ?)";
        try(Connection connection = ConnectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery))
        {

            statement.setString(1, task.getTitle());
            statement.setDate(2, Date.valueOf(task.getDate()));
            statement.setString(3, task.getDescription());
            statement.setLong(4, userId);

            statement.execute();
        }
    }

    @Override
    public List<Task> getAllTasksByUserId(long userId)
    {
        List<Task> tasksList = new LinkedList<>();
        String selectQuery = String.format("SELECT * FROM tasks WHERE username_id=%d", userId);
        try(Connection connection = ConnectionProvider.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery))
        {
            while (resultSet.next())
            {
                long taskId = resultSet.getLong("task_id");
                String title = resultSet.getString("title");
                Date date = resultSet.getDate("date");
                String description = resultSet.getString("description");
                boolean taskDone = resultSet.getBoolean("task_done");

                LocalDate taskDate = date.toLocalDate();

                Task task = new Task(taskId, title, taskDate, description, taskDone);
                tasksList.add(task);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return tasksList;
    }

    @Override
    public void updateCheckTask(String checkTask, long task_id) throws SQLException
    {
        String sqlQuery = "UPDATE tasks SET task_done=? WHERE task_id=?";
        try(Connection connection = ConnectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery))
        {
            statement.setString(1, checkTask);
            statement.setLong(2, task_id);

            statement.executeUpdate();
        }
    }

    @Override
    public void updateTask(Task task) throws SQLException
    {
        String sqlQuery = "UPDATE tasks SET title=?, date=?, description=?, task_done=? WHERE task_id=?";
        try(Connection connection = ConnectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery))

        {
            statement.setString(1, task.getTitle());
            statement.setDate(2, Date.valueOf(task.getDate()));
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getTaskDone());
            statement.setLong(5, task.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void deleteTaskById(long taskId) throws SQLException
    {
        String sqlQuery = "DELETE FROM tasks WHERE task_id=?";
        try(Connection connection = ConnectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery))
        {
            statement.setLong(1, taskId);

            statement.executeUpdate();
        }
    }

    @Override
    public void deleteAllTasks(long userId) throws SQLException
    {
        String sqlQuery = "DELETE FROM tasks WHERE username_id=?";
        try(Connection connection = ConnectionProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery))
        {
            statement.setLong(1, userId);

            statement.executeUpdate();
        }
    }
}
