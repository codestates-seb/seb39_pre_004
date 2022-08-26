package team.pre004.stackoverflowclone.dto;

import lombok.Builder;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Builder
@Setter
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String bio;

    private LocalDateTime createDate;

    private LocalDateTime modDate;
}
