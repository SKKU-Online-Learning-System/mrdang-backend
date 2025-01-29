package cs.skku.edu.mrdang.domain.content.dto;

import cs.skku.edu.mrdang.domain.content.entity.Content;
import cs.skku.edu.mrdang.domain.content.entity.ContentType;
import org.springframework.stereotype.Component;

@Component
public class ContentMapper {
    public Content youtube2Content(YoutubeDTO.Response youtube) {
        return Content.builder()
                .type(ContentType.YOUTUBE)
                .title(youtube.getTitle())
                .description(youtube.getDescription())
                .author(youtube.getAuthor())
                .link(youtube.getLink())
                .thumbnail_url(youtube.getThumbnail())
                .build();
    }
}
