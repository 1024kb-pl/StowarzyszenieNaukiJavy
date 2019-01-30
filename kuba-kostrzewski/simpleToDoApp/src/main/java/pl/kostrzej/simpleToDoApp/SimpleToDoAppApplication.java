package pl.kostrzej.simpleToDoApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.kostrzej.simpleToDoApp.app.AppController;

import java.util.Scanner;

@SpringBootApplication
public class SimpleToDoAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SimpleToDoAppApplication.class, args);
		AppController controller = ctx.getBean(AppController.class);
		controller.run();
	}
	@Bean
	Scanner scanner(){
		return new Scanner(System.in);
	}
}

