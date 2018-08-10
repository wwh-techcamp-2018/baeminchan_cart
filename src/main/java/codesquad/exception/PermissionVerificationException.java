package codesquad.exception;

public class PermissionVerificationException extends RuntimeException {
    private final String PERMISSION = "PERMISSION";
    private ValidationError error;

    public PermissionVerificationException(String errorMessage) {
        error = new ValidationError(PERMISSION, errorMessage);
    }

    public ValidationError getError() {
        return error;
    }
}
