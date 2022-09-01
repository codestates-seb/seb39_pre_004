package team.pre004.stackoverflowclone.dto.common;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.handler.ResponseCode;

@Data
@NoArgsConstructor
public class QuestionRespDto<T>{

    private ResponseCode code;
    private String message;
    private T question;

    @Builder
    public QuestionRespDto(ResponseCode code, String message, T question) {
        this.code = code;
        this.message = message;
        this.question = question;
    }
}
