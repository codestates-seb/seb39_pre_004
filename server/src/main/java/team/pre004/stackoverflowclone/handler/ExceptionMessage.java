package team.pre004.stackoverflowclone.handler;

import org.springframework.http.HttpStatus;

public enum ExceptionMessage {

    //유저 에러 메세지

    NOT_CONTENT_USER_ID(40, "유저 아이디가 존재하지 않습니다."),
    NOT_CONTENT_USER_NAME(41, "유저 닉네임이 존재하지 않습니다."),
    NOT_CONTENT_USER_EMAIL(42, "유저 이메일이 존재하지 않습니다."),
    NOT_CONTENT_USER_PASSWORD(43, "유저 비밀번호가 존재하지 않습니다."),

    NOT_CONTENT_QUESTION_ID(50, "질문이 존재하지 않습니다."),
    NOT_CONTENT_QUESTION_TITLE(51, "질문 제목이 존재하지 않습니다."),
    NOT_CONTENT_QUESTION_BODY(52, "질문 내용이 존재하지 않습니다."),

    NOT_CONTENT_QUESTION_COMMENT_BODY(53, "댓글 내용이 존재하지 않습니다."),

    NOT_ACCESS_EDIT_QUESTION(400, "질문을 변경할 수 있는 권한이 없습니다.");

    public String getMessage() {
        return message;
    }

    ExceptionMessage(int value, String message) {
        this.value = value;
        this.message = message;
    }

    private final int value;

    private final String message;


}