package team.pre004.stackoverflowclone.handler.exception;

import team.pre004.stackoverflowclone.handler.ExceptionMessage;

public class CustomNullPointUsersException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CustomNullPointUsersException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
    }
}
