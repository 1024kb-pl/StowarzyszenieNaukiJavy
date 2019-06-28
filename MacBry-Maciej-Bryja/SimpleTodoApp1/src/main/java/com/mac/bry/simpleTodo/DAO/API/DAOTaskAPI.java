package com.mac.bry.simpleTodo.DAO.API;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.mac.bry.simpleTodo.entity.Task;

public interface DAOTaskAPI {

	void addTask(Task task);

	void editTaskName(Task task, String newTaskName);

	Optional<Task> selectTaskByID(int taskID);

	List<Task> getAllTasks();

	void deleteTaskByID(int taskID);

	void changeTaskStatus(Task task);

	List<Task> selectTasksByStatus(boolean status);

	List<Task> selectTasksByDataOfCompletion(LocalDate dateOfCompletion);

	List<Task> sortTasksByName();

	List<Task> sortTasksByStatus();

	List<Task> sortTasksByDateOfCompletion();
}
