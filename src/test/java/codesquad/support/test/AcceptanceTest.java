package codesquad.support.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

    @Autowired
    private TestRestTemplate template;

    public TestRestTemplate template() {
        return template;
    }

    protected ResponseEntity request(TestRestTemplate template, RequestEntity requestEntity) {
        return template.exchange(
                requestEntity.getUrl(),
                requestEntity.getMethod(),
                requestEntity.getBody(),
                requestEntity.getReturnType()
        );
    }
}

