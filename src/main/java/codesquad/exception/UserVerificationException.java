package codesquad.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserVerificationException extends RuntimeException {
    public UserVerificationException(String message) {
        super(message);
    }
}

