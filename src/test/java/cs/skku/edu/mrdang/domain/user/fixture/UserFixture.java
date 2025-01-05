package cs.skku.edu.mrdang.domain.user.fixture;

import cs.skku.edu.mrdang.domain.user.entity.User;
import cs.skku.edu.mrdang.domain.user.entity.UserRole;

public class UserFixture {
    public static final User USER_윤성 = User.builder()
            .id(1L)
            .name("이윤성")
            .glsId("tjd1024")
            .role(UserRole.STUDENT)
            .studentId("2020313052")
            .academicDepartment("소프트웨어학과")
            .academicDegree("학사")
            .academicStatus("재학")
            .employeeId("N/A")
            .employeeGroup("N/A")
            .build();

    public static final User USER_상엽 = User.builder()
            .id(2L)
            .name("김상엽")
            .glsId("tkdduq629")
            .role(UserRole.STUDENT)
            .studentId("2019312635")
            .academicDepartment("소프트웨어학과")
            .academicDegree("학사")
            .academicStatus("재학")
            .employeeId("N/A")
            .employeeGroup("N/A")
            .build();

    public static final User USER = USER_윤성;
}
