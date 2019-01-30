package pl.kostrzej.simpleToDoApp.components.user;

public class UserAlreadyExistsException extends RuntimeException {
    UserAlreadyExistsException(){ super("Użytkownik o podanym loginie już istnieje!");}
}
