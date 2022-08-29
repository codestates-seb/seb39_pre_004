package team.pre004.stackoverflowclone.mapper;

import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.response.UserInfoDto;

public interface UsersMapper {
    UserInfoDto getUserInfo(Users users);
}
