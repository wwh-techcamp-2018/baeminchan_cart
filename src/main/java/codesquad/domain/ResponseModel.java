package codesquad.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseModel<T> {
    private T data;

    public ResponseModel(T data) {
        this.data = data;
    }

    public static <T> ResponseModel<T> ofSuccess(T data) {
        return new ResponseModel<>(data);
    }
}
