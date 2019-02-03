package pl.kostrzej.simpleToDoApp.app;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public enum MainMenuOptions {

    SHOW_ALL_TASKS(1, "Pokaż listę wszystkich zadań"),
    ADD_NEW_TASK(2, "Dodaj nowe zadanie"),
    DELETE_TASK(3, "Usuń zadanie"),
    CHANGE_TASK_STATUS(4, "Zmień status zadania"),
    EXIT(5, "Koniec");

    private int number;
    private String name;

    MainMenuOptions(int number, String name){
        this.name = name;
        this.number = number;
    }
    static MainMenuOptions returnIfCorrect(int number){
        return Arrays.stream(values())
                .filter(value -> value.number == number)
                .findAny()
                .orElseThrow(() -> new InvalidOptionException());
    }

    @Override
    public String toString() {
        return number + ". " + name;
    }
}
