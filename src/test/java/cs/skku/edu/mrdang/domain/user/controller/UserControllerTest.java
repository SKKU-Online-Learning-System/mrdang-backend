package cs.skku.edu.mrdang.domain.user.controller;

import cs.skku.edu.mrdang.domain.common.BaseControllerTest;
import cs.skku.edu.mrdang.domain.user.dto.UserDTO;
import cs.skku.edu.mrdang.domain.user.entity.User;
import cs.skku.edu.mrdang.domain.user.entity.UserRole;
import cs.skku.edu.mrdang.security.jwt.UserToken;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import java.util.List;

import static cs.skku.edu.mrdang.domain.common.CommonSnippet.MasterCookie;
import static cs.skku.edu.mrdang.domain.common.JWTFixture.USER_TOKEN;
import static cs.skku.edu.mrdang.domain.user.fixture.UserFixture.USER_상엽;
import static cs.skku.edu.mrdang.domain.user.fixture.UserFixture.USER_윤성;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
class UserControllerTest extends BaseControllerTest {
    @DisplayName("API 문서 - 모든 유저 가져오기")
    @Test
    void getUsers() throws Exception {
        UserToken userToken = USER_TOKEN;
        when(userService.getUsers())
                .thenReturn(List.of(
                        UserDTO.Response.from(USER_상엽),
                        UserDTO.Response.from(USER_윤성)
                ));

        mockMvc.perform(get("/users")
                        .cookie(new Cookie("access-token", userToken.getAccessToken()))
                        .cookie(new Cookie("refresh-token", userToken.getRefreshToken()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document(
                        "get-users",
                        MasterCookie(),
                        UserResponses()
                ));
    }

    @DisplayName("API 문서 - 유저 권한 변경하기")
    @Test
    void changeRole() throws Exception {
        UserToken userToken = USER_TOKEN;
        User student = USER_상엽;
        UserDTO.ChangeRoleRequest request = new UserDTO.ChangeRoleRequest(student.getGlsId(), UserRole.ADMIN);

        student.setRole(request.getRole());
        when(userService.changeRole(any()))
                .thenReturn(UserDTO.Response.from(student));

        mockMvc.perform(patch("/users")
                        .cookie(new Cookie("access-token", userToken.getAccessToken()))
                        .cookie(new Cookie("refresh-token", userToken.getRefreshToken()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document(
                        "change-role",
                        MasterCookie(),
                        requestFields(
                                fieldWithPath("glsId").description("유저의 성균관대 ID").type(JsonFieldType.STRING),
                                fieldWithPath("role").description("변경할 권한 (예: STUDENT, ADMIN 등)").type(JsonFieldType.STRING)
                        ),
                        UserResponse()
                ));
    }

    private ResponseFieldsSnippet UserResponses() {
        return responseFields(
                fieldWithPath("[].id")
                        .type(JsonFieldType.NUMBER)
                        .description("유저 고유번호(ID)"),
                fieldWithPath("[].name")
                        .type(JsonFieldType.STRING)
                        .description("유저의 실명"),
                fieldWithPath("[].glsId")
                        .type(JsonFieldType.STRING)
                        .description("유저의 성균관대 ID"),
                fieldWithPath("[].studentId")
                        .type(JsonFieldType.STRING)
                        .description("유저의 학번"),
                fieldWithPath("[].role")
                        .type(JsonFieldType.STRING)
                        .description("유저의 권한"));
    }

    private ResponseFieldsSnippet UserResponse() {
        return responseFields(
                fieldWithPath("id")
                        .type(JsonFieldType.NUMBER)
                        .description("유저 고유번호(ID)"),
                fieldWithPath("name")
                        .type(JsonFieldType.STRING)
                        .description("유저의 실명"),
                fieldWithPath("glsId")
                        .type(JsonFieldType.STRING)
                        .description("유저의 성균관대 ID"),
                fieldWithPath("studentId")
                        .type(JsonFieldType.STRING)
                        .description("유저의 학번"),
                fieldWithPath("role")
                        .type(JsonFieldType.STRING)
                        .description("유저의 권한 (예: STUDENT, ADMIN 등)")
        );
    }
}