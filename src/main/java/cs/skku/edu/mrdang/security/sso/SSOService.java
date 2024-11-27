package cs.skku.edu.mrdang.security.sso;

import SafeIdentity.SsoAuthInfo;
import cs.skku.edu.mrdang.domain.user.entity.User;
import cs.skku.edu.mrdang.domain.user.service.UserService;
import cs.skku.edu.mrdang.exception.ErrorCode;
import cs.skku.edu.mrdang.exception.RestException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Profile({"dev", "prod"})
@Service
public final class SSOService {

    private SafeIdentity.SSO sso;

    @Value("${sso.api.key}")
    private String SSO_API_KEY;
    @Value("${sso.host}")
    private String SSO_HOST;
    @Value("${sso.port}")
    private String SSO_PORT;
    @Value("${sso.retUrl}")
    private String SSO_REDIRECT_URL;

    private final UserService userService;

    public SSOService(UserService userService) {
        this.userService = userService;
    }

    public void init() {
        this.sso = new SafeIdentity.SSO(SSO_API_KEY);
        sso.setHostName(SSO_HOST);
        sso.setPortNumber(Integer.parseInt(SSO_PORT));
    }

    public User login(HttpServletRequest request) {
        if(this.sso == null) {
            this.init();
        }
        String pToken = getPToken(request);
        if (isTokenValid(pToken)) {
            SSOUser ssoUser = getAuthInfoByToken(pToken);

            User user = userService.getOrCreateUser(ssoUser);
            return user;
        }
        throw new RestException(ErrorCode.INVALID_SSO_TOKEN);
    }

    public void logout(HttpServletRequest request) {
        if(this.sso == null) {
           this.init();
        }
        String pToken = getPToken(request);
        unregister(pToken);
    }

    public boolean isValid(HttpServletRequest request) {
        if(this.sso == null) {
            this.init();
        }
        String pToken = getPToken(request);
        return isTokenValid(pToken);
    }

    public boolean isTokenValid(String token) {
        return (verifyToken(token) >= -1);
    }

    public SSOUser getAuthInfoByToken(String token) {
        SsoAuthInfo ssoAuthInfo = sso.userView(token);
        String ssoProfile = ssoAuthInfo.getProfile();
        String[] profileArr = ssoProfile.split("\\*");

        return SSOUser.builder()
                .userId(ssoAuthInfo.getUserId())
                .userName(ssoAuthInfo.getUserName())
                .studentId(getInfo(profileArr[0]))
                .department(getInfo(profileArr[2]))
                .degree(getInfo(profileArr[4]))
                .status(getInfo(profileArr[6]))
                .group(getInfo(profileArr[14]))
                .employeeId(getInfo(profileArr[15]))
                .build();
    }

    public String getPToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("pToken"))
                return cookie.getValue();
        }
        return "";
    }

    public String getRedirectUrl() {
        return this.SSO_REDIRECT_URL;
    }

    private int verifyToken(String token) {
        return sso.verifyToken(token);
    }

    private void unregister(String pToken) {
        this.sso.unregUserSession(pToken);
    }

    private String getInfo(String str) {
        String[] tokens = str.split("-");
        return ((tokens.length > 1) ? tokens[1] : "N/A");
    }

}
