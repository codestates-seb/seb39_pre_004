package teampre004.stackoverflonwclonever2.domain.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VoteType {

    UP(true),
    DOWN(false);

    private final boolean value;
}
