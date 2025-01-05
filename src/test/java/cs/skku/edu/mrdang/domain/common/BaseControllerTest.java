package cs.skku.edu.mrdang.domain.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs.skku.edu.mrdang.domain.user.service.UserService;
import cs.skku.edu.mrdang.security.config.LoginArgumentResolver;
import cs.skku.edu.mrdang.security.jwt.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static cs.skku.edu.mrdang.domain.user.fixture.UserFixture.USER;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@WebMvcTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@Import(RestDocsAutoConfiguration.class)
public class BaseControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected LoginArgumentResolver loginArgumentResolver;

    @MockBean
    protected JWTUtil jwtUtil;

    @MockBean
    protected UserService userService;

    protected  static final String FAKE_SUBJECT = "SKKU";

    @BeforeEach
    void setUp() {
        doNothing().when(jwtUtil).validateAccessToken(anyString());
        doNothing().when(jwtUtil).validateRefreshToken(anyString());
        given(jwtUtil.getSubject(anyString()))
                .willReturn(FAKE_SUBJECT);
        given(userService.getUserByGlsId(anyString()))
                .willReturn(USER);
    }
}
