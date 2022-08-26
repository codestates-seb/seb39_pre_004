package team.pre004.stackoverflowclone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import team.pre004.stackoverflowclone.domain.user.User;
import team.pre004.stackoverflowclone.dto.UserDto;
import team.pre004.stackoverflowclone.dto.UserSignUpDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    User userSignUpDtoToUser(UserSignUpDto requestBody);
    UserDto.response userToUserResponse(User user);


}
