package cs.skku.edu.mrdang.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestException extends RuntimeException{

    private ErrorCode errorCode;

    public static RestException from(ErrorCode errorCode) {
        return RestException.builder()
                .errorCode(errorCode)
                .build();
    }
}
