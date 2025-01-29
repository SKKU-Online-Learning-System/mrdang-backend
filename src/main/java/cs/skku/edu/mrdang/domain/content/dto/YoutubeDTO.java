package cs.skku.edu.mrdang.domain.content.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class YoutubeDTO {

    @Getter
    @AllArgsConstructor

    public static class Response {
        private String title;
        private String author;
        private String description;
        private String link;
        private String thumbnail;
        private String type;
        private List<String> tags;
    }

}
