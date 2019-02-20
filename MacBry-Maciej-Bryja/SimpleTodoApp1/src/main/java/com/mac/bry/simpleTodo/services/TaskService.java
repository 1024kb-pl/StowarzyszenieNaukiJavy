package com.mac.bry.simpleTodo.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.mac.bry.simpleTodo.DAO.TaskDAO;
import com.mac.bry.simpleTodo.entity.Task;
import com.mac.bry.simpleTodo.entity.User;
import com.mac.bry.simpleTodo.entity.comparators.TaskComparatorByDateOfCompletion;
import com.mac.bry.simpleTodo.entity.comparators.TaskComparatorByName;
import com.mac.bry.simpleTodo.services.API.TaskServiceAPI;

public class TaskService implements TaskServiceAPI {

	private TaskDAO taskDAO;
	private Logger logger = Logger.getLogger(TaskService.class.getName());
	
	public TaskService() {
		this.taskDAO = new TaskDAO();
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#addTask(com.mac.bry.simpleTodo.entity.Task)
	 */
	@Override
	public void addTask(Task task) {
		taskDAO.addTask(task);
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nAdded new task  ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#deleteTaskByID(int)
	 */
	@Override
	public void deleteTaskByID(int taskID) {
		taskDAO.deleteTaskByID(taskID);
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nDeleted task where id was  " + taskID;
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#editTaskName(com.mac.bry.simpleTodo.entity.Task, java.lang.String)
	 */
	@Override
	public void editTaskName(Task task, String newTaskName) {
		String oldTaskName = task.getTaskName();
		taskDAO.editTaskName(task, newTaskName);
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nChange task name from " + oldTaskName +" to " + newTaskName;
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#changeTaskStatus(com.mac.bry.simpleTodo.entity.Task)
	 */
	@Override
	public void changeTaskStatus(Task task) {
		boolean oldTaskStatus = task.isTaskStatus();
		taskDAO.changeTaskStatus(task);
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nChange task status form " + oldTaskStatus +" to " +task.isTaskStatus();
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#selectTaskByID(int)
	 */
	@Override
	public Task selectTaskByID(int taskID) {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nFound task ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
		
		return taskDAO.selectTaskByID(taskID).get();
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#selectTasksByStatus(boolean)
	 */
	@Override
	public List<Task> selectTasksByStatus(boolean status) {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nFound tasks ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
		
		return taskDAO.selectTasksByStatus(status);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#selectTasksByStatus(boolean, User)
	 */
	@Override
	public List<Task> selectTasksByStatus(boolean status, User user){
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nFound tasks ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	
		return taskDAO.selectTasksByStatus(status).stream().filter(task -> task.getUser().getLogin().equals(user.getLogin())).collect(Collectors.toList());
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#selectTasksByDateofCompletion(java.time.LocalDate)
	 */
	@Override
	public List<Task> selectTasksByDateofCompletion(LocalDate dateOfCompletion){
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nFound tasks ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
		
		return taskDAO.selectTasksByDataOfCompletion(dateOfCompletion);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#selectTasksByDateofCompletion(java.time.LocalDate, User)
	 */
	@Override
	public List<Task> selectTasksByDateofCompletion(LocalDate dateOfCompletion, User user){
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nFound tasks ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	
		return taskDAO.selectTasksByDataOfCompletion(dateOfCompletion).stream().filter(task -> task.getUser().getLogin().equals(user.getLogin())).collect(Collectors.toList());
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#sortTasksByName()
	 */
	@Override
	public List<Task> sortTasksByName() {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nSorted tasks by Name ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	
		return taskDAO.sortTasksByName();
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#sortTasksByName(User user)
	 */
	@Override
	public List<Task> sortTasksByName(User user) {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nSorted tasks by Name ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	
		List<Task> tempTasksList = getAllTasksOfUser(user);
		tempTasksList.sort(new TaskComparatorByName());
		return tempTasksList;
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#sortTasksByStatus()
	 */
	@Override
	public List<Task> sortTasksByStatus() {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nSorted tasks by Status ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
		
		return taskDAO.sortTasksByStatus();
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#sortTasksByStatus(User user)
	 */
	@Override
	public List<Task> sortTasksByStatus(User user){
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nSorted tasks by Status ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	
		List<Task> tempTasksList = getAllTasksOfUser(user);
		tempTasksList.sort(new TaskComparatorByName());
		return tempTasksList;
	}

	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#sortTasksByDateOdCompletion()
	 */
	@Override
	public List<Task> sortTasksByDateOdCompletion() {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nSorted tasks by Date of Completion ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	
		return taskDAO.sortTasksByDateOfCompletion();
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#sortTasksByDateOdCompletion(User user)
	 */
	@Override
	public List<Task> sortTasksByDateOdCompletion (User user) {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nSorted tasks by Date of Completion ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
		
		List<Task> tempTasksList = getAllTasksOfUser(user);
		tempTasksList.sort(new TaskComparatorByDateOfCompletion());
		return tempTasksList;
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#getAllTasks()
	 */
	@Override
	public List<Task> getAllTasks(){
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nFound all tasks ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
		
		return taskDAO.getAllTasks();
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.TaskServiceAPI#getAllTasksOfUser()
	 */
	@Override	
	public List<Task> getAllTasksOfUser(User user) {
		List<Task> tempTasksList = taskDAO.getAllTasks();
		tempTasksList.stream().filter(task -> (task.getUser().getLogin().equals(user.getLogin()))).collect(Collectors.toList());
		return tempTasksList;
	}
}
