package codesquad.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class ApiErrorResponse {
    private HttpStatus status;
    private List<ApiError> errors = new ArrayList<>();
    private String msg;
    private LocalDate time;

    public ApiErrorResponse error(ApiError error){
        errors.add(error);
        return this;
    }
    public ApiErrorResponse error(List<ApiError> apiErrors){
        apiErrors.stream().forEach(x -> errors.add(x));
        return this;
    }
    public ApiErrorResponse error(ApiError... apiErrors){
        Arrays.stream(apiErrors).forEach(x -> errors.add(x));
        return this;
    }
}
