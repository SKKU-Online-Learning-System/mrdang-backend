package cs.skku.edu.mrdang.util;

import cs.skku.edu.mrdang.domain.content.dto.ContentDTO;
import cs.skku.edu.mrdang.domain.content.entity.ContentType;
import cs.skku.edu.mrdang.domain.content.service.ContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Profile({"local"})
@Component
public class InitialDataLoader implements ApplicationRunner {

    private final ContentService contentService;

    @Transactional
    public void run(ApplicationArguments args) {
        ContentDTO.CreateRequest youtubeContentRequest = ContentDTO.CreateRequest.builder()
                .type(ContentType.YOUTUBE)
                .title("입학과 동시에 삼성 취업보장!? 지능형소프트웨어학과 촬영스케치_2편│꽃보다교수│성균관대학교")
                .description("성균관대학교 소프트웨어융합대학의 꽃,\n네 분의 교수님들의 꽃같은 케미!\n그 촬영 현장을 함께 살펴보실까요?\n\n\n–––––––––––––––––––––––––––––––––\n[Video Source Support]\nYoutube channel \"freeticon\" :   \n\n https://www.youtube.com/c/Freeticon\n–––––––––––––––––––––––––––––––––")
                .author("SW중심대학사업단_소프트웨어융합대학")
                .link("https://www.youtube.com/watch?v=BAHaRkTgiV0")
                .thumbnailUrl("https://i.ytimg.com/vi/BAHaRkTgiV0/default.jpg")
                .tags(List.of("tag1", "tag2")) // Add appropriate tags
                .build();

        ContentDTO.CreateRequest inflearnContentRequest = ContentDTO.CreateRequest.builder()
                .type(ContentType.INFLEARN)
                .title("Practical Testing: 실용적인 테스트 가이드")
                .description("이 강의를 통해 실무에서 개발하는 방식 그대로, 깔끔하고 명료한 테스트 코드를 작성할 수 있게 됩니다. 테스트 코드가 왜 필요한지, 좋은 테스트 코드란 무엇인지 궁금하신 모든 분을 위한 강의입니다.")
                .author("박우빈")
                .link("https://www.inflearn.com/course/practical-testing-%EC%8B%A4%EC%9A%A9%EC%A0%81%EC%9D%B8-%ED%85%8C%EC%8A%A4%ED%8A%B8-%EA%B0%80%EC%9D%B4%EB%93%9C")
                .thumbnailUrl("https://cdn.inflearn.com/public/courses/329295/cover/91207c2c-36ad-4c66-af8e-990193224b8a/329295.png")
                .tags(List.of("tag3", "tag4")) // Add appropriate tags
                .build();

        contentService.createContent(youtubeContentRequest);
        contentService.createContent(inflearnContentRequest);
    }
}