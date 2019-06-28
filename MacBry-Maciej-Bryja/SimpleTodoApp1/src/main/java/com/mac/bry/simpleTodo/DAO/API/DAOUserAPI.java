package com.mac.bry.simpleTodo.DAO.API;

import java.util.List;
import java.util.Optional;

import com.mac.bry.simpleTodo.entity.Task;
import com.mac.bry.simpleTodo.entity.User;

public interface DAOUserAPI {

	void addUser(User user);

	void deleteUserByID(int id);

	void deleteUserByLogin(String login);

	void editUserLogin(User user, String newLogin);

	void editUserPassword(User user, String newPassword);

	Optional<User> findUserByID(int ID);

	Optional<User> findUserByLogin(String login);

	List<User> getAllUsers();

	boolean isLoginExists(String tempLogin);

	void addTaskToUser(User loggedUser, Task taskToAdd);

	boolean isEmailExist(String tempMail);

	void editEmail(User user, String newEmail);

}
