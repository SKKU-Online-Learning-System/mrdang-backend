package cs.skku.edu.mrdang.domain.user.entity;

import cs.skku.edu.mrdang.security.sso.SSOUser;
import cs.skku.edu.mrdang.util.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에 생성을 위임한다.
    private Long id;

    private String name;

    private String glsId;

    @Setter
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Comment("학번 - 학생과 교직원은 학번의 유무(N/A)로 구분함.")
    private String studentId;

    @Comment("e.g. 소프트웨어학과/지능형소프트웨어학과...")
    private String academicDepartment;
    @Comment("e.g. 학사/석사...")
    private String academicDegree;
    @Comment("e.g. 재학/휴학...")
    private String academicStatus;

    @Comment("employee~ 는 교직원 전용필드")
    private String employeeId;
    private String employeeGroup;

    public static User from(SSOUser ssoUser) {
        return User.builder()
                .name(ssoUser.getUserName())
                .glsId(ssoUser.getUserId())
                .role(ssoUser.isStudent() ? UserRole.STUDENT : UserRole.ADMIN)
                .studentId(ssoUser.getStudentId())
                .academicDepartment(ssoUser.getDepartment())
                .academicDegree(ssoUser.getDegree())
                .academicStatus(ssoUser.getStatus())
                .employeeId(ssoUser.getEmployeeId())
                .employeeGroup(ssoUser.getGroup())
                .build();
    }

    public static User of(String glsId, UserRole role) {
        return User.builder()
                .glsId(glsId)
                .role(role)
                .build();
    }

    public boolean isAdmin() {
        return role.isAdmin() || role.isMaster();
    }

    public boolean isMaster() {
        return role.isMaster();
    }
}
