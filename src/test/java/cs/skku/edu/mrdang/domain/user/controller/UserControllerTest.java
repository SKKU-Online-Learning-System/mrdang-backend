package cs.skku.edu.mrdang.domain.user.controller;

import cs.skku.edu.mrdang.domain.common.BaseControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
class UserControllerTest extends BaseControllerTest {

}