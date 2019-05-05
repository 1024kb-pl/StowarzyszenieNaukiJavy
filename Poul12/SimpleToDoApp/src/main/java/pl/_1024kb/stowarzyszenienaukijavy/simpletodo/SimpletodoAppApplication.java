package pl._1024kb.stowarzyszenienaukijavy.simpletodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@SpringBootApplication
public class SimpleToDoAppApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(SimpleToDoAppApplication.class, args);
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
