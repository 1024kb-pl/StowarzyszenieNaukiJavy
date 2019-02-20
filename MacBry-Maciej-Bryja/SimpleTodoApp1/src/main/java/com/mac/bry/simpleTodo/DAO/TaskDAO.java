package com.mac.bry.simpleTodo.DAO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mac.bry.simpleTodo.DAO.API.DAOTaskAPI;
import com.mac.bry.simpleTodo.appController.TaskController;
import com.mac.bry.simpleTodo.entity.Task;
import com.mac.bry.simpleTodo.entity.User;
import com.mac.bry.simpleTodo.entity.comparators.TaskComparatorByDateOfCompletion;
import com.mac.bry.simpleTodo.entity.comparators.TaskComparatorByName;
import com.mac.bry.simpleTodo.entity.comparators.TaskComparatorByStatus;

public class TaskDAO implements DAOTaskAPI {

	private SessionFactory factory;
	private Logger logger = Logger.getLogger(TaskController.class.getName());
	
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
		Session session = factory.getCurrentSession();
		task.setTaskName(newTaskName);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Optional <Task> selectTaskByID(int taskID) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		try {
			Task tempTask = session.get(Task.class, taskID);
			session.getTransaction().commit();
			session.close();
			return Optional.of(tempTask);
			
		} catch (NoResultException e) {
			session.getTransaction().commit();
			session.close();
			return Optional.empty();
		}
	}

	@Override
	public void deleteTaskByID(int taskID) {
		Task taskToDelete = selectTaskByID(taskID).get();
		Session session = factory.getCurrentSession();
		session.delete(taskToDelete);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void changeTaskStatus(Task task) {
		Session session = factory.getCurrentSession();
		task.setTaskStatus(!task.isTaskStatus());
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public List<Task> selectTasksByStatus(boolean status) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Task> taskList = session.createQuery("from Task task where task.taskStatus='" + status + "'").getResultList();
		session.getTransaction().commit();
		session.close();
		return taskList;
	}

	@Override
	public List<Task> selectTasksByDataOfCompletion(LocalDate dateOfCompletion) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Task> taskList = session.createQuery("from Task task where task.dateOfCompletion='" + dateOfCompletion + "'").getResultList();
		session.getTransaction().commit();
		session.close();
		return taskList;
	}

	@Override
	public List<Task> sortTasksByName() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Task> sortedTaskList = session.createQuery("from Task task order by task.taskName").getResultList();
		session.getTransaction().commit();
		session.close();
		return sortedTaskList;
	}
	

	@Override
	public List<Task> sortTasksByStatus() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Task> sortedTaskList = session.createQuery("from Task task order by task.taskStatus").getResultList();
		session.getTransaction().commit();
		session.close();
		return sortedTaskList;
	}

	@Override
	public List<Task> sortTasksByDateOfCompletion() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Task> sortedTaskList = session.createQuery("from Task task order by task.dateOfCompletion").getResultList();
		session.getTransaction().commit();
		session.close();
		return sortedTaskList;
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
