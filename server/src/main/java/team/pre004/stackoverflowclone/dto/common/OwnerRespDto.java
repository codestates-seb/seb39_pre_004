package team.pre004.stackoverflowclone.dto.common;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.handler.ResponseCode;

@Getter
@NoArgsConstructor
public class OwnerRespDto<T> {
    private ResponseCode code;
    private String message;
    private T owner;

    @Builder
    public OwnerRespDto(ResponseCode code, String message, T owner) {
        this.code = code;
        this.message = message;
        this.owner = owner;
    }
}
