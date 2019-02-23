package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
