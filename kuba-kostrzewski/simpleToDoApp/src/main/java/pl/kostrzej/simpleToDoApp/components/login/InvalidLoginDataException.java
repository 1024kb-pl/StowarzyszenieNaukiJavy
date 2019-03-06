package pl.kostrzej.simpleToDoApp.components.login;

public class InvalidLoginDataException extends RuntimeException{
    InvalidLoginDataException() {
        super("Niepoprawne dane logowania!");
    }
}
