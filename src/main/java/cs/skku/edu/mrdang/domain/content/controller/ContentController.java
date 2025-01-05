package cs.skku.edu.mrdang.domain.content.controller;

import cs.skku.edu.mrdang.domain.content.dto.ContentDTO;
import cs.skku.edu.mrdang.domain.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contents")
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public ResponseEntity<Void> createContent() {
        Long id = 1L;
        return ResponseEntity
                .created(URI.create("/contents" + id))
                .build();
    }

    @GetMapping
    public ResponseEntity<List<ContentDTO.Response>> getContents() {
        List<ContentDTO.Response> response = contentService.getContents();

        return ResponseEntity.ok().body(response);
    }
}
