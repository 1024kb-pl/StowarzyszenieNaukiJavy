package pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception;

public class TooShortPasswordLengthException extends Exception {
    public TooShortPasswordLengthException() {
    }

    public TooShortPasswordLengthException(String message) {
        super(message);
    }
}
