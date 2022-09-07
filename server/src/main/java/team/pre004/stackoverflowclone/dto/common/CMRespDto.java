package team.pre004.stackoverflowclone.dto.common;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.pre004.stackoverflowclone.handler.ResponseCode;

@Data
@NoArgsConstructor
public class CMRespDto<T>{
    private ResponseCode code;
    private String message;
    private T data;

    @Builder
    public CMRespDto(ResponseCode code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
