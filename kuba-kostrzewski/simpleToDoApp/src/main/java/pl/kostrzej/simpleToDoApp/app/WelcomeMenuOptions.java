package pl.kostrzej.simpleToDoApp.app;

import java.util.Arrays;

public enum WelcomeMenuOptions{

    LOGIN(1, "Zaloguj się"),
    REGISTER(2, "Zarejestruj się"),
    EXIT(3, "Koniec");

    private int number;
    private String name;

    WelcomeMenuOptions(int number, String name) {
        this.number = number;
        this.name = name;
    }
    static WelcomeMenuOptions returnIfCorrect(int number){
        return Arrays.stream(values())
                .filter(value -> value.number == number)
                .findAny()
                .orElseThrow(() ->new InvalidOptionException());
    }

    @Override
    public String toString() {
        return number + ". " + name;
    }
}
