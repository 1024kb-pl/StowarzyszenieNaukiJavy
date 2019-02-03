package com.mac.bry.simpleTodo.appController;

import java.nio.charset.Charset;
import java.util.Random;

import com.mac.bry.simpleTodo.DAO.UserDAO;
import com.mac.bry.simpleTodo.Enums.LoginOption;
import com.mac.bry.simpleTodo.entity.User;
import com.mac.bry.simpleTodo.utilitis.DataReader;
import com.mac.bry.simpleTodo.utilitis.EmailSender;
import com.mac.bry.simpleTodo.utilitis.PasswordUtillity;

public class MainController {

	private DataReader dataReader;
	private UserDAO userDAO;
	private TaskController taskController;
	private User loggedUser;

	public MainController() {
		super();
		this.dataReader = new DataReader();
		this.userDAO = new UserDAO();
		this.taskController = new TaskController();
	}

	public void programLoop() {
		LoginOption option;
		printLoginOptions();

		while ((option = LoginOption.createFromInt(dataReader.readNumber())) != LoginOption.EXIT) {
			switch (option) {
			case LOGIN:
				if(login()) {;
					taskController.taskLoop();
				}
				else {
					System.out.println("Try again");
					login();
				}
				break;
				
			case REGISTR:
				registr();
				break;
				
			case PASSWORD_RESET:
				resetPassword();
				break;
				
			default:
				System.err.println("\nNo such option");
				programLoop();
				break;
			}
		}
	}

	private void resetPassword() {
		
	    String newPassword = dataReader.generateRandomString();
		String tempMail =  dataReader.readString(" your email");
		EmailSender.configureEmail(tempMail, newPassword);
		newPassword = PasswordUtillity.getHashedPassword(newPassword);
		userDAO.editUserPassword(userDAO.findUserByLogin(dataReader.readString("Login")), newPassword);
	}

	private void printLoginOptions() {
		System.out.println("Chose Option: ");
		for (LoginOption option : LoginOption.values()) {
			System.out.println(option);
		}
	}
	
	private boolean login () {
		User tempUser = dataReader.readUser();
		this.loggedUser = userDAO.findUserByLogin(tempUser.getLogin());
		taskController.setLoggedUser(this.loggedUser);
		return userDAO.login(tempUser);
	}
	
	private void registr () {
		userDAO.addUser(dataReader.readAndCreateUser());
		programLoop();
	}
	
	
}
