package team.pre004.stackoverflowclone.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "비회원"),
    USER("ROLE_USER", "일반유저"),
    MANAGER("ROLE_MANAGER", "관리자"),
    ADMIN("ROLE_MANAGER", "운영자");

    private final String key;
    private final String title;

}
