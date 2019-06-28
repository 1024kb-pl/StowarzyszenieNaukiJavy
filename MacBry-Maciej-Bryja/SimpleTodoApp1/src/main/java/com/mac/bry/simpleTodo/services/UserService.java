package com.mac.bry.simpleTodo.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.log4j.Logger;

import com.mac.bry.simpleTodo.DAO.UserDAO;
import com.mac.bry.simpleTodo.entity.User;
import com.mac.bry.simpleTodo.services.API.UserServiceAPI;

public class UserService implements UserServiceAPI {

	private UserDAO userDAO;
	private Logger logger = Logger.getLogger(UserService.class.getName());
	
	public UserService () {
		this.userDAO = new UserDAO();
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.UserServiceAPI#addUser(com.mac.bry.simpleTodo.entity.User)
	 */
	@Override
	public void addUser(User user) {
		userDAO.addUser(user);
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nAdded new user  ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.UserServiceAPI#deleteUserByID(int)
	 */
	@Override
	public void deleteUserByID (int ID) {
		userDAO.deleteUserByID(ID);
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nDeleted user where id was  " + ID;
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.UserServiceAPI#deleteUserByLogin(java.lang.String)
	 */
	@Override
	public  void deleteUserByLogin(String login) {
		userDAO.deleteUserByLogin(login);
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nDelete user where login  " + login;
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.UserServiceAPI#editUserLogin(com.mac.bry.simpleTodo.entity.User, java.lang.String)
	 */
	@Override
	public void editUserLogin(User user, String newLogin) {
		String oldLogin = user.getLogin();
		userDAO.editUserLogin(user, newLogin);
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nChange user login from " + oldLogin +" to " +user.getLogin();
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.UserServiceAPI#editUserPassword(com.mac.bry.simpleTodo.entity.User, java.lang.String)
	 */
	@Override
	public void editUserPassword(User user, String newPassword) {
		String oldPassword = user.getPassword();
		userDAO.editUserPassword(user, newPassword);
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nChanged user password from " + oldPassword +" to " +user.getPassword();
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.UserServiceAPI#editEmail(com.mac.bry.simpleTodo.entity.User, java.lang.String)
	 */
	@Override
	public void editEmail(User user, String newEmail) {
		String oldEmail = user.getEmailAdress();
		System.out.println(oldEmail);
		userDAO.editEmail(user, newEmail);
		
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nChanged user email from " + oldEmail +" to " +user.getEmailAdress();
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.UserServiceAPI#findUserByID(int)
	 */
	@Override
	public User findUserByID(int ID) {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nFound user ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
		
		return userDAO.findUserByID(ID).get();
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.UserServiceAPI#findUserByLogin(java.lang.String)
	 */
	@Override
	public User findUserByLogin(String login) {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nFound user ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
		
		return userDAO.findUserByLogin(login).orElse(User.DEFAULT_USER);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.UserServiceAPI#getAllUsers()
	 */
	@Override
	public List<User> getAllUsers(){
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nFound users ";
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
		
		return userDAO.getAllUsers();
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.UserServiceAPI#isLoginExists(java.lang.String)
	 */
	@Override
	public boolean isLoginExists(String tempLogin) {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method");
		
		List<User> usersList = (List<User>)findUserByLogin(tempLogin);
		return !usersList.isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.UserServiceAPI#isLoginExists(com.mac.bry.simpleTodo.entity.User)
	 */
	@Override
	public boolean isLoginExists(User user) {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method");
		
		List<User> usersList = (List<User>)findUserByLogin(user.getLogin());
		return !usersList.isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.UserServiceAPI#isEmailExists(java.lang.String)
	 */
	@Override
	public boolean isEmailExists(String tempMail) {
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method");
		
		return isEmailExists(tempMail);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.services.UserServiceAPI#isUserExistsByLoginAndPassword(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isUserExistsByLoginAndPassword(String login, String password) {
		User tempUser = findUserByLogin(login);
		if(tempUser.getPassword().equals(password)) {
			String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
			String msg = "\nFound user ";
			logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
			
			return true;
		}
		String methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		String msg = "\nUser not found ";
		logger.warn("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + methodName + "() method" + msg);
		
		return false;
	}
	
}
