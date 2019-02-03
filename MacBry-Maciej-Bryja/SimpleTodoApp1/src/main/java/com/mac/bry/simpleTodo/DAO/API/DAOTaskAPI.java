package com.mac.bry.simpleTodo.DAO.API;

import java.time.LocalDate;
import java.util.List;

import com.mac.bry.simpleTodo.entity.Task;

public interface DAOTaskAPI {
	
	public void addTask(Task task);
	public void editTaskName(Task task, String newTaskName);
	public Task selectTaskByID(int taskID);
	public List<Task> getAllTasks();
	
	public void deleteTaskByID(int taskID);
	
	public void changeTaskStatus(Task task);
	
	public List<Task> selectTasksByStatus(boolean status);
	public List<Task> selectTasksByDataOfCompletion(LocalDate dateOfCompletion);

	public List<Task> sortTasksByName();
	public List<Task> sortTasksByStatus();
	public List<Task> sortTasksByDateOfCompletion();
}
