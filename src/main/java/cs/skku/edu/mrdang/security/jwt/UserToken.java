package cs.skku.edu.mrdang.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserToken {
    private String accessToken;
    private String refreshToken;
}
