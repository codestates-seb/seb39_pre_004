package team.pre004.stackoverflowclone.handler.exception;

import team.pre004.stackoverflowclone.handler.ExceptionMessage;

public class CustomDuplicationUsersException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomDuplicationUsersException(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
    }
}
