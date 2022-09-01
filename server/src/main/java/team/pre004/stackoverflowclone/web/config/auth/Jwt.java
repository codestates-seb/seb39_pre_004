package team.pre004.stackoverflowclone.web.config.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum Jwt {
    SECRET_CODE("SecretCode", "pre004");

    private final String key;
    private final String value;
}
