package cs.skku.edu.mrdang.domain.content.service;

import cs.skku.edu.mrdang.domain.content.dto.ContentDTO;
import cs.skku.edu.mrdang.domain.content.dto.ContentMapper;
import cs.skku.edu.mrdang.domain.content.entity.Content;
import cs.skku.edu.mrdang.domain.content.entity.ContentLike;
import cs.skku.edu.mrdang.domain.content.entity.ContentTag;
import cs.skku.edu.mrdang.domain.content.entity.Tag;
import cs.skku.edu.mrdang.domain.content.repository.ContentLikeRepository;
import cs.skku.edu.mrdang.domain.content.repository.ContentRepository;
import cs.skku.edu.mrdang.domain.content.repository.ContentTagRepository;
import cs.skku.edu.mrdang.domain.content.repository.TagRepository;
import cs.skku.edu.mrdang.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final TagRepository tagRepository;
    private final ContentTagRepository contentTagRepository;
    private final ContentLikeRepository contentLikeRepository;

    public Long createContent(ContentDTO.CreateRequest request) {
        Content content = ContentMapper.request2Content(request);

        Content saved = contentRepository.save(content);
        saveTags(saved, request.getTags());

        return content.getId();
    }

    private void saveTags(Content content, List<String> tagNames) {
        List<Tag> tags = new ArrayList<>();

        // 중복 태그 발생 방지
        for(String tagName : tagNames) {
            Tag tag = tagRepository.findByName(tagName)
                    .orElseGet(() -> tagRepository.save(Tag.builder().name(tagName).build()));
            tags.add(tag);
        }
        for(Tag tag : tags) {
            ContentTag contentTag = ContentTag.builder()
                    .content(content)
                    .tag(tag)
                    .build();
            contentTagRepository.save(contentTag);
        }
    }

    // TODO: 우선순위 추가
    public List<ContentDTO.Response> getContents() {
        return this.getContents(null);
    }

    public List<ContentDTO.Response> getContents(User user) {
        List<Content> contents = contentRepository.findAll();

        return contents.stream()
                .map(content -> buildUserBasedResponse(content, user))
                .collect(Collectors.toList());
    }

    public ContentDTO.Response getContent(Long contentId) {
        return this.getContent(null, contentId);
    }

    public ContentDTO.Response getContent(User user, Long contentId) {
        // TODO: 에러코드 추가
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 컨텐츠가 존재하지 않습니다. id=" + contentId));

        content.increaseViewCount();
        contentRepository.save(content);

        return buildUserBasedResponse(content, user);
    }

    public void deleteContent(Long contentId) {
        contentRepository.deleteById(contentId);
    }

    public void updateLike(User user, Long contentId) {
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 컨텐츠가 존재하지 않습니다. id=" + contentId));

        contentLikeRepository.findByUserAndContent(user, content)
                .ifPresentOrElse(
                        (found) -> contentLikeRepository.delete(found),
                        () -> {
                            ContentLike contentLike = ContentLike.of(content, user);
                            contentLikeRepository.save(contentLike);
                        }
                );
    }

    private ContentDTO.Response buildUserBasedResponse(Content content, User user) {
        ContentDTO.Response response = ContentDTO.Response.from(content);
        boolean isLiked = contentLikeRepository.existsByUserAndContent(user, content); // TODO: when user is null
        if(isLiked) {
            response.updateIsLike();
        }
        return response;
    }
}
