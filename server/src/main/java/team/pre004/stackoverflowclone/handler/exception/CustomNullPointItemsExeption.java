package team.pre004.stackoverflowclone.handler.exception;

import team.pre004.stackoverflowclone.handler.ExceptionMessage;

public class CustomNullPointItemsExeption extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CustomNullPointItemsExeption(ExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
    }
}
