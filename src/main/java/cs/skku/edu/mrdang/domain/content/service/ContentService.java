package cs.skku.edu.mrdang.domain.content.service;

import cs.skku.edu.mrdang.domain.content.dto.ContentDTO;
import cs.skku.edu.mrdang.domain.content.entity.Content;
import cs.skku.edu.mrdang.domain.content.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ContentService {

    private final ContentRepository contentRepository;

    public Long createContent(ContentDTO.CreateRequest request) {
        Content content = request.toEntity();

        contentRepository.save(content);
        return content.getId();
    }

    // TODO: 우선순위 추가
    public List<ContentDTO.Response> getContents() {
        List<Content> contents = contentRepository.findAll();

        return contents.stream()
                .map(ContentDTO.Response::from)
                .collect(Collectors.toList());
    }

    public ContentDTO.Response getContent(Long contentId) {
        // TODO: 에러코드 추가
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 컨텐츠가 존재하지 않습니다. id=" + contentId));

        return ContentDTO.Response.from(content);
    }

    public void deleteContent(Long contentId) {
        contentRepository.deleteById(contentId);
    }
}
