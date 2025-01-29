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
                fieldWithPath("thumbnail_url").type(JsonFieldType.STRING).description("컨텐츠 썸네일 URL")
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
                fieldWithPath("thumbnail_url").type(JsonFieldType.STRING).description("컨텐츠 썸네일 URL")
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
                fieldWithPath("[].thumbnail_url").type(JsonFieldType.STRING).description("컨텐츠 썸네일 URL")
        );
    }
}
