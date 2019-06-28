package com.mac.bry.simpleTodo.Application;

import org.apache.log4j.BasicConfigurator;

import com.mac.bry.simpleTodo.appController.MainController;
import com.mac.bry.simpleTodo.configuration.Log4jConfigurator;

public class Application {

	public static void main(String[] args) {
		Log4jConfigurator.configure();
		
		MainController controller = new MainController();
		controller.start();
	}

}
