package com.mac.bry.simpleTodo.appController;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import com.mac.bry.simpleTodo.DAO.TaskDAO;
import com.mac.bry.simpleTodo.DAO.UserDAO;
import com.mac.bry.simpleTodo.Enums.TaskOption;
import com.mac.bry.simpleTodo.entity.Task;
import com.mac.bry.simpleTodo.entity.User;
import com.mac.bry.simpleTodo.entity.comparators.TaskComparatorByDateOfCompletion;
import com.mac.bry.simpleTodo.entity.comparators.TaskComparatorByName;
import com.mac.bry.simpleTodo.entity.comparators.TaskComparatorByStatus;
import com.mac.bry.simpleTodo.utilitis.TaskPrintProcessor;
import com.mac.bry.simpleTodo.utilitis.TaskReader;
import com.mac.bry.simpleTodo.utilitis.API.TaskReaderAPI;

public class TaskController {
	
	private Logger logger = Logger.getLogger(TaskController.class.getName());

	private TaskReaderAPI taskReader;
	private UserDAO userDAO;
	private TaskDAO taskDAO;
	private User loggedUser;
	private MainController mainController;

	public TaskController() {
		super();
		this.taskReader = new TaskReader();
		this.userDAO = new UserDAO();
		this.taskDAO = new TaskDAO();
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

		while ((option = TaskOption.createFromInt(taskReader.readNumber())) != TaskOption.LOGOUT) {
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
				editTaskNam();
				taskLoop();
				break;

			case EDIT_TASK_STATUS:
				editTaskStatus();
				taskLoop();
				break;

			case DELET_TASK_BY_ID:
				deletTask();
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

			case LOGOUT:
				logout();
				break;

			default:
				System.err.println("\nNo such option");
				taskLoop();
				break;
			}
		}

	}

	private void logout() {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() + "] ---> Run " + name);
		
		this.mainController = new MainController();
		mainController.programLoop();

	}

	private void sortTasksByDate() {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() + "] ---> Run " + name);
		
		List<Task> tasks = taskDAO.getAllTasks();
		tasks.sort(new TaskComparatorByDateOfCompletion());
		TaskPrintProcessor.printTasks(tasks);
	}

	private void sortTasksByStatus() {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() + "] ---> Run " + name);
		
		List<Task> tasks = taskDAO.getAllTasks();
		tasks.sort(new TaskComparatorByStatus());
		TaskPrintProcessor.printTasks(tasks);
	}

	private void sortTasksByName() {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() + "] ---> Run " + name);
		
		List<Task> tasks = taskDAO.getAllTasks();
		tasks.sort(new TaskComparatorByName());
		TaskPrintProcessor.printTasks(tasks);

	}

	private void showTasksByDate() {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() + "] ---> Run " + name);
		
		List<Task> tasks = taskDAO.selectTasksByDataOfCompletion(taskReader.readAndCreateDate());
		TaskPrintProcessor.printTasks(tasks);
	}

	private void showTasksByStatus() {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() + "] ---> Run " + name);
		
		List<Task> tasks = taskDAO.selectTasksByStatus(taskReader.readBoolean("true or false"));
		TaskPrintProcessor.printTasks(tasks);
	}

	private void deletTask() {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() + "] ---> Run " + name);
		
		taskDAO.deleteTaskByID(taskReader.readNumber("Task ID"));
	}

	private void editTaskStatus() {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() + "] ---> Run " + name);
		
		taskDAO.changeTaskStatus(taskDAO.selectTaskByID(taskReader.readNumber("Task ID")));
	}

	private void editTaskNam() {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() + "] ---> Run " + name);
		
		Task tempTask = taskDAO.selectTaskByID(taskReader.readNumber("ID"));
		taskDAO.editTaskName(tempTask, taskReader.readString("new Task name"));
		taskLoop();

	}

	private void showTasks() {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() + "] ---> Run " + name);
		
		TaskPrintProcessor.printTasks(taskDAO.getAllTasks());
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
		
		userDAO.addTaskToUser(loggedUser, taskReader.readAndCreateTask());
	}
}
