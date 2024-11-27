package cs.skku.edu.mrdang.security;

import cs.skku.edu.mrdang.domain.user.entity.User;
import cs.skku.edu.mrdang.domain.user.entity.UserRole;
import cs.skku.edu.mrdang.domain.user.repository.UserRepository;
import cs.skku.edu.mrdang.security.jwt.JWTUtil;
import cs.skku.edu.mrdang.security.jwt.UserToken;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@Profile("local")
@RequiredArgsConstructor
@RestController
public class BackdoorController {

    private static final int ONE_WEEK_SECONDS = 604800;
    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;

    @GetMapping("/backdoor")
    public ResponseEntity<Void> backdoor(
            @RequestParam String glsId,
            HttpServletResponse response
    ) {
        UserToken userToken = jwtUtil.createUserToken(glsId);

        if(!userRepository.existsByGlsId(glsId))
            generateTempUser(glsId);

        ResponseCookie accessToken = ResponseCookie.from("access-token", userToken.getAccessToken())
                .maxAge(ONE_WEEK_SECONDS)
                .sameSite("Lax")
                .domain("localhost")
                .path("/")
                .build();

        ResponseCookie refreshToken = ResponseCookie.from("refresh-token", userToken.getRefreshToken())
                .maxAge(ONE_WEEK_SECONDS)
                .sameSite("Lax")
                .domain("localhost")
                .path("/")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessToken.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshToken.toString());
        return ResponseEntity.status(CREATED).build();
    }

    private void generateTempUser(String glsId) {
        User newUser = User.builder()
                .name("안잔다")
                .glsId(glsId)
                .role(UserRole.ADMIN)
                .studentId("2020313052")
                .academicDepartment("소프트웨어학과")
                .academicDegree("학사")
                .academicStatus("재학")
                .employeeId("N/A")
                .employeeGroup("N/A")
                .build();

        userRepository.save(newUser);
    }
}
