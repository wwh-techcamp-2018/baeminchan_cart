package codesquad.security;

import codesquad.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SecurityControllerAdvice {
    @ExceptionHandler(UnAuthenticationException.class)
    public ResponseEntity<ErrorResponse> unAuthenticatedAccess(UnAuthenticationException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(NotMatchedException.class)
    public ResponseEntity<ErrorResponse> notMatchedAccess(NotMatchedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(UserVerificationException.class)
    public ResponseEntity<ValidationError> UserVerification(ValidationError validationError) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(validationError);
    }

    @ExceptionHandler(PermissionVerificationException.class)
    public ResponseEntity<ValidationError> PermissionVerification(ValidationError validationError) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(validationError);
    }
}
