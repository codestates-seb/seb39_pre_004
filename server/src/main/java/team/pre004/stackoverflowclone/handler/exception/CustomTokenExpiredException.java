package team.pre004.stackoverflowclone.handler.exception;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;

public class CustomTokenExpiredException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CustomTokenExpiredException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
    }

    public CustomTokenExpiredException(String exceptionMessage) {
        super(exceptionMessage);
    }

}
