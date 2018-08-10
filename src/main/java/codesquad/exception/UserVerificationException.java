package codesquad.exception;

public class UserVerificationException extends RuntimeException {
    private ValidationError error;

    public UserVerificationException(String fieldname, String errorMessage) {
        error = new ValidationError(fieldname, errorMessage);
    }

    public ValidationError getError() {
        return error;
    }
}
