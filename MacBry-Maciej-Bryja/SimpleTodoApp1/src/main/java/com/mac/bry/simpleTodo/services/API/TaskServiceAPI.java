package com.mac.bry.simpleTodo.services.API;

import java.time.LocalDate;
import java.util.List;

import com.mac.bry.simpleTodo.Exception.NoTasksForSelectedUser;
import com.mac.bry.simpleTodo.entity.Task;
import com.mac.bry.simpleTodo.entity.User;

public interface TaskServiceAPI {

	void addTask(Task task);

	void deleteTaskByID(int taskID);

	void editTaskName(Task task, String newTaskName);

	void changeTaskStatus(Task task);

	Task selectTaskByID(int taskID);

	List<Task> selectTasksByStatus(boolean status);

	List<Task> selectTasksByDateofCompletion(LocalDate dateOfCompletion);

	List<Task> sortTasksByName();

	List<Task> sortTasksByStatus();

	List<Task> sortTasksByDateOdCompletion();

	List<Task> getAllTasks();

	List<Task> getAllTasksOfUser(User user) throws NoTasksForSelectedUser;

	List<Task> sortTasksByStatus(User user);

	List<Task> sortTasksByDateOdCompletion(User user);

	List<Task> sortTasksByName(User user);

	List<Task> selectTasksByStatus(boolean status, User user);

	List<Task> selectTasksByDateofCompletion(LocalDate dateOfCompletion, User user);

}