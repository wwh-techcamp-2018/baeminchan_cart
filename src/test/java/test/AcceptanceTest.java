package test;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.exception.NotMatchedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class AcceptanceTest {
    private static final String DEFAULT_LOGIN_USER = "serverwizard@naver.com";
    private static final Logger log = LoggerFactory.getLogger(AcceptanceTest.class);

    @Autowired
    private TestRestTemplate template;

    public TestRestTemplate template() {
        return template;
    }

//    public TestRestTemplate basicAuthTemplate() {
//        return basicAuthTemplate(defaultUser());
//    }
}