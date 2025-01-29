package cs.skku.edu.mrdang.domain.content.controller;

import cs.skku.edu.mrdang.domain.common.BaseControllerTest;
import cs.skku.edu.mrdang.domain.content.ContentSnippet;
import cs.skku.edu.mrdang.domain.content.dto.ContentDTO;
import cs.skku.edu.mrdang.domain.content.service.ContentService;
import cs.skku.edu.mrdang.security.jwt.UserToken;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;

import java.util.stream.Collectors;

import static cs.skku.edu.mrdang.domain.common.CommonSnippet.AdminCookie;
import static cs.skku.edu.mrdang.domain.common.CommonSnippet.StudentCookie;
import static cs.skku.edu.mrdang.domain.common.JWTFixture.USER_TOKEN;
import static cs.skku.edu.mrdang.domain.content.fixture.ContentFixture.CONTENT;
import static cs.skku.edu.mrdang.domain.content.fixture.ContentFixture.CONTENTS;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContentController.class)
@MockBean(JpaMetamodelMappingContext.class)
class ContentControllerTest extends BaseControllerTest {

    @MockBean
    private ContentService contentService;
    private ContentSnippet contentSnippet = new ContentSnippet();

    @DisplayName("API 문서 - 컨텐츠 생성")
    @Test
    void createContent() throws Exception {
        UserToken userToken = USER_TOKEN;

        ContentDTO.CreateRequest request = ContentDTO.CreateRequest.builder()
                .type(CONTENT.getType())
                .title(CONTENT.getTitle())
                .description(CONTENT.getDescription())
                .author(CONTENT.getAuthor())
                .link(CONTENT.getLink())
                .thumbnail_url(CONTENT.getThumbnail_url())
                .build();

        mockMvc.perform(post("/contents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .cookie(new Cookie("access-token", userToken.getAccessToken()))
                        .cookie(new Cookie("refresh-token", userToken.getRefreshToken()))
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(document(
                        "create-content",
                        StudentCookie(),
                        contentSnippet.CreateContentRequest()
                ));
    }

    @DisplayName("API 문서 - 모든 컨텐츠 가져오기")
    @Test
    void getContents() throws Exception {
        when(contentService.getContents()).thenReturn(
                CONTENTS.stream().map(ContentDTO.Response::from).collect(Collectors.toList())
        );

        mockMvc.perform(get("/contents")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document(
                        "get-contents",
                        contentSnippet.ContentResponses()
                ));
    }

    @DisplayName("API 문서 - 컨텐츠 가져오기")
    @Test
    void getContent() throws Exception {
        Long id = CONTENT.getId();

        when(contentService.getContent(id)).thenReturn(ContentDTO.Response.from(CONTENT));

        mockMvc.perform(get("/contents/{contentId}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document(
                        "get-content",
                        pathParameters(
                                parameterWithName("contentId").description("가져올 컨텐츠 ID")
                        ),
                        contentSnippet.ContentResponse()
                ));
    }

    @DisplayName("API 문서 - 컨텐츠 삭제")
    @Test
    void deleteContent() throws Exception {
        UserToken userToken = USER_TOKEN;

        Long id = CONTENT.getId();

        mockMvc.perform(delete("/contents/{contentId}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .cookie(new Cookie("access-token", userToken.getAccessToken()))
                        .cookie(new Cookie("refresh-token", userToken.getRefreshToken()))
                )
                .andExpect(status().isNoContent())
                .andDo(print())
                .andDo(document(
                        "delete-content",
                        pathParameters(
                                parameterWithName("contentId").description("삭제할 컨텐츠 ID")
                        ),
                        AdminCookie()
                ));
    }
}