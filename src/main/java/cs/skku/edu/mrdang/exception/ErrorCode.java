package cs.skku.edu.mrdang.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(1001, 404, "요청한 ID에 해당하는 유저가 존재하지 않습니다."),

    FAIL_TO_VALIDATE_TOKEN(9001, 401, "JWT을 검증하는데 실패하였습니다."),
    INVALID_ACCESS_TOKEN(9002, 401, "올바르지 않은 형식의 AccessToken입니다."),
    INVALID_REFRESH_TOKEN(9002, 401, "올바르지 않은 형식의 RefreshToken입니다."),
    EXPIRED_PERIOD_ACCESS_TOKEN(9003, 401, "기한이 만료된 AccessToken입니다."),
    EXPIRED_PERIOD_REFRESH_TOKEN(9004, 401, "기한이 만료된 RefreshToken입니다."),
    INVALID_SSO_TOKEN(9005, 401, "유효하지 않은 SSO정보입니다."),
    INVALID_ADMIN_AUTHORITY(9006, 401, "해당 요청에 대한 권한이 없습니다.");

    private final int code;
    private final int status;
    private final String message;
}
