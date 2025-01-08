package cs.skku.edu.mrdang.domain.content.dto;

import cs.skku.edu.mrdang.domain.content.entity.Content;
import cs.skku.edu.mrdang.domain.content.entity.ContentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ContentDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateRequest{
        private ContentType type;

        private String title;
        private String description;
        private String author;

        private String link;
        private String thumbnail_url;

        public static Response from(Content content) {
            return Response.builder()
                    .type(content.getType())
                    .title(content.getTitle())
                    .description(content.getDescription())
                    .author(content.getAuthor())
                    .link(content.getLink())
                    .thumbnail_url(content.getThumbnail_url())
                    .build();
        }

        public Content toEntity() {
            return Content.builder()
                    .type(type)
                    .title(title)
                    .description(description)
                    .author(author)
                    .link(link)
                    .thumbnail_url(thumbnail_url)
                    .build();
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {

        private Long id;

        private ContentType type;

        private String title;
        private String description;
        private String author;

        private String link;
        private String thumbnail_url;

        public static Response from(Content content) {
            return Response.builder()
                    .id(content.getId())
                    .type(content.getType())
                    .title(content.getTitle())
                    .description(content.getDescription())
                    .author(content.getAuthor())
                    .link(content.getLink())
                    .thumbnail_url(content.getThumbnail_url())
                    .build();
        }
    }
}
