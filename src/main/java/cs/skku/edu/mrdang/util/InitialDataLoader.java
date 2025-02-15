package cs.skku.edu.mrdang.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs.skku.edu.mrdang.domain.content.dto.ContentDTO;
import cs.skku.edu.mrdang.domain.content.entity.ContentType;
import cs.skku.edu.mrdang.domain.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Profile({"local"})
@Component
public class InitialDataLoader implements ApplicationRunner {

    private final ContentService contentService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    public void run(ApplicationArguments args) {
        try {
            List<VideoData> videos = loadVideosFromJson("skku_sw_videos.json");
            Collections.shuffle(videos, new Random()); // Use a fixed seed for reproducibility
            List<VideoData> selectedVideos = videos.stream().limit(50).collect(Collectors.toList());

            for (VideoData video : selectedVideos) {
                ContentDTO.CreateRequest request = ContentDTO.CreateRequest.builder()
                        .type(ContentType.YOUTUBE)
                        .title(video.getTitle())
                        .description(video.getDescription())
                        .author(video.author)
                        .duration(video.getDuration())
                        .link(video.link)
                        .thumbnailUrl(video.getThumbnailUrl())
                        .tags(video.getTags())
                        .build();
                contentService.createContent(request);
            }
        } catch (IOException e) {
            log.error("Failed to load videos from JSON", e);
        }
    }

    private List<VideoData> loadVideosFromJson(String filePath) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new FileNotFoundException("File not found in classpath: " + filePath);
            }
            return objectMapper.readValue(inputStream, new TypeReference<List<VideoData>>() {});
        }
    }

    @lombok.Data
    private static class VideoData {
        private String type;
        private String youtubeVideoId;
        private String title;
        private String description;
        private String author;
        private String link;
        private String thumbnailUrl;
        private List<String> tags;
        private Long duration;
    }
}