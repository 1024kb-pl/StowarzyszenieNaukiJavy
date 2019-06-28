package com.mac.bry.simpleTodo.services.API;

import java.util.List;

import com.mac.bry.simpleTodo.entity.User;

public interface UserServiceAPI {

	void addUser(User user);

	void deleteUserByID(int ID);

	void deleteUserByLogin(String login);

	void editUserLogin(User user, String newLogin);

	void editUserPassword(User user, String newPassword);

	void editEmail(User user, String newEmail);

	User findUserByID(int ID);

	User findUserByLogin(String login);

	List<User> getAllUsers();

	boolean isLoginExists(String tempLogin);

	boolean isLoginExists(User user);

	boolean isEmailExists(String tempMail);

	boolean isUserExistsByLoginAndPassword(String login, String password);

}