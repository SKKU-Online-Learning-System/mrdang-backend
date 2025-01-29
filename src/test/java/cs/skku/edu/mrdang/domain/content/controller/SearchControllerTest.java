package cs.skku.edu.mrdang.domain.content.controller;

import cs.skku.edu.mrdang.domain.common.BaseControllerTest;
import cs.skku.edu.mrdang.domain.content.ContentSnippet;
import cs.skku.edu.mrdang.domain.content.dto.ContentDTO;
import cs.skku.edu.mrdang.domain.content.service.SearchService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

import static cs.skku.edu.mrdang.domain.content.fixture.ContentFixture.CONTENT;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContentController.class)
@MockBean(JpaMetamodelMappingContext.class)
class SearchControllerTest extends BaseControllerTest {

    @MockBean
    private SearchService searchService;
    private ContentSnippet contentSnippet = new ContentSnippet();

    @DisplayName("API 문서 - 컨텐츠 검색")
    @Test
    void searchContent() throws Exception {
        // given
        String link = "https://www.youtube.com/watch?v=1234";

        // when
        when(searchService.searchContent(link))
                .thenReturn(ContentDTO.Response.from(CONTENT));

        // then
        mockMvc.perform(get("/search")
                        .param("link", link)
                )
                .andExpect(status().isOk())
                .andDo(document("search-content",
                        queryParameters(
                                parameterWithName("link").description("검색할 컨텐츠의 링크")
                        ),
                        contentSnippet.ContentResponse()
                ));
    }
}