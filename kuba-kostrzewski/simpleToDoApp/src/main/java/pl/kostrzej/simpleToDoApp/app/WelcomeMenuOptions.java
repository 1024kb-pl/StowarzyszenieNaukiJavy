package pl.kostrzej.simpleToDoApp.app;

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
        if (number<1 || number>values().length)
            throw new InvalidOptionException();
        return values()[number-1];
    }

    @Override
    public String toString() {
        return number + ". " + name;
    }
}
