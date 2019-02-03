package com.mac.bry.simpleTodo.Application;

import com.mac.bry.simpleTodo.appController.MainController;

public class Application {

	public static void main(String[] args) {
		MainController controller = new MainController();
		controller.programLoop();
	}

}
