package com.mac.bry.simpleTodo.Enums;

public enum AdminOption {
	
	SHOW_ALL_USERS(0, "Show all Users info"),
	EDIT_PERMISION(1, "Edit permision"),
	EDIT_NUMBER_OF_PROJECTS(2, "Edit the number of projects"),
	DELETE (3, "Delete account"),
	BACK (4, "Back");
	
	private int value;
	private String description;
	
	
	private AdminOption (int value, String description) {
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
	
	public static AdminOption createFromInt(int option) {
		return AdminOption.values()[option];
	}
}
