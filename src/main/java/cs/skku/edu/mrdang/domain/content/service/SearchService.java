package cs.skku.edu.mrdang.domain.content.service;

import cs.skku.edu.mrdang.domain.content.dto.ContentDTO;
import cs.skku.edu.mrdang.domain.content.entity.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchService {

    private final ApiService apiService;

    public ContentDTO.Response searchContent(String url) {
        Content content = apiService.getContents(url);
        return ContentDTO.Response.from(content);
    }
}
