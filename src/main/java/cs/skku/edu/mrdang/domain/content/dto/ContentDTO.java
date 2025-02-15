package cs.skku.edu.mrdang.domain.content.dto;

import cs.skku.edu.mrdang.domain.content.entity.Content;
import cs.skku.edu.mrdang.domain.content.entity.ContentTag;
import cs.skku.edu.mrdang.domain.content.entity.ContentType;
import cs.skku.edu.mrdang.domain.content.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

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
        private String thumbnailUrl;
        private List<String> tags;

        @Nullable
        private String youtubeVideoId;

        public Content toEntity() {
            return Content.builder()
                    .type(type)
                    .title(title)
                    .description(description)
                    .author(author)
                    .link(link)
                    .thumbnailUrl(thumbnailUrl)
                    .youtubeVideoId(youtubeVideoId)
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
        private String thumbnailUrl;
        private List<String> tags;

        /* Custom fields */
        @Nullable
        private String youtubeVideoId;

        public static Response from(Content content) {
            return Response.builder()
                    .id(content.getId())
                    .type(content.getType())
                    .title(content.getTitle())
                    .description(content.getDescription())
                    .author(content.getAuthor())
                    .link(content.getLink())
                    .thumbnailUrl(content.getThumbnailUrl())
                    .tags(content.getContentTags().stream().map(ContentTag::getTag).map(Tag::getName).collect(Collectors.toList()))
                    .youtubeVideoId(content.getYoutubeVideoId())
                    .build();
        }
    }
}
