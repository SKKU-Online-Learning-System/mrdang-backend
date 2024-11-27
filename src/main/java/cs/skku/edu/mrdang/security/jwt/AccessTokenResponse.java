package cs.skku.edu.mrdang.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccessTokenResponse {
    private String accessToken;

    public static AccessTokenResponse from(String accessToken) {
        return new AccessTokenResponse(accessToken);
    }
}
