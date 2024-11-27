package cs.skku.edu.mrdang.security;


import cs.skku.edu.mrdang.security.jwt.UserToken;
import cs.skku.edu.mrdang.security.sso.SSOService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@Profile({"dev", "prod"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final int ONE_WEEK_SECONDS = 604800;

    private final SSOService ssoService;
    private final AuthService authService;

    @PostMapping()
    public ResponseEntity<Void> login(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        if (!authService.isSSOValid(request)) {
            response.sendRedirect("https://login.skku.edu?retUrl=" + ssoService.getRedirectUrl());
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }
        UserToken userToken = authService.login(request);
        ResponseCookie access = ResponseCookie.from("access-token", userToken.getAccessToken())
                .maxAge(ONE_WEEK_SECONDS)
                .secure(true)
                .sameSite("None")
                .domain("mrdang.cs.skku.edu")
                .path("/")
                .build();
        ResponseCookie refresh = ResponseCookie.from("refresh-token", userToken.getRefreshToken())
                .maxAge(ONE_WEEK_SECONDS)
                .secure(true)
                .sameSite("None")
                .domain("mrdang.cs.skku.edu")
                .path("/")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, access.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refresh.toString());

        response.sendRedirect("https://mrdang.cs.skku.edu");
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }

    @GetMapping("/logout")
    public void logout(
            final HttpServletRequest request,
            final HttpServletResponse response,
            @CookieValue("refresh-token") final String refreshToken
    ) {
        authService.logout(request, refreshToken);

        ResponseCookie access = ResponseCookie.from("access-token", "")
                .maxAge(0)
                .secure(true)
                .sameSite("None")
                .domain("mrdang.cs.skku.edu")
                .path("/")
                .build();
        ResponseCookie refresh = ResponseCookie.from("refresh-token", "")
                .maxAge(0)
                .secure(true)
                .sameSite("None")
                .domain("mrdang.cs.skku.edu")
                .path("/")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, access.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refresh.toString());
    }

    @PostMapping("/reissue")
    public void reissue(
            @CookieValue("access-token") final String accessToken,
            @CookieValue("refresh-token") final String refreshToken,
            HttpServletResponse response
    ) {
        String newAccessToken = authService.reissueAccessToken(accessToken, refreshToken);

        ResponseCookie access = ResponseCookie.from("access-token", newAccessToken)
                .maxAge(ONE_WEEK_SECONDS)
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .domain("mrdang.cs.skku.edu")
                .path("/")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, access.toString());
        ResponseEntity.status(CREATED).build();
    }
}