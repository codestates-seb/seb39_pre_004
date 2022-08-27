package team.pre004.stackoverflowclone.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T> {

    private int code;
    private String message;
    private T data;
}
