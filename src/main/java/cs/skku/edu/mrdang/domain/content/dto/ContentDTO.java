package cs.skku.edu.mrdang.domain.content.dto;

import cs.skku.edu.mrdang.domain.content.entity.Content;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ContentDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {

        private Long id;

        private String title;
        private String description;
        private String author;

        private String link;
        private String thumbnail_url;

        public static Response from(Content content) {
            return Response.builder()
                    .id(content.getId())
                    .title(content.getTitle())
                    .description(content.getDescription())
                    .author(content.getAuthor())
                    .link(content.getLink())
                    .thumbnail_url(content.getThumbnail_url())
                    .build();
        }
    }
}
