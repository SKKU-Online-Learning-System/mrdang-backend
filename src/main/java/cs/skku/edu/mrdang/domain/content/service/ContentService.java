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

    public List<ContentDTO.Response> getContents() {
        List<Content> contents = contentRepository.findAll();

        return contents.stream()
                .map(ContentDTO.Response::from)
                .collect(Collectors.toList());
    }
}
