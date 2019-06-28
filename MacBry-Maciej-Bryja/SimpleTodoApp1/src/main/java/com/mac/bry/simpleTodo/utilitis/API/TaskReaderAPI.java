package com.mac.bry.simpleTodo.utilitis.API;

import java.time.LocalDate;

import com.mac.bry.simpleTodo.entity.Task;

public interface TaskReaderAPI {

	void closeScanner();

	int readNumber();

	int readNumber(String msg);

	String readString();

	String readString(String msg);

	Task readAndCreateTask();
	
	boolean readBoolean(String msg);
	
	LocalDate readAndCreateDate();

}