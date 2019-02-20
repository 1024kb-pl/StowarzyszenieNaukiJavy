package com.mac.bry.simpleTodo.printProcessors;

import java.util.List;

import com.mac.bry.simpleTodo.entity.User;

public class UserPrintProcessor {

	private static void printHorizontalBorder() {
		for (int i = 0; i < 170; i++) {
			System.out.print("=");
		}
		System.out.println();
	}

	private static void printColumnName() {
		System.out.printf("|| %-10s || %-10s || %-20s || %-32s || %-30s || %-20s ||", "LP", "USER ID", "LOGIN",
				"USER PASSWORD", "EMAIL ADRESS", "PERMISION");
		System.out.println();
	}
	
	private static String permisionToString(boolean permision) {
		if ( permision) {
			return "Administrator";
		}
		else return "User";
	}

	public static void printUser(List<User> users) {
		printHorizontalBorder();
		printColumnName();
		printHorizontalBorder();

		int lp = 1;

		for (User user : users) {
			System.out.printf("|| %-10d || %-10d || %-20s || %-35s || %-30s || %-20s ||", lp, user.getUserID(),
					user.getLogin(), user.getPassword(), user.getEmailAdress(), permisionToString(user.isPermision()));
			System.out.println();
		}
		printHorizontalBorder();
	}
	
	public static void printUser(User user) {
		printHorizontalBorder();
		printColumnName();
		printHorizontalBorder();
		
		System.out.printf("|| %-10d || %-10d || %-20s || %-20s || %-30s || %-20s ||", 1, user.getUserID(),
				user.getLogin(), user.getPassword(), user.getEmailAdress(), "Permision");
		System.out.println();
		
		printHorizontalBorder();
	}
	
}
