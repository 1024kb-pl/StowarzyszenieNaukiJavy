package pl.kostrzej.simpleToDoApp.components.validator;

public class UserAlreadyExistsException extends RuntimeException {
    UserAlreadyExistsException(){ super("Użytkownik o podanym loginie już istnieje!");}
}
