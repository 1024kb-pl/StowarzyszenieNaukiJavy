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

import com.mac.bry.simpleTodo.DAO.API.DAOUserAPI;
import com.mac.bry.simpleTodo.appController.TaskController;
import com.mac.bry.simpleTodo.entity.Task;
import com.mac.bry.simpleTodo.entity.User;

public class UserDAO implements DAOUserAPI {

	private SessionFactory factory;
	private Logger logger = Logger.getLogger(TaskController.class.getName());

	public UserDAO() {
		super();
		this.factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.addAnnotatedClass(Task.class).buildSessionFactory();
	}

	@Override
	public void addUser(User user) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteUserByID(int id) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.createQuery("delete User user where user.userID='" + id + "'").executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void deleteUserByLogin(String login) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.createQuery("delete User user where user.login='" + login + "'").executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void editUserLogin(User user, String newLogin) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		user.setLogin(newLogin);
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void editUserPassword(User user, String newPassword) {
		Session session = factory.getCurrentSession();
		user.setPassword(newPassword);
		session.update(user);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Optional<User> findUserByID(int ID) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		try {
			User tempUser = session.get(User.class, ID);
			session.getTransaction().commit();
			session.close();
			return Optional.of(tempUser);
			
		} catch (NoResultException e) {
			session.getTransaction().commit();
			session.close();
			return Optional.empty();
		}
	}

	@Override
	public Optional<User> findUserByLogin(String login) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		try {
			User tempUser = (User) session.createQuery("from User user where user.login='" + login + "'").getSingleResult();
			session.getTransaction().commit();
			session.close();
			return Optional.of(tempUser);
			
		} catch (NoResultException e ) {
			session.getTransaction().commit();
			session.close();
			return Optional.empty();
		}
	}

	@Override
	public List<User> getAllUsers() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<User> usersList = session.createQuery("from User").getResultList();
		session.getTransaction().commit();
		session.close();
		return usersList;
	}

	@Override
	public boolean isLoginExists(String tempLogin) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<User> usersList = session.createQuery("from User user where user.login='" + tempLogin + "'")
				.getResultList();
		if (usersList.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public void addTaskToUser(User loggedUser, Task taskToAdd) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		loggedUser.add(taskToAdd);
		session.save(taskToAdd);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public boolean isEmailExist(String tempMail) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<User> tempUserList = session.createQuery("from User user where user.password='" + tempMail + "'")
				.getResultList();
		return !tempUserList.isEmpty();
	}

	@Override
	public void editEmail(User user, String newEmail) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		user.setEmailAdress(newEmail);
		session.update(user);
		session.getTransaction().commit();

	}

}
