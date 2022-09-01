package team.pre004.stackoverflowclone.dto.common;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.NONE)
public class CMRequestDto <T>{
    private T requestData;
}
