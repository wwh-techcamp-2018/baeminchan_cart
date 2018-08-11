package codesquad.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor @Builder
public class ApiError {
    private String errorMsg;
    @JsonIgnore
    private String debugErrorMsg;
}
