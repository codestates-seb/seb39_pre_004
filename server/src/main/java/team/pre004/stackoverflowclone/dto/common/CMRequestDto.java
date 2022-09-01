package team.pre004.stackoverflowclone.dto.common;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CMRequestDto <T>{
    private T data;
}
