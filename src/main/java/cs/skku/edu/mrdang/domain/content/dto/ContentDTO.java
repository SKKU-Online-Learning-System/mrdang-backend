package cs.skku.edu.mrdang.domain.content.dto;

import cs.skku.edu.mrdang.domain.content.entity.Content;
import cs.skku.edu.mrdang.domain.content.entity.ContentType;
import cs.skku.edu.mrdang.domain.content.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
        private List<String> tags;

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
                    .tags(new HashSet<>())
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
        private List<String> tags;

        public static Response from(Content content) {
            return Response.builder()
                    .id(content.getId())
                    .type(content.getType())
                    .title(content.getTitle())
                    .description(content.getDescription())
                    .author(content.getAuthor())
                    .link(content.getLink())
                    .thumbnail_url(content.getThumbnail_url())
                    .tags(content.getTags().stream().map(Tag::getName).collect(Collectors.toList()))
                    .build();
        }
    }
}
