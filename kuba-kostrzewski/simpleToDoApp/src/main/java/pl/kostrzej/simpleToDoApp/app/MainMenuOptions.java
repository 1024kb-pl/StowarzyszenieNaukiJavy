package pl.kostrzej.simpleToDoApp.app;

public enum MainMenuOptions {

    SHOW_ALL_TASKS(1, "Pokaż listę wszystkich zadań"),
    ADD_NEW_TASK(2, "Dodaj nowe zadanie"),
    EXIT(3, "Koniec");

    private int number;
    private String name;

    MainMenuOptions(int number, String name){
        this.name = name;
        this.number = number;
    }
    static MainMenuOptions returnIfCorrect(int number){
        if (number<1 || number>values().length)
            throw new InvalidOptionException();
        return values()[number-1];
    }

    @Override
    public String toString() {
        return number + ". " + name;
    }
}
