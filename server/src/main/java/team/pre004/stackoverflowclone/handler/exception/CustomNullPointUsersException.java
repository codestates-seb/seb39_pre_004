package team.pre004.stackoverflowclone.handler.exception;

public class CustomNullPointUsersException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CustomNullPointUsersException(String message) {
        super(message);
    }
}
