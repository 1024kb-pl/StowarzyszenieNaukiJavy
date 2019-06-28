package com.mac.bry.simpleTodo.appController;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import com.mac.bry.simpleTodo.DAO.TaskDAO;
import com.mac.bry.simpleTodo.DAO.UserDAO;
import com.mac.bry.simpleTodo.Enums.TaskOption;
import com.mac.bry.simpleTodo.Exception.TaskOptionNoExistException;
import com.mac.bry.simpleTodo.entity.Task;
import com.mac.bry.simpleTodo.entity.User;
import com.mac.bry.simpleTodo.entity.comparators.TaskComparatorByDateOfCompletion;
import com.mac.bry.simpleTodo.entity.comparators.TaskComparatorByName;
import com.mac.bry.simpleTodo.entity.comparators.TaskComparatorByStatus;
import com.mac.bry.simpleTodo.printProcessors.TaskPrintProcessor;
import com.mac.bry.simpleTodo.services.TaskService;
import com.mac.bry.simpleTodo.services.UserService;
import com.mac.bry.simpleTodo.utilitis.DataReader;
import com.mac.bry.simpleTodo.utilitis.TaskReader;
import com.mac.bry.simpleTodo.utilitis.API.TaskReaderAPI;

public class TaskController {
	
	private Logger logger = Logger.getLogger(TaskController.class.getName());

	private TaskReader taskReader;
	private TaskService taskService;
	private User loggedUser;
	private MainController mainController;

	public TaskController() {
		super();
		this.taskReader = new TaskReader();
		this.taskService = new TaskService();
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public void taskLoop() {
		TaskOption option;
		printTaskOptions();

		try {
			while ((option = TaskOption.getOptionByOrderNumber(taskReader.readNumber())) != TaskOption.BACK) {
				switch (option) {
				case ADD_TASK:
					System.out.println("add task");
					addTask();
					taskLoop();
					break;

				case SHOW_TASKS:
					showTasks();
					taskLoop();
					break;

				case EDIT_TASK_NAME:
					editTaskName();
					taskLoop();
					break;

				case EDIT_TASK_STATUS:
					editTaskStatus();
					taskLoop();
					break;

				case DELET_TASK_BY_ID:
					deleteTask();
					taskLoop();
					break;

				case SHOW_TASKS_BY_STATUS:
					showTasksByStatus();
					taskLoop();
					break;

				case SHOW_TASKS_BY_DATA:
					showTasksByDate();
					taskLoop();
					break;

				case SORT_TASKS_BY_NAME:
					sortTasksByName();
					taskLoop();
					break;

				case SORT_TASKS_BY_STATUS:
					sortTasksByStatus();
					taskLoop();
					break;

				case SORT_TASKS_BY_DATA:
					sortTasksByDate();
					taskLoop();
					break;

				case BACK:
					back();
					break;

				default:
					System.err.println("\nNo such option");
					taskLoop();
					break;
				}
			}
		} catch (TaskOptionNoExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void back() {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() + "] ---> Run " + name);
		
		this.mainController = new MainController();
		mainController.start();

	}

	private void sortTasksByDate() {
		TaskPrintProcessor.printTasks(taskService.sortTasksByDateOdCompletion(loggedUser));
		
	}

	private void sortTasksByStatus() {
		TaskPrintProcessor.printTasks(taskService.sortTasksByStatus(loggedUser));
		
	}

	private void sortTasksByName() {
		TaskPrintProcessor.printTasks(taskService.sortTasksByStatus(loggedUser));

	}

	private void showTasksByDate() {
		TaskPrintProcessor.printTasks(taskService.selectTasksByDateofCompletion(taskReader.readAndCreateDate(), loggedUser));
	
	}

	private void showTasksByStatus() {
		TaskPrintProcessor.printTasks(taskService.selectTasksByStatus(taskReader.readBoolean("status"), loggedUser));
		
	}

	private void deleteTask() {
		taskService.deleteTaskByID(taskReader.readNumber("Task ID"));
		
	}

	private void editTaskStatus() {	
		taskService.changeTaskStatus(taskService.selectTaskByID(taskReader.readNumber("Task ID")));
		
	}

	private void editTaskName() {
		Task tempTask = taskService.selectTaskByID(taskReader.readNumber("ID"));
		taskService.editTaskName(tempTask, taskReader.readString("new Task name"));
		taskLoop();

	}

	private void showTasks() {
		TaskPrintProcessor.printTasks(taskService.getAllTasksOfUser(loggedUser));
		
	}

	private void printTaskOptions() {
		System.out.println("Chose Option: ");
		for (TaskOption option : TaskOption.values()) {
			System.out.println(option);
		}
	}

	private void addTask() {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() + "] ---> Run " + name);
		
		taskService.addTask(taskReader.readAndCreateTask());
	}
}
