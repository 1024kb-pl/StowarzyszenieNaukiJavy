package pl.kostrzej.simpleToDoApp.components.user;

public class UserAlreadyExistsException extends RuntimeException {
   public UserAlreadyExistsException(String message){ super(message);}
}
