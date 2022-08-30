package team.pre004.stackoverflowclone.handler.exception;

import team.pre004.stackoverflowclone.handler.ExceptionMessage;

public class CustomLikesConflictException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CustomLikesConflictException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
    }
}
