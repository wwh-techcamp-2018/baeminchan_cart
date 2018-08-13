package codesquad.web;

public class RestError {

    private String message;

    public RestError() {
    }

    public RestError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
