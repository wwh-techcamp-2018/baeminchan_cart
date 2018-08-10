package codesquad.exception;

public class ResourceNotFoundException extends RuntimeException{

    private String msg;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String msg) {
        this.msg = msg;
    }
}
