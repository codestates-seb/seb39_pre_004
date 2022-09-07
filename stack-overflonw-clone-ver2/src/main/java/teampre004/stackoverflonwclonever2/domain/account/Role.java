package teampre004.stackoverflonwclonever2.domain.account;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "비회원"),
    USER("ROLE_USER", "일반유저"),
    MANAGER("ROLE_MANAGER", "관리자"),
    ADMIN("ROLE_ADMIN", "운영자");

    private final String code;
    private final String displayName;

    public static Role of(String code) {
        return Arrays.stream(Role.values())
                .filter(role -> role.getCode().equals(code))
                .findAny()
                .orElse(GUEST);
    }


}
