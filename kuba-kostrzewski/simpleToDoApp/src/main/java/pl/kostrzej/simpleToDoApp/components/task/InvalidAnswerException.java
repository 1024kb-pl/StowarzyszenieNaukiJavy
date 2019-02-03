package pl.kostrzej.simpleToDoApp.components.task;

public class InvalidAnswerException extends RuntimeException {
    InvalidAnswerException(){
        super("Niepoprawna odpowiedź!");
    }
}
