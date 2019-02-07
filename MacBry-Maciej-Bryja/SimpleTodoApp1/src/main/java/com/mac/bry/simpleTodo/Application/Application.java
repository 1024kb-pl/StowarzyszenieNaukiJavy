package com.mac.bry.simpleTodo.Application;

import org.apache.log4j.BasicConfigurator;

import com.mac.bry.simpleTodo.appController.MainController;

public class Application {

	public static void main(String[] args) {
		//for Log4j run
		BasicConfigurator.configure();
		
		
		MainController controller = new MainController();
		controller.programLoop();
	}

}
