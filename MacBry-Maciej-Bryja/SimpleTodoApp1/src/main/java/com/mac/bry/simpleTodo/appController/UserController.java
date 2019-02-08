package com.mac.bry.simpleTodo.appController;

import com.mac.bry.simpleTodo.DAO.UserDAO;
import com.mac.bry.simpleTodo.Enums.UserMenuOption;
import com.mac.bry.simpleTodo.entity.User;
import com.mac.bry.simpleTodo.utilitis.DataReader;

public class UserController {

	private User loggedUser;
	private MenuController menuController;
	private DataReader dataReader;
	private UserDAO userDAO; 

	public UserController() {
		this.dataReader = new DataReader();
	}

	public void userLoop() {
		UserMenuOption option;
		printUserOptions();

		while ((option = UserMenuOption.createFromInt(dataReader.readNumber())) != UserMenuOption.BACK) {
			switch (option) {
			case SHOW:
				System.out.println("Show self data");
				show();
				break;

			case EDIT_PASSWORD:
				editPassword();
				break;

			case EDIT_MAIL:
				editMail();
				break;

			default:
				System.err.println("\nNo such option");
				userLoop();
				break;
			}
		}
		menuController.menuLoop();
	}

	private void editMail() {
		String newEmail = dataReader.readString("new email");
		userDAO.editEmail(loggedUser, newEmail);
	}

	private void printUserOptions() {
		System.out.println("Chose Option: ");
		for (UserMenuOption option : UserMenuOption.values()) {
			System.out.println(option);
		}
	}
	
	private void show() {
		System.out.println("UserPrintProcesor");
	}
	
	private void editPassword() {
		String newPassword = dataReader.readString("new password");
		userDAO.editUserPassword(loggedUser, newPassword);
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public void setMenuController(MenuController menuController) {
		this.menuController = menuController;
	}

}
