package com.mac.bry.simpleTodo.utilitis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.mac.bry.simpleTodo.entity.Task;
import com.mac.bry.simpleTodo.utilitis.API.TaskReaderAPI;

public class TaskReader implements TaskReaderAPI {

	private Scanner scanner;

	public TaskReader() {
		super();
		this.scanner = new Scanner(System.in);
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.utilitis.TaskReaderAPI#closeScanner()
	 */
	@Override
	public void closeScanner() {
		this.scanner.close();
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.utilitis.TaskReaderAPI#readNumber()
	 */
	@Override
	public int readNumber () {
		while(true) {	
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
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.utilitis.TaskReaderAPI#readNumber(java.lang.String)
	 */
	@Override
	public int readNumber(String msg) {
		while(true) {
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
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.utilitis.TaskReaderAPI#readString()
	 */
	@Override
	public String readString() {
		String tempString = scanner.nextLine();
		return tempString;
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.utilitis.TaskReaderAPI#readString(java.lang.String)
	 */
	@Override
	public String readString(String msg) {
		System.out.println("Enter " + msg);
		String tempString = scanner.nextLine();
		return tempString;
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.utilitis.TaskReaderAPI#readAndCreateTask()
	 */
	@Override
	public Task readAndCreateTask() {
		
		System.out.println("Enter task Name: ");
		String tempTaskName = scanner.nextLine();
		System.out.println("Enter task Description: ");
		String tempTaskDescription = scanner.nextLine();
		LocalDate tempDate = readAndCreateDate();
		return new Task(tempTaskName, 
				tempTaskDescription, 
				false, 
				tempDate);
		
	}
	

	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.utilitis.TaskReaderAPI#readBoolean()
	 */
	@Override
	public boolean readBoolean(String msg) {
		while(true) {
			System.out.println("Enter " + msg);
			
			try {
				boolean tempAmswer = scanner.nextBoolean();
				System.out.println(tempAmswer);
				return tempAmswer;
			} catch (InputMismatchException e) {
				System.err.println("It's not a correct input\n");
				scanner.nextLine();
				continue;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mac.bry.simpleTodo.utilitis.TaskReaderAPI#readAndCreateDate()
	 */
	@Override
	public LocalDate readAndCreateDate() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		System.out.println("Enter date (d/mm/yyyy)");
		String tempTaskDateOfCompletion =  scanner.nextLine();
		return  LocalDate.parse(tempTaskDateOfCompletion,dateFormatter);
	}
	
	
	
	
}
