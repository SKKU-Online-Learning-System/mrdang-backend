package cs.skku.edu.mrdang.domain.content.controller;

import cs.skku.edu.mrdang.domain.content.service.ApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SearchControllerTest {

    @Autowired
    private ApiService apiService;

    @Test
    void tmp() {
        String res = apiService.getContents();
        System.out.println(res);
    }

}