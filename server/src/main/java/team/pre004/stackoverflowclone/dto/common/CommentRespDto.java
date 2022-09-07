package team.pre004.stackoverflowclone.dto.common;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.handler.ResponseCode;

@Data
@NoArgsConstructor
public class CommentRespDto<T> {
    private ResponseCode code;
    private String message;
    private T comment;

    @Builder
    public CommentRespDto(ResponseCode code, String message, T comment) {
        this.code = code;
        this.message = message;
        this.comment = comment;
    }
}
