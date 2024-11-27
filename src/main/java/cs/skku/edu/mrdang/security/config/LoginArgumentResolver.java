package cs.skku.edu.mrdang.security.config;


import cs.skku.edu.mrdang.domain.user.entity.User;
import cs.skku.edu.mrdang.domain.user.service.UserService;
import cs.skku.edu.mrdang.exception.ErrorCode;
import cs.skku.edu.mrdang.exception.RestException;
import cs.skku.edu.mrdang.security.annotation.Auth;
import cs.skku.edu.mrdang.security.jwt.JWTUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;

@RequiredArgsConstructor
@Component
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    private final JWTUtil jwtUtil;
    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.withContainingClass(User.class).hasParameterAnnotation(Auth.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        if (request== null) {
            throw new RestException(ErrorCode.FAIL_TO_VALIDATE_TOKEN);
        }

        String accessToken = extractAccessToken(request);
        String refreshToken = extractRefreshToken(request);

        jwtUtil.validateAccessToken(accessToken);
        jwtUtil.validateRefreshToken(refreshToken);

        String glsId = jwtUtil.getSubject(accessToken);

        User user = userService.getUserByGlsId(glsId);
        return user;
    }

    private String extractAccessToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new RestException(ErrorCode.INVALID_ACCESS_TOKEN);
        }

        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("access-token"))
                .findFirst()
                .orElseThrow(() -> new RestException(ErrorCode.INVALID_ACCESS_TOKEN))
                .getValue();
    }

    private String extractRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new RestException(ErrorCode.INVALID_REFRESH_TOKEN);
        }

        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("refresh-token"))
                .findFirst()
                .orElseThrow(() -> new RestException(ErrorCode.INVALID_REFRESH_TOKEN))
                .getValue();
    }
}
