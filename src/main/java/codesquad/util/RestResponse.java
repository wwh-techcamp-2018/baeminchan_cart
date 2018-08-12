package codesquad.util;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class RestResponse<T> {
    @NonNull
    private T data;
}
