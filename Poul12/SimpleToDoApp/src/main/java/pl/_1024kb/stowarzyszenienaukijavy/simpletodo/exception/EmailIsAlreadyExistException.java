package pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception;

public class EmailIsAlreadyExistException extends Exception
{
    public EmailIsAlreadyExistException() {
    }

    public EmailIsAlreadyExistException(String message) {
        super(message);
    }
}
