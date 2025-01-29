package cs.skku.edu.mrdang.domain.content.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs.skku.edu.mrdang.domain.content.dto.ContentMapper;
import cs.skku.edu.mrdang.domain.content.dto.YoutubeDTO;
import cs.skku.edu.mrdang.domain.content.entity.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class ApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ContentMapper contentMapper;

    private static String CONTENT_API_URL = "http://localhost:8888/scrap"; // TODO: .env

    public Content getContents(String url) {
        String type = getApiType(url);
        if(type=="youtube") {
            Content content = getContentFromYoutube(url);
            return content;
        } else {
            throw new RuntimeException("Invalid URL");
        }
    }


    private Content getContentFromYoutube(String url) {
        String apiUrl = CONTENT_API_URL + "/youtube?link=" + url;
        String res = restTemplate.getForObject(apiUrl, String.class);
        try {
            YoutubeDTO.Response youtubeResponse = objectMapper.readValue(res, YoutubeDTO.Response.class);
            return contentMapper.youtube2Content(youtubeResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse youtube response");
        }
    }

    private String getApiType(String url) {
        if(url.contains("youtube")) {
            return "youtube";
        } else {
            return null;
        }
    }
}
