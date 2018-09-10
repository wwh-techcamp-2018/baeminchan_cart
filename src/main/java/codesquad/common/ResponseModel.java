package codesquad.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel<T> {
    private T data;
    private List<Error> errors;

    private ResponseModel(T data) {
        this.data = data;
    }

    private ResponseModel(List<Error> errors) {
        this.errors = errors;
    }

    public static <T> ResponseModel<T> ofSuccess(T data) {
        return new ResponseModel<>(data);
    }

    public static ErrorResponseBuilder error() {
        return new ErrorResponseBuilder();
    }

    public static ErrorResponseBuilder error(String field, String message) {
        return new ErrorResponseBuilder(new Error(field, message));
    }

    public static ErrorResponseBuilder error(String message) {
        return error(null, message);
    }

    @NoArgsConstructor
    public static class ErrorResponseBuilder {
        private List<Error> errors = new ArrayList<>();

        private ErrorResponseBuilder(Error error) {
            errors.add(error);
        }

        public ErrorResponseBuilder addError(String field, String message) {
            errors.add(new Error(field, message));
            return this;
        }

        public ErrorResponseBuilder addError(String message) {
            return this.addError(null, message);
        }

        public ResponseModel<?> build() {
            return new ResponseModel<>(this.errors);
        }
    }

    @Getter
    @NoArgsConstructor
    public static class Error {
        private String field;
        private String message;

        public Error(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}
