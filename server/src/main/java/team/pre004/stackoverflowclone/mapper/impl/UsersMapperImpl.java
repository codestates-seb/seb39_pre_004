package team.pre004.stackoverflowclone.mapper.impl;

import org.springframework.stereotype.Component;
import team.pre004.stackoverflowclone.domain.user.entity.Users;
import team.pre004.stackoverflowclone.dto.post.response.UserInfoDto;
import team.pre004.stackoverflowclone.handler.ExceptionMessage;
import team.pre004.stackoverflowclone.handler.exception.CustomNullPointUsersException;
import team.pre004.stackoverflowclone.mapper.UsersMapper;

@Component
public class UsersMapperImpl implements UsersMapper {
    @Override
    public UserInfoDto getUserInfo(Users users) {
        if (users == null) {
            throw new CustomNullPointUsersException(ExceptionMessage.NOT_CONTENT_USER_ID);
        }

        return UserInfoDto.builder()
                .userId(users.getOwnerId())
                .name(users.getName())
                .email(users.getEmail())
                .bio(users.getBio())
                .link(users.getLink())
                .build();
    }

}
