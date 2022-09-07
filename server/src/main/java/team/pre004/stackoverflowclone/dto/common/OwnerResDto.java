package team.pre004.stackoverflowclone.dto.common;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.handler.ResponseCode;

@Data
@NoArgsConstructor
public class OwnerResDto<T> {
    private ResponseCode code;
    private String message;
    private T owner;

    @Builder
    public OwnerResDto(ResponseCode code, String message, T owner) {
        this.code = code;
        this.message = message;
        this.owner = owner;
    }
}
