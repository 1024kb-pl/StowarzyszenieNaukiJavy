package com.mac.bry.simpleTodo.Enums;

import com.mac.bry.simpleTodo.Exception.MenuOptionNoExistException;

public enum MenuOption {
	USER_MENU(0, "User Menu"),
	TASK_MENU(1, "Task Menu"),
	PROJECT_MENU(2, "Project Menu"),
	BACK(3, "Back");

	private int value;
	private String description;
	
	private MenuOption (int value, String description) {
		this.value = value;
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return value + " - " + description;
	}
	
	public static MenuOption getOptionByOrderNumber(int option) throws MenuOptionNoExistException {
		return MenuOption.values()[option];
	}
	

}
