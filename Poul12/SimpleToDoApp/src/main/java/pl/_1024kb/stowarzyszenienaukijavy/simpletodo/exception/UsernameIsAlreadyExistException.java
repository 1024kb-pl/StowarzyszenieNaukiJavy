package pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception;

public class UsernameIsAlreadyExistException extends Exception {
    public UsernameIsAlreadyExistException() {
    }

    public UsernameIsAlreadyExistException(String message) {
        super(message);
    }
}
