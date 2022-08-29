package team.pre004.stackoverflowclone.dto.common;

import lombok.*;
import team.pre004.stackoverflowclone.handler.ResponseCode;

@NoArgsConstructor
@Data
public class CMRespDto<T> {


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
