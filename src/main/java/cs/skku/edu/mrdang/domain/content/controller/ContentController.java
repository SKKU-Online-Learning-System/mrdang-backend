package cs.skku.edu.mrdang.domain.content.controller;

import cs.skku.edu.mrdang.domain.content.dto.ContentDTO;
import cs.skku.edu.mrdang.domain.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contents")
public class ContentController {

    private final ContentService contentService;

    // auth 추가
    @PostMapping
    public ResponseEntity<Long> createContent(@RequestBody ContentDTO.CreateRequest request) {
        Long id = contentService.createContent(request);
        return ResponseEntity
                .created(URI.create("/contents" + id))
                .body(id);
    }

    @GetMapping
    public ResponseEntity<List<ContentDTO.Response>> getContents() {
        List<ContentDTO.Response> response = contentService.getContents();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<ContentDTO.Response> getContent(@PathVariable Long contentId) {
        ContentDTO.Response response = contentService.getContent(contentId);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long contentId) {
        contentService.deleteContent(contentId);
        return ResponseEntity.noContent().build();
    }
}
