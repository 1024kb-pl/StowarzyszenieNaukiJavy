package com.mac.bry.simpleTodo.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mac.bry.simpleTodo.DAO.API.DAOUserAPI;
import com.mac.bry.simpleTodo.entity.Task;
import com.mac.bry.simpleTodo.entity.User;

public class UserDAO implements DAOUserAPI {
	
	private SessionFactory factory;
	
	public UserDAO() {
		super();
		this.factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Task.class)
				.buildSessionFactory();
	}
	
	@Override
	public void addUser(User user) {
		List<User> userList;
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		userList = session.createQuery("from User user where user.login='" + user.getLogin() + "'").getResultList();
		if(userList.isEmpty()) {
			session.save(user);
			session.getTransaction().commit();
			session.close();
		}
		else {
			System.out.println("Incorrect login");	
			session.getTransaction().commit();
			session.close();
		}
		
	}
	
	@Override
	public void deleteUserByID(int id) {
		User userToDelete = findUserByID(id);
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.delete(userToDelete);
		session.getTransaction().commit();
		session.close();
	}
	
	@Override
	public void deleteUserByLogin(String login) {
		User userToDelete = findUserByLogin(login);
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.delete(userToDelete);
		session.getTransaction().commit();
	}

	@Override
	public void editUserLogin(User user, String newLogin) {
		String oldLogin = user.getLogin();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		user.setLogin(newLogin);
		session.getTransaction().commit();
		System.out.println("You have changed login from " + oldLogin + " to " + user.getLogin());
	}

	@Override
	public void editUserPassword(User user, String newPassword) {
		String oldPassword = user.getPassword();
		Session session = factory.getCurrentSession();
		//session.beginTransaction();
		user.setPassword(newPassword);
		session.getTransaction().commit();
		System.out.println("You have changed password from " + oldPassword + " to " + user.getPassword());
		
	}
	
	@Override
	public User findUserByID(int ID) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		User tempUser = session.get(User.class, ID);
		session.getTransaction().commit();
		return tempUser;
	}

	@Override
	public User findUserByLogin(String login) {
		User tempUser;
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		tempUser = (User)session.createQuery("from User user where user.login='" + login + "'").getSingleResult();
		return tempUser;
	}

	@Override
	public List<User> getAllUsers() {
		List <User> usersList;
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		usersList = session.createQuery("from User").getResultList();
		session.getTransaction().commit();
		session.close();
		return usersList;
	}

	@Override
	public boolean isLoginExists(String tempLogin) {
		List <User> userList;
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		userList = session.createQuery("from User user where user.login='" + tempLogin + "'").getResultList();
		if(userList.isEmpty()) {
			return false;
		}
		else return true;
	}

	@Override
	public boolean passwordCheck(User user, String tempPassword) {
		return user.getPassword().equals(tempPassword); 
	}


	@Override
	public boolean logingCheck(User user) {
		List<User> userList;
		Session session = factory.getCurrentSession();
		//session.beginTransaction();
		userList = session.createQuery("from User user where user.login='" + user.getLogin() + "' and user.password='" + user.getPassword() +"'").getResultList();
		session.getTransaction().commit();
		//session.close();
		if(userList.isEmpty()) {
			return false;
		}
		return true ;
	}

	@Override
	public boolean login(User user) {
		
		if(logingCheck(user)) {
			System.out.println("Login correct!");
			if(passwordCheck(findUserByLogin(user.getLogin()), user.getPassword())){
				System.out.println("Password correct!");
				return true;
			}
			else {
				System.out.println("Password incorrect!");
				return false;
			}
		}
		else {
			System.out.println("Login incorrect");
			return false;
		}
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
		List<User> tempUserList = session.createQuery("from User user where user.password='" + tempMail + "'").getResultList();
		if(tempUserList.isEmpty()) {
			return false;
		}
		return true;
	}
	
	@Override
	public void editEmail(User user, String newEmail) {
		String oldEmail = user.getEmailAdress();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		user.setEmailAdress(newEmail);
		session.getTransaction().commit();
		System.out.println("You have changed email adress from " + oldEmail + " to " + user.getEmailAdress());
	}
	
	
}
