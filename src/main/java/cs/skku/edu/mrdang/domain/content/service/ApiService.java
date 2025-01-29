package cs.skku.edu.mrdang.domain.content.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs.skku.edu.mrdang.domain.content.dto.YoutubeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class ApiService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public String getContents() {
        String res = restTemplate.getForObject("http://localhost:8888/scrap/youtube?link=https://www.youtube.com/watch?v=eboJ0668vqg", String.class);
        try {
            YoutubeDTO.Response json = objectMapper.readValue(res, YoutubeDTO.Response.class);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
