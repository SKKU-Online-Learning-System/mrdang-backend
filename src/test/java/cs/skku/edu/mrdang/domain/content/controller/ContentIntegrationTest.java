package cs.skku.edu.mrdang.domain.content.controller;

import cs.skku.edu.mrdang.domain.content.dto.ContentDTO;
import cs.skku.edu.mrdang.domain.content.entity.ContentType;
import cs.skku.edu.mrdang.domain.content.entity.Tag;
import cs.skku.edu.mrdang.domain.content.repository.TagRepository;
import cs.skku.edu.mrdang.domain.content.service.ContentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@ActiveProfiles("test")
@SpringBootTest
public class ContentIntegrationTest {

    @Autowired
    private ContentService contentService;

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    @Test
    void testTagDeleting() {
        ContentDTO.CreateRequest request = ContentDTO.CreateRequest.builder()
                .type(ContentType.YOUTUBE)
                .title("입학과 동시에 삼성 취업보장!? 지능형소프트웨어학과 촬영스케치_2편│꽃보다교수│성균관대학교")
                .description("성균관대학교 소프트웨어융합대학의 꽃,\n네 분의 교수님들의 꽃같은 케미!\n그 촬영 현장을 함께 살펴보실까요?\n\n\n–––––––––––––––––––––––––––––––––\n[Video Source Support]\nYoutube channel \"freeticon\" :   \n\n https://www.youtube.com/c/Freeticon\n–––––––––––––––––––––––––––––––––")
                .author("SW중심대학사업단_소프트웨어융합대학")
                .duration(100L)
                .link("https://www.youtube.com/watch?v=BAHaRkTgiV0")
                .thumbnailUrl("https://i.ytimg.com/vi/BAHaRkTgiV0/default.jpg")
                .tags(List.of("성균관대학교", "소프트웨어융합대학", "꽃보다교수"))
                .build();

        ContentDTO.CreateRequest request2 = ContentDTO.CreateRequest.builder()
                .type(ContentType.YOUTUBE)
                .title("입학과 동시에 삼성")
                .description("성균관대학교")
                .author("SW")
                .duration(100L)
                .link("https://www.youtube.com/watch?v=BAHaRkTgiV0")
                .thumbnailUrl("https://i.ytimg.com/vi/BAHaRkTgiV0/default.jpg")
                .tags(List.of("성균관대학교", "소프트웨어융합대학", "안잔다"))
                .build();

        Long contentId1 = contentService.createContent(request);
        Long contentId2 = contentService.createContent(request2);


        List<Tag> tags = tagRepository.findAll();

        for (Tag tag : tags) {
            System.out.println(tag.getName());
        }

        contentService.deleteContent(contentId1);
        contentService.deleteContent(contentId2);
    }


    @Transactional
    @Test
    void testViewCount() {
        ContentDTO.CreateRequest request = ContentDTO.CreateRequest.builder()
                .type(ContentType.YOUTUBE)
                .title("입학과 동시에 삼성 취업보장!? 지능형소프트웨어학과 촬영스케치_2편│꽃보다교수│성균관대학교")
                .description("성균관대학교 소프트웨어융합대학의 꽃,\n네 분의 교수님들의 꽃같은 케미!\n그 촬영 현장을 함께 살펴보실까요?\n\n\n–––––––––––––––––––––––––––––––––\n[Video Source Support]\nYoutube channel \"freeticon\" :   \n\n https://www.youtube.com/c/Freeticon\n–––––––––––––––––––––––––––––––––")
                .author("SW중심대학사업단_소프트웨어융합대학")
                .duration(100L)
                .link("https://www.youtube.com/watch?v=BAHaRkTgiV0")
                .thumbnailUrl("https://i.ytimg.com/vi/BAHaRkTgiV0/default.jpg")
                .tags(List.of("성균관대학교", "소프트웨어융합대학", "꽃보다교수"))
                .build();

        Long contentId = contentService.createContent(request);

        ContentDTO.Response response = contentService.getContent(contentId);
        assert response.getViewCount() == 1;
    }

    @Transactional
    @Test
    void testParsingYoutubeVideoId() {
        ContentDTO.CreateRequest request = ContentDTO.CreateRequest.builder()
                .type(ContentType.YOUTUBE)
                .title("입학과 동시에 삼성 취업보장!? 지능형소프트웨어학과 촬영스케치_2편│꽃보다교수│성균관대학교")
                .description("성균관대학교 소프트웨어융합대학의 꽃,\n네 분의 교수님들의 꽃같은 케미!\n그 촬영 현장을 함께 살펴보실까요?\n\n\n–––––––––––––––––––––––––––––––––\n[Video Source Support]\nYoutube channel \"freeticon\" :   \n\n https://www.youtube.com/c/Freeticon\n–––––––––––––––––––––––––––––––––")
                .author("SW중심대학사업단_소프트웨어융합대학")
                .duration(100L)
                .link("https://www.youtube.com/watch?v=BAHaRkTgiV0")
                .thumbnailUrl("https://i.ytimg.com/vi/BAHaRkTgiV0/default.jpg")
                .tags(List.of("성균관대학교", "소프트웨어융합대학", "꽃보다교수"))
                .build();

        Long contentId = contentService.createContent(request);
        ContentDTO.Response response = contentService.getContent(contentId);

        assert response.getYoutubeVideoId().equals("BAHaRkTgiV0");
    }
}
