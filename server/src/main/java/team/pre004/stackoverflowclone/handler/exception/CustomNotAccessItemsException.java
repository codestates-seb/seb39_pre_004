package team.pre004.stackoverflowclone.handler.exception;

import team.pre004.stackoverflowclone.handler.ExceptionMessage;

public class CustomNotAccessItemsException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CustomNotAccessItemsException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
    }

    public CustomNotAccessItemsException(String message) {
        super(message);
    }

}
