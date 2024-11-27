package cs.skku.edu.mrdang.security.sso;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SSOUser {
    private String userId;
    private String userName;
    private String studentId;
    private String department;
    private String degree;
    private String status;
    private String group;
    private String employeeId;

    public boolean isStudent() {
        return !studentId.contains("N/A"); // N/A가 NULL임..ㅎ
    }
}
