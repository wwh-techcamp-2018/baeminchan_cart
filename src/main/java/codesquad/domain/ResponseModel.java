package codesquad.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseModel<T> {
    private T data;

    public static <T> ResponseModel<T> ofSuccess(T data) {
        return new ResponseModel<>(data);
    }

    public ResponseModel() {
    }

    public ResponseModel(T data) {
        this.data = data;
    }
}
