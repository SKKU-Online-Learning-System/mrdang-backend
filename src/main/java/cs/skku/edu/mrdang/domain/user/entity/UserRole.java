package cs.skku.edu.mrdang.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRole {
    STUDENT("ROLE_STUDENT", "학생"),
    ADMIN("ROLE_ADMIN", "교직원"),
    MASTER("ROLE_MASTER", "전체운영자");

    private final String code;
    private final String name;

    public boolean isAdmin() {
        return code.equals("ROLE_ADMIN");
    }

    public boolean isMaster() {
        return code.equals("ROLE_MASTER");
    }
}