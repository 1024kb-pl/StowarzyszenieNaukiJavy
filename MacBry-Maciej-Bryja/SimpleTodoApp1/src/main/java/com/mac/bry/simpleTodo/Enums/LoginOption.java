package com.mac.bry.simpleTodo.Enums;

import com.mac.bry.simpleTodo.Exception.LoginOptionNoExistException;

public enum LoginOption {

	LOGIN(0, "Login"),
	REGISTR(1, "Registr"),
	PASSWORD_RESET(2, "Reset password"),
	EXIT(3, "Exit Program");
	
	private int value;
	private String description;
	
	private LoginOption(int value, String description) {
		this.value = value;
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return value + " - " + description;
	}
	
	public static LoginOption getOptionByOrderNumber(int option) throws LoginOptionNoExistException {
		return LoginOption.values()[option];
	}
}
