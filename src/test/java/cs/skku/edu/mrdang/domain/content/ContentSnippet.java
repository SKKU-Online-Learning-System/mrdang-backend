package cs.skku.edu.mrdang.domain.content;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import static org.springframework.restdocs.payload.PayloadDocumentation.*;

public class ContentSnippet {
    public RequestFieldsSnippet CreateContentRequest() {
        return requestFields(
                fieldWithPath("type").type(JsonFieldType.STRING).description("컨텐츠 타입"),
                fieldWithPath("title").type(JsonFieldType.STRING).description("컨텐츠 제목"),
                fieldWithPath("description").type(JsonFieldType.STRING).description("컨텐츠 설명"),
                fieldWithPath("author").type(JsonFieldType.STRING).description("컨텐츠 작성자"),
                fieldWithPath("link").type(JsonFieldType.STRING).description("컨텐츠 링크"),
                fieldWithPath("thumbnailUrl").type(JsonFieldType.STRING).description("컨텐츠 썸네일 URL"),
                fieldWithPath("tags").type(JsonFieldType.ARRAY).description("컨텐츠 태그"),
                fieldWithPath("youtubeVideoId").type(JsonFieldType.STRING).description("유튜브 비디오 ID").optional()
        );
    }

    public ResponseFieldsSnippet ContentResponse() {
        return responseFields(
                fieldWithPath("id").type(JsonFieldType.NUMBER).description("컨텐츠 ID"),
                fieldWithPath("type").type(JsonFieldType.STRING).description("컨텐츠 타입"),
                fieldWithPath("title").type(JsonFieldType.STRING).description("컨텐츠 제목"),
                fieldWithPath("description").type(JsonFieldType.STRING).description("컨텐츠 설명"),
                fieldWithPath("author").type(JsonFieldType.STRING).description("컨텐츠 작성자"),
                fieldWithPath("link").type(JsonFieldType.STRING).description("컨텐츠 링크"),
                fieldWithPath("thumbnailUrl").type(JsonFieldType.STRING).description("컨텐츠 썸네일 URL"),
                fieldWithPath("tags").type(JsonFieldType.ARRAY).description("컨텐츠 태그"),
                fieldWithPath("viewCount").type(JsonFieldType.NUMBER).description("컨텐츠 조회수"),
                fieldWithPath("youtubeVideoId").type(JsonFieldType.STRING).description("유튜브 비디오 ID").optional()
        );
    }

    public ResponseFieldsSnippet ContentResponses() {
        return responseFields(
                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("컨텐츠 ID"),
                fieldWithPath("[].type").type(JsonFieldType.STRING).description("컨텐츠 타입"),
                fieldWithPath("[].title").type(JsonFieldType.STRING).description("컨텐츠 제목"),
                fieldWithPath("[].description").type(JsonFieldType.STRING).description("컨텐츠 설명"),
                fieldWithPath("[].author").type(JsonFieldType.STRING).description("컨텐츠 작성자"),
                fieldWithPath("[].link").type(JsonFieldType.STRING).description("컨텐츠 링크"),
                fieldWithPath("[].thumbnailUrl").type(JsonFieldType.STRING).description("컨텐츠 썸네일 URL"),
                fieldWithPath("[].tags").type(JsonFieldType.ARRAY).description("컨텐츠 태그"),
                fieldWithPath("[].viewCount").type(JsonFieldType.NUMBER).description("컨텐츠 조회수"),
                fieldWithPath("[].youtubeVideoId").type(JsonFieldType.STRING).description("유튜브 비디오 ID").optional()
        );
    }
}
