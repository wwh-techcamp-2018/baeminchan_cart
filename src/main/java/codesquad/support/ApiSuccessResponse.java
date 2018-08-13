package codesquad.support;

import codesquad.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiSuccessResponse<T> {
   private HttpStatus status;
   private T data;
   private String msg;
}
