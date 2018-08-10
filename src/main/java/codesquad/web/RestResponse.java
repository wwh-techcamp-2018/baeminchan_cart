package codesquad.web;

public class RestResponse<T> {
    private T data;

    public RestResponse() {
    }

    public RestResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
