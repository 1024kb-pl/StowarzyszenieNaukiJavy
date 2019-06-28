package com.mac.bry.simpleTodo.Enums;

import com.mac.bry.simpleTodo.Exception.UserMenuOptionNoExistException;

public enum UserMenuOption {
	
	SHOW(0,"Show User info"),
	EDIT_PASSWORD(1, "Edit password"),
	EDIT_MAIL(2, "Edit mail"),
	DELETE (3, "Delete account"),
	BACK(4, "Back");
	
	private int value;
	private String description;
	
	private UserMenuOption (int value, String description) {
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
	
	public static UserMenuOption getOptionByOrderNumber(int option) throws UserMenuOptionNoExistException {
		return UserMenuOption.values()[option];
	}
	
}
