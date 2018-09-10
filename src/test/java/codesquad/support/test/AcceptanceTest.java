package codesquad.support.test;

import codesquad.common.ResponseModel;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

    @Autowired
    private TestRestTemplate template;

    public TestRestTemplate template() {
        return template;
    }

    protected <T, R> ResponseEntity<ResponseModel<R>> requestJson(String path, HttpMethod method, T body, ParameterizedTypeReference<ResponseModel<R>> typeRef, String cookie) {
        return template().exchange(path, method, new HttpEntity<>(body, getJsonHeader(cookie)), typeRef);
    }

    protected <T> ResponseEntity<Void> requestJson(String path, HttpMethod method, T body, String cookie) {
        return template().exchange(path, method, new HttpEntity<>(body, getJsonHeader(cookie)), Void.class);
    }

    protected HttpHeaders getJsonHeader(String cookie) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (cookie != null) {
            headers.add("cookie", cookie);
        }
        return headers;
    }


}

