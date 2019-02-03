package com.mac.bry.simpleTodo.DAO;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mac.bry.simpleTodo.DAO.API.DAOTaskAPI;
import com.mac.bry.simpleTodo.entity.Task;
import com.mac.bry.simpleTodo.entity.User;
import com.mac.bry.simpleTodo.entity.comparators.TaskComparatorByDateOfCompletion;
import com.mac.bry.simpleTodo.entity.comparators.TaskComparatorByName;
import com.mac.bry.simpleTodo.entity.comparators.TaskComparatorByStatus;

public class TaskDAO implements DAOTaskAPI {

	private SessionFactory factory;
	
	public TaskDAO() {
		super();
		this.factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Task.class)
				.addAnnotatedClass(User.class)
				.buildSessionFactory();
	}
	
	@Override
	public void addTask(Task task) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(task);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void editTaskName(Task task, String newTaskName) {
		String oldTaskName = task.getTaskName();
		Session session = factory.getCurrentSession();
		task.setTaskName(newTaskName);
		session.getTransaction().commit();
		System.out.println("You have changed task name from " + oldTaskName + " to " + task.getTaskName());
	}

	@Override
	public Task selectTaskByID(int taskID) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Task tempTask = session.get(Task.class, taskID);
		return tempTask;
	}

	@Override
	public void deleteTaskByID(int taskID) {
		Task taskToDelete = selectTaskByID(taskID);
		Session session = factory.getCurrentSession();
		session.delete(taskToDelete);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void changeTaskStatus(Task task) {
		boolean oldTaskStatus = task.isTaskStatus(); 
		Session session = factory.getCurrentSession();
		task.setTaskStatus(!oldTaskStatus);
		session.getTransaction().commit();
		System.out.println("You have changed task status from " + oldTaskStatus + " to " + task.isTaskStatus());
	}

	@Override
	public List<Task> selectTasksByStatus(boolean status) {
		int option;
		if(status==true)
			option = 1;
		else option = 0;
		List<Task> taskList;
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		taskList = session.createQuery("from Task task where task.taskStatus='" + option + "'").getResultList();
		session.getTransaction().commit();
		session.close();
		return taskList;
	}

	@Override
	public List<Task> selectTasksByDataOfCompletion(LocalDate dateOfCompletion) {
		List<Task> taskList;
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		taskList = session.createQuery("from Task task where task.dateOfCompletion='" + dateOfCompletion + "'").getResultList();
		session.getTransaction().commit();
		System.out.println("Test1");
		session.close();
		return taskList;
	}

	@Override
	public List<Task> sortTasksByName() {
		List <Task> tasksListToSort = getAllTasks();
		tasksListToSort.sort(new TaskComparatorByName());
		return tasksListToSort;
	}

	@Override
	public List<Task> sortTasksByStatus() {
		List <Task> tasksListToSort = getAllTasks();
		tasksListToSort.sort(new TaskComparatorByStatus());;
		return tasksListToSort;
	}

	@Override
	public List<Task> sortTasksByDateOfCompletion() {
		List <Task> tasksListToSort = getAllTasks();
		tasksListToSort.sort(new TaskComparatorByDateOfCompletion());
		return null;
	}

	@Override
	public List<Task> getAllTasks() {
		List <Task> allTasks;
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		allTasks = session.createQuery("from Task task").getResultList();
		session.getTransaction().commit();
		session.close();
		return allTasks;
	}

}
