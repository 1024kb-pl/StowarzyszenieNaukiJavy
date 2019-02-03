package com.mac.bry.simpleTodo.utilitis.API;

import com.mac.bry.simpleTodo.entity.User;

public interface DataReaderAPI {

	public void closeScanner();

	public int readNumber();

	public int readNumber(String msg);

	public String readString();

	public String readString(String msg);

	public User readUser();

	public User readAndCreateUser();

}