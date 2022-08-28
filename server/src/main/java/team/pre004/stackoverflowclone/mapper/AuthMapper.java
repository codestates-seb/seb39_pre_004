package team.pre004.stackoverflowclone.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import team.pre004.stackoverflowclone.domain.user.Users;
import team.pre004.stackoverflowclone.dto.auth.SignUpDto;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

    Users SignUpDtoToUsers(SignUpDto signUpDto);

}
