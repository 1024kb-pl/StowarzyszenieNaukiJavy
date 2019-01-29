package pl.kostrzej.simpleToDoApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrzej.simpleToDoApp.model.User;
import pl.kostrzej.simpleToDoApp.repository.UserRepository;

import java.util.Scanner;

@Service
public class LoginService {

    private Scanner scanner;
    private UserRepository userRepository;

    @Autowired
    public LoginService(Scanner scanner, UserRepository userRepository) {
        this.scanner = scanner;
        this.userRepository = userRepository;
    }

    public User logIn(){
        String login, password;
        do {
            System.out.println("Podaj login:");
            login = scanner.nextLine();
        } while (isLoginEmpty(login));
        System.out.println("Podaj hasło:");
        password = scanner.nextLine();
        User user = userRepository.findByLogin(login);
        if (user != null && login.equals(user.getLogin()) && password.equals(user.getPassword())) return user;
        else{
            System.out.println("Niepoprawne dane logowania");
            return null;
        }
    }
    private boolean isLoginEmpty(String value){
        if (value == null || value.equals("")){
            System.out.println("Pole \"Login\" nie może być puste!");
            return true;
        } else return false;
    }
}
