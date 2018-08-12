package codesquad.web;

import codesquad.exception.ApiError;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class ApiSuccessResponse<T> {
    private T data;
    private String msg;
    private LocalDate time;
    private ApiSuccessResponse(){}
    public static ApiSuccessResponse builder(String msg){
        ApiSuccessResponse response = new ApiSuccessResponse();
        response.time = LocalDate.now();
        response.msg = msg;
        return response;
    }
    public ApiSuccessResponse data(T data){
        this.data = data;
        return this;
    }

}
