package team.pre004.stackoverflowclone.dto.common;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.handler.ResponseCode;

@Data
@NoArgsConstructor
public class AnswerRespDto<T>{


    private ResponseCode code;
    private String message;

    private T answer;

    @Builder
    public AnswerRespDto(ResponseCode code, String message, T answer) {
        this.code = code;
        this.message = message;
        this.answer = answer;
    }
}
