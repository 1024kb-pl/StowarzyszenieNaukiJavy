package pl._1024kb.stowarzyszenienaukijavy.simpletodo.exception;

public class NotFoundDesiredDataRuntimeException extends RuntimeException
{
    public NotFoundDesiredDataRuntimeException(){}

    public static UserNotFoundException newRunTimeException()
    {
        return new UserNotFoundException("Not found any desired data");
    }
}
