package pl.kostrzej.simpleToDoApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kostrzej.simpleToDoApp.model.User;
import pl.kostrzej.simpleToDoApp.repository.UserRepository;

import java.util.Scanner;

@Service
public class UserService {

    UserRepository userRepository;
    Scanner scanner;

    @Autowired
    public UserService(UserRepository userRepository, Scanner scanner) {
        this.userRepository = userRepository;
        this.scanner = scanner;
    }

    public void addUser(){
        User user = new User();
        String login, password, question, answer;
        do {
            System.out.println("Podaj nazwę użytkownika:");
            login = scanner.nextLine();
        } while (checkLogin(login));
        user.setLogin(login);
        do {
            System.out.println("Podaj hasło:");
            password = scanner.nextLine();
        } while (checkPassword(password));
        user.setPassword(password);
        System.out.println("Podaj e-mail lub zostaw puste:");
        user.setEmail(scanner.nextLine());
        do {
            System.out.println("Podaj pytanie pomocnicze:");
            question = scanner.nextLine();
        } while (isFieldEmpty(question, "Pytanie pomocnicze"));
        user.setQuestion(question);
        do {
            System.out.println("Podaj odpowiedź:");
            answer = scanner.nextLine();
        } while (isFieldEmpty(answer, "Odpowiedź"));
        user.setAnswer(answer);
        userRepository.save(user);
        System.out.println("Użytkownik został poprawnie zarejestrowany\n" +
                "Możesz się teraz zalogować");
    }
    private boolean checkPassword(String password){
        if (isFieldEmpty(password, "Hasło")){
            return true;
        }
        System.out.println("Powtórz hasło:");
        if (!password.equals(scanner.nextLine())){
            System.out.println("Podane hasła są różne!");
            return true;
        } else return false;
    }
    private boolean checkLogin(String login) {
        if (isFieldEmpty(login, "Login")) {
            return true;
        } else if (userRepository.existsByLogin(login)) {
            System.out.println("Podany login już istnieje!");
            return true;
        } else return false;
    }
    private boolean isFieldEmpty(String value, String name){
        if (value == null || value.equals("")){
            System.out.println("Pole \"" + name + "\" nie może być puste!");
            return true;
        } else return false;
    }
}
