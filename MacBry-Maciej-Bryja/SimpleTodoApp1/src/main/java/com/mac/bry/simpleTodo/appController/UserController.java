package com.mac.bry.simpleTodo.appController;

import com.mac.bry.simpleTodo.Enums.AdminOption;
import com.mac.bry.simpleTodo.Enums.UserMenuOption;
import com.mac.bry.simpleTodo.Exception.UserMenuOptionNoExistException;
import com.mac.bry.simpleTodo.entity.User;
import com.mac.bry.simpleTodo.printProcessors.UserPrintProcessor;
import com.mac.bry.simpleTodo.services.UserService;
import com.mac.bry.simpleTodo.utilitis.DataReader;
import com.mac.bry.simpleTodo.utilitis.PasswordUtillity;

public class UserController {

	private User loggedUser;
	private MenuController menuController;
	private DataReader dataReader;
	private UserService userService;

	public UserController() {
		this.dataReader = new DataReader();
		this.userService = new UserService();
	}

	public void userLoop() {
		UserMenuOption option;
		printUserOptions();

		try {
			while ((option = UserMenuOption.getOptionByOrderNumber(dataReader.readNumber())) != UserMenuOption.BACK) {
				switch (option) {
				case SHOW:
					show();
					break;

				case EDIT_PASSWORD:
					editPassword();
					break;

				case EDIT_MAIL:
					editMail();
					break;
					
				case DELETE:
					deleteAccount();
					break;

				default:
					System.err.println("\nNo such option");
					userLoop();
					break;
				}
			}
		} catch (UserMenuOptionNoExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menuController.menuLoop();
	}

	public void adminLoop() {
		AdminOption option;
		printAdminOptions();

		while ((option = AdminOption.createFromInt(dataReader.readNumber())) != AdminOption.BACK) {
			switch (option) {
			case SHOW_ALL_USERS:
				System.out.println("Show all users");
				break;

			case EDIT_PERMISION:
				System.out.println("Edit permision");
				break;

			case EDIT_NUMBER_OF_PROJECTS:
				System.out.println("Edit number of proj.");
				break;

			case DELETE:
				System.out.println("Delete");
				break;

			default:
				System.err.println("\nNo such option");
				adminLoop();
				break;
			}
		}
		menuController.menuLoop();
	}

	private void printAdminOptions() {
		System.out.println("Chose Option: ");
		for (AdminOption option : AdminOption.values()) {
			System.out.println(option);
		}
	}

	private void deleteAccount() {
		userService.deleteUserByLogin(loggedUser.getLogin());
		
	}

	private void editMail() {
		String newEmail = dataReader.readString("new email");
		System.out.println(loggedUser);
		userService.editEmail(this.loggedUser, newEmail);
		userLoop();
	}

	private void printUserOptions() {
		System.out.println("Chose Option: ");
		for (UserMenuOption option : UserMenuOption.values()) {
			System.out.println(option);
		}
	}

	private void show() {
		UserPrintProcessor.printUser(loggedUser);
		userLoop();
	}

	private void editPassword() {
		String newPassword = dataReader.readString("new password");
		newPassword = PasswordUtillity.getHashedPassword(newPassword);
		userService.editUserPassword(loggedUser, newPassword);
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public void setMenuController(MenuController menuController) {
		this.menuController = menuController;
	}

}
