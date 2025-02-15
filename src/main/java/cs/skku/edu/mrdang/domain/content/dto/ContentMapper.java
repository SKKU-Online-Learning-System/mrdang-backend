package cs.skku.edu.mrdang.domain.content.dto;

import cs.skku.edu.mrdang.domain.content.entity.Content;
import cs.skku.edu.mrdang.domain.content.entity.ContentType;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class ContentMapper {
    public static Content request2Content(ContentDTO.CreateRequest request) {
        return Content.builder()
                .type(request.getType())
                .title(request.getTitle())
                .description(request.getDescription())
                .author(request.getAuthor())
                .link(request.getLink())
                .thumbnailUrl(request.getThumbnailUrl())
                .youtubeVideoId(request.getType() == ContentType.YOUTUBE ? parseYoutubeVideoId(request.getLink()) : null)
                .viewCount(0L)
                .contentTags(new HashSet<>())
                .contentLikes(new HashSet<>())
                .build();
    }

    public Content youtube2Content(YoutubeDTO.Response youtube) {
        return Content.builder()
                .type(ContentType.YOUTUBE)
                .title(youtube.getTitle())
                .description(youtube.getDescription())
                .author(youtube.getAuthor())
                .link(youtube.getLink())
                .thumbnailUrl(youtube.getThumbnail())
                .youtubeVideoId(youtube.getYoutubeVideoId())
                .build();
    }

    private static String parseYoutubeVideoId(String link) {
        String[] split = link.split("v=");
        return split[1];
    }
}
