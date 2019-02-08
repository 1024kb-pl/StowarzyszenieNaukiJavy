package com.mac.bry.simpleTodo.appController;

import com.mac.bry.simpleTodo.Enums.MenuOption;
import com.mac.bry.simpleTodo.entity.User;
import com.mac.bry.simpleTodo.utilitis.DataReader;

public class MenuController {

	private DataReader dataReader;
	private TaskController taskController;
	private MainController mainController;
	private UserController userController;
	private User loggedUser;

	public MenuController() {
		this.dataReader = new DataReader();
		this.taskController = new TaskController();
		this.userController = new UserController();
	}

	public void menuLoop() {
		MenuOption option;
		printMenuOptions();

		while ((option = MenuOption.createFromInt(dataReader.readNumber())) != MenuOption.BACK) {
			switch (option) {
			case USER_MENU:
				System.out.println("user menu");
				break;

			case TASK_MENU:
				taskController.taskLoop();
				break;

			case PROJECT_MENU:
				System.out.println("project menu");
				break;

			default:
				System.err.println("\nNo such option");
				menuLoop();
				break;
			}
		}
		mainController.programLoop();
	}

	private void printMenuOptions() {
		System.out.println("Chose Option: ");
		for (MenuOption option : MenuOption.values()) {
			System.out.println(option);
		}
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
		
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
}
