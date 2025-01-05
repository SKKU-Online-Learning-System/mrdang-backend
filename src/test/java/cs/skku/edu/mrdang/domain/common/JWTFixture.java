package cs.skku.edu.mrdang.domain.common;

import cs.skku.edu.mrdang.security.jwt.UserToken;

public class JWTFixture {
    public static final UserToken USER_TOKEN = UserToken.builder()
            .accessToken("access")
            .refreshToken("refresh")
            .build();
}
