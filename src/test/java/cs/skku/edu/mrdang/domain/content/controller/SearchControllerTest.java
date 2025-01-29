package cs.skku.edu.mrdang.domain.content.controller;

import cs.skku.edu.mrdang.domain.content.dto.ContentDTO;
import cs.skku.edu.mrdang.domain.content.service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class SearchControllerTest {

    @Autowired
    private SearchService searchService;

    @Test
    void invalidUrl() {
        assertThrows(
                RuntimeException.class,
                () -> searchService.searchContent("https://www.naver.com")
        );
    }

    @Test
    void validUrl() {
        ContentDTO.Response res =  searchService.searchContent("https://www.youtube.com/watch?v=i3u2OReIWSU");
        assertEquals("https://www.youtube.com/watch?v=i3u2OReIWSU", res.getLink());
    }

}