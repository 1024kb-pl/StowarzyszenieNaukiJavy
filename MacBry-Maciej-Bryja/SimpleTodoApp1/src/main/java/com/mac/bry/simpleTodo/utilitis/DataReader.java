package com.mac.bry.simpleTodo.utilitis;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import com.mac.bry.simpleTodo.entity.User;
import com.mac.bry.simpleTodo.utilitis.API.DataReaderAPI;

public class DataReader implements DataReaderAPI {

	private Scanner scanner;

	public DataReader() {
		super();
		this.scanner = new Scanner(System.in);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mac.bry.simpleTodo.utilitis.DataReaderAPI#CloseScanner()
	 */
	@Override
	public void closeScanner() {
		this.scanner.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mac.bry.simpleTodo.utilitis.DataReaderAPI#ReadNumber()
	 */
	@Override
	public int readNumber() {
		while (true) {
			try {
				int tempNumber = scanner.nextInt();
				scanner.nextLine();
				return tempNumber;
			} catch (InputMismatchException e) {
				System.err.println("It's not a number\n");
				scanner.nextLine();
				continue;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mac.bry.simpleTodo.utilitis.DataReaderAPI#ReadNumber(java.lang.String)
	 */
	@Override
	public int readNumber(String msg) {
		while (true) {
			System.out.println("Enter " + msg);

			try {
				int tempNumber = scanner.nextInt();
				scanner.nextLine();
				return tempNumber;
			} catch (InputMismatchException e) {
				System.err.println("It's not a number\n");
				scanner.nextLine();
				continue;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mac.bry.simpleTodo.utilitis.DataReaderAPI#ReadString()
	 */
	@Override
	public String readString() {
		return scanner.nextLine();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mac.bry.simpleTodo.utilitis.DataReaderAPI#ReadString(java.lang.String)
	 */
	@Override
	public String readString(String msg) {
		System.out.println("Enter " + msg);
		return scanner.nextLine();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mac.bry.simpleTodo.utilitis.DataReaderAPI#ReadUser()
	 */
	@Override
	public User readUser() {
		System.out.println("Enter login: ");
		String tempLogin = scanner.nextLine();
		System.out.println("Enter password: ");
		String tempPassword = scanner.nextLine();
		tempPassword = PasswordUtillity.getHashedPassword(tempPassword);
		User tempUser = new User(tempLogin, tempPassword);
		return tempUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mac.bry.simpleTodo.utilitis.DataReaderAPI#ReadAndCreateUser()
	 */
	@Override
	public User readAndCreateUser() {
		System.out.println("Enter login: ");
		String tempLogin = scanner.nextLine();
		System.out.println("Enter password: ");
		String tempPassword = scanner.nextLine();
		tempPassword = PasswordUtillity.getHashedPassword(tempPassword);
		// System.out.println(tempPassword);
		System.out.println("Confirm password: ");
		String tempConfirmPassword = scanner.nextLine();
		tempConfirmPassword = PasswordUtillity.getHashedPassword(tempConfirmPassword);
		// System.out.println(tempConfirmPassword);
		System.out.println("Enter email adress:");
		String tempEmailAdress = scanner.nextLine();

		if (!tempPassword.equals(tempConfirmPassword)) {
			System.out.println("Incorect password!");
			readAndCreateUser();
		}
		return new User(tempLogin, tempPassword, tempEmailAdress);
	}

	public String generateRandomUUID() {

		return UUID.randomUUID().toString();
		
		// String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		// StringBuilder salt = new StringBuilder();
		// Random rnd = new Random();
		// while (salt.length() < 18) {
		// int index = (int) (rnd.nextFloat() * SALTCHARS.length());
		// salt.append(SALTCHARS.charAt(index));
		// }
		// String saltStr = salt.toString();
		// return saltStr;
	}

}
