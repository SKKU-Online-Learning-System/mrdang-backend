package cs.skku.edu.mrdang.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ErrorResponse> handleRestException(RestException restException) {
        final ErrorCode errorCode = restException.getErrorCode();
        final ErrorResponse errorResponse = ErrorResponse.from(errorCode);

        return ResponseEntity
                .status(HttpStatus.valueOf(errorCode.getStatus()))
                .body(errorResponse);
    }
}
