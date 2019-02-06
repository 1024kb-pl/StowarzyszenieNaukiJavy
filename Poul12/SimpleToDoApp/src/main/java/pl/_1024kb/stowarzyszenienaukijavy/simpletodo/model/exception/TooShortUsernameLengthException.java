package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.exception;

public class TooShortUsernameLengthException extends Exception
{
    public TooShortUsernameLengthException() {
    }

    public TooShortUsernameLengthException(String message) {
        super(message);
    }
}
