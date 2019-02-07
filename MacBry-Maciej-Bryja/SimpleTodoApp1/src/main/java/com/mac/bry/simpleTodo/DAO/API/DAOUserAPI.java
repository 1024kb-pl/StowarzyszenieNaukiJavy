package com.mac.bry.simpleTodo.DAO.API;

import java.util.List;

import com.mac.bry.simpleTodo.entity.Task;
import com.mac.bry.simpleTodo.entity.User;

public interface DAOUserAPI {
	
	public void addUser(User user);
	
	public void deleteUserByID(int id);
	public void deleteUserByLogin(String login);
	public void editUserLogin(User user, String newLogin);
	public void editUserPassword(User user, String newPassword);
	public User findUserByID(int ID);
	public User findUserByLogin(String login);
	public List<User> getAllUsers();
	public boolean isLoginExists(String tempLogin);
	public boolean passwordCheck(User user, String tempPassword);
	public boolean logingCheck(User user);
	public boolean login(User user);
	public void addTaskToUser(User loggedUser, Task taskToAdd);
	public boolean isEmailExist(String tempMail);
}
