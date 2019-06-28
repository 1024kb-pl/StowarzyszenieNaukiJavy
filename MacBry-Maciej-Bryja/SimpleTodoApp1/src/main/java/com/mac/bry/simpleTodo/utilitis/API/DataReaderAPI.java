package com.mac.bry.simpleTodo.utilitis.API;

import com.mac.bry.simpleTodo.entity.User;

public interface DataReaderAPI {

	void closeScanner();

	int readNumber();

	int readNumber(String msg);

	String readString();

	String readString(String msg);

	User readUser();

	User readAndCreateUser();

}