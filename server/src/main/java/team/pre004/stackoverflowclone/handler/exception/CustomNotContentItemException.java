package team.pre004.stackoverflowclone.handler.exception;

import team.pre004.stackoverflowclone.handler.ExceptionMessage;

public class CustomNotContentItemException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CustomNotContentItemException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
    }
}
