package cs.skku.edu.mrdang.security;

import cs.skku.edu.mrdang.security.jwt.JWTUtil;
import cs.skku.edu.mrdang.security.jwt.RefreshToken;
import cs.skku.edu.mrdang.security.jwt.UserToken;
import cs.skku.edu.mrdang.security.sso.SSOService;
import cs.skku.edu.mrdang.domain.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"dev", "prod"})
@RequiredArgsConstructor
@Service
public class AuthService {

    private final SSOService ssoService;
    private final JWTUtil jwtUtil;

    public UserToken login(HttpServletRequest request) {
        User user =  ssoService.login(request);

        UserToken userToken = jwtUtil.createUserToken(user.getGlsId());
        return userToken;
    }

    public void logout(HttpServletRequest request, String refreshToken) {
        ssoService.logout(request);
        jwtUtil.removeRefreshToken(refreshToken);
    }

    public String reissueAccessToken(final String accessToken, final String refreshToken) {
        jwtUtil.validateRefreshToken(refreshToken);
        if(jwtUtil.isAccessTokenExpired(accessToken)) {
            RefreshToken token = jwtUtil.getRefreshToken(refreshToken);
            return jwtUtil.regenerateAccessToken(token.getGlsId());
        } else {
            return accessToken;
        }
    }

    public boolean isSSOValid(HttpServletRequest request) {
        return ssoService.isValid(request);
    }
}
