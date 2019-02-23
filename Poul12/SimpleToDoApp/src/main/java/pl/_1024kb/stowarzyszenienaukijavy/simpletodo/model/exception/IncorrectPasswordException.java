package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception;

public class IncorrectPasswordException extends Exception
{
    public IncorrectPasswordException() {
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
