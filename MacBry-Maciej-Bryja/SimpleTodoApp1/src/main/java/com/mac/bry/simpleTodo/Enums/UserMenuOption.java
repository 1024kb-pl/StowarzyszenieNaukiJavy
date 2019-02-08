package com.mac.bry.simpleTodo.Enums;

public enum UserMenuOption {
	
	SHOW(0,"Show data"),
	EDIT_PASSWORD(1, "Edit password"),
	EDIT_MAIL(2, "Edit mail"),
	BACK(3, "Back");
	
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
	
	public static UserMenuOption createFromInt(int option) {
		return UserMenuOption.values()[option];
	}
	
}
