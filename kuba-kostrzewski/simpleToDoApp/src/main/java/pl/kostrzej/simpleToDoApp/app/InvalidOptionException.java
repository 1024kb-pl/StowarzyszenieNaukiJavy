package pl.kostrzej.simpleToDoApp.app;

public class InvalidOptionException extends RuntimeException {
    InvalidOptionException(){super("Nie ma takiej opcji!");}
}
