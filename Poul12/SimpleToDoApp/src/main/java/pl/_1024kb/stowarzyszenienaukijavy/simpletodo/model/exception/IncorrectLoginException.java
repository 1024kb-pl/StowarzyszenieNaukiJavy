package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception;

public class IncorrectLoginException extends Exception
{
    public IncorrectLoginException() {
    }

    public IncorrectLoginException(String message) {
        super(message);
    }
}
