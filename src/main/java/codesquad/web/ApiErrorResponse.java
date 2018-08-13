package codesquad.web;

import codesquad.exception.ApiError;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class ApiErrorResponse {
    private HttpStatus status;
    private List<ApiError> errors = new ArrayList<>();
    private String msg;
    private LocalDate time;
    private ApiErrorResponse(){}
    public static ApiErrorResponse builder(String msg){
        ApiErrorResponse response = new ApiErrorResponse();
        response.time = LocalDate.now();
        response.msg = msg;
        return response;
    }
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
