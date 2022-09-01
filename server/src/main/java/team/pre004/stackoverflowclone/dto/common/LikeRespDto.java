package team.pre004.stackoverflowclone.dto.common;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import team.pre004.stackoverflowclone.handler.ResponseCode;

@Data
@NoArgsConstructor
public class LikeRespDto<T> {

    private ResponseCode code;
    private String message;
    private T like;

    @Builder
    public LikeRespDto(ResponseCode code, String message, T like) {
        this.code = code;
        this.message = message;
        this.like = like;
    }
}
