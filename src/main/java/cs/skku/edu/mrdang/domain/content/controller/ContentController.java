package cs.skku.edu.mrdang.domain.content.controller;

import cs.skku.edu.mrdang.domain.content.dto.ContentDTO;
import cs.skku.edu.mrdang.domain.content.service.ContentService;
import cs.skku.edu.mrdang.domain.user.entity.User;
import cs.skku.edu.mrdang.security.annotation.Auth;
import cs.skku.edu.mrdang.security.annotation.AuthenticationPrincipal;
import cs.skku.edu.mrdang.security.annotation.MasterOnly;
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

    @Auth
    @PostMapping
    public ResponseEntity<Long> createContent(@AuthenticationPrincipal User user, @RequestBody ContentDTO.CreateRequest request) {
        Long id = contentService.createContent(request);
        return ResponseEntity
                .created(URI.create("/contents" + id))
                .body(id);
    }

    @GetMapping
    public ResponseEntity<List<ContentDTO.Response>> getContents(@AuthenticationPrincipal User user) {
        List<ContentDTO.Response> response = contentService.getContents(user);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{contentId}")
    public ResponseEntity<ContentDTO.Response> getContent(
            @AuthenticationPrincipal User user,
            @PathVariable Long contentId
    ) {
        ContentDTO.Response response = contentService.getContent(user, contentId);

        return ResponseEntity.ok().body(response);
    }

    @Auth
    @PatchMapping("/{contentId}/likes")
    public ResponseEntity<Void> updateContentLike(@AuthenticationPrincipal User user, @PathVariable Long contentId) {
        contentService.updateLike(user, contentId);

        return ResponseEntity.noContent().build();
    }

    @MasterOnly
    @DeleteMapping("/{contentId}")
    public ResponseEntity<Void> deleteContent(@AuthenticationPrincipal User user, @PathVariable Long contentId) {
        contentService.deleteContent(contentId);
        return ResponseEntity.noContent().build();
    }
}
