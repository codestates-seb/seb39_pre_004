package team.pre004.stackoverflowclone.handler.exception;

import team.pre004.stackoverflowclone.handler.ExceptionMessage;

public class CustomNotContentByIdException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CustomNotContentByIdException() {
    }

    public CustomNotContentByIdException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
    }
}
