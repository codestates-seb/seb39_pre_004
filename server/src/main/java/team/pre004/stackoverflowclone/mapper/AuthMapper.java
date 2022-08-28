package team.pre004.stackoverflowclone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import team.pre004.stackoverflowclone.domain.user.User;
import team.pre004.stackoverflowclone.dto.request.UserSignUpDto;
import team.pre004.stackoverflowclone.dto.response.UserResponseDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    User userSignUpToUser(UserSignUpDto requestBody);
    UserResponseDto userToUserResponseDto(User user);


}
