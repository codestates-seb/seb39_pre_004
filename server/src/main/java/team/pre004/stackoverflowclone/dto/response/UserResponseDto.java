package team.pre004.stackoverflowclone.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Builder
@Getter
public class UserResponseDto{

    private String name;
    private String email;
    private String password;
    private String bio = "";
    private LocalDateTime createDate;
    private LocalDateTime modDate;
}
