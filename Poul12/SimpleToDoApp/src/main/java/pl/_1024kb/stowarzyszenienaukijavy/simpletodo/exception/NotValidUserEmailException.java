package pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception;

public class NotValidUserEmailException extends Exception {
    public NotValidUserEmailException() {
    }

    public NotValidUserEmailException(String message) {
        super(message);
    }
}
