package com.mac.bry.simpleTodo.appController;

import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.log4j.Logger;

import com.mac.bry.simpleTodo.Enums.LoginOption;
import com.mac.bry.simpleTodo.Exception.LoginOptionNoExistException;
import com.mac.bry.simpleTodo.entity.User;
import com.mac.bry.simpleTodo.services.UserService;
import com.mac.bry.simpleTodo.utilitis.DataReader;
import com.mac.bry.simpleTodo.utilitis.EmailSender;
import com.mac.bry.simpleTodo.utilitis.PasswordUtillity;

public class MainController {

	private Logger logger = Logger.getLogger(MainController.class.getName());
	
	
	private DataReader dataReader;
	private UserService userService; 
	private MenuController menuController;
	private User loggedUser;

	public MainController() {
		super();
		this.dataReader = new DataReader();
		this.userService = new UserService();
		this.menuController = new MenuController();
	}

	public void start() {
		LoginOption option;
		printLoginOptions();

		try {
			while ((option = LoginOption.getOptionByOrderNumber(dataReader.readNumber())) != LoginOption.EXIT) {
				switch (option) {
				case LOGIN:
					if (login()) {
						menuController.menuLoop();
						
					} else {
						System.out.println("Try again");
						start();
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
					
					String name = new Object(){}.getClass().getEnclosingMethod().getName();
					logger.warn("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + name + " No such option");
					
					start();
					break;
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoginOptionNoExistException e) {
			String name = new Object(){}.getClass().getEnclosingMethod().getName();
			logger.error("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + name + " No such option");
			e.printStackTrace();
		}
	}

	private void resetPassword() {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() + "] ---> Run " + name);
		
		String newPassword = dataReader.generateRandomUUID();
		String tempMail = dataReader.readString(" your email");
		if (userService.isEmailExists(tempMail)) {
			EmailSender.configureEmail(tempMail, newPassword);
			newPassword = PasswordUtillity.getHashedPassword(newPassword);
			userService.editUserPassword(userService.findUserByLogin(dataReader.readString("Login")), newPassword);
		} else {
			System.err.println("No such email!");
			start();
		}
	}

	private void printLoginOptions() {
		System.out.println("Chose Option: ");
		for (LoginOption option : LoginOption.values()) {
			System.out.println(option);
		}
	}

	private boolean login() {
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		logger.info("[" + LocalDate.now().toString() +" "+ LocalTime.now().toString() + "] ---> Run " + name);
		
		User tempUser = dataReader.readUser();
		this.loggedUser = userService.findUserByLogin(tempUser.getLogin());
		menuController.setLoggedUser(this.loggedUser);
		menuController.setMainController(this);
		return userService.isUserExistsByLoginAndPassword(tempUser.getLogin(), tempUser.getPassword());
	}

	private void registr() {
		userService.addUser(dataReader.readAndCreateUser());
		start();
	}

}
