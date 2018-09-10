package codesquad.web;

import codesquad.common.ResponseModel;
import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.LoginDTO;
import codesquad.dto.UserDTO;
import codesquad.support.test.AcceptanceTest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class UserControllerAcceptanceTest extends AcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(UserControllerAcceptanceTest.class);

    public static final String SIGNUP_URL = "/users/signup";
    public static final String LOGIN_URL = "/users/login";
    public static final String DEFAULT_EMAIL = "javajigi@woowahan.com";
    public static final String DEFAULT_PASSWORD = "87654321";

    private UserDTO dto;
    private UserDTO signupDTO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        userRepository.deleteAll();
        signupDTO = new UserDTO(DEFAULT_EMAIL, DEFAULT_PASSWORD, DEFAULT_PASSWORD, "javajigi", "010-1234-5678");
        userRepository.save(User.valueOf(signupDTO, passwordEncoder));
    }

    @Test
    public void signup() throws Exception {
        dto = new UserDTO("javajigi@tech.com", "12345678", "12345678", "javajigi", "010-1234-5678");
        requestSuccessProcess(SIGNUP_URL, dto);
    }

    @Test
    public void signupAssertPassword() {
        dto = new UserDTO("javajigi@tech.com", "12345678", "12345679", "javajigi", "010-1234-5678");
        requestFailProcess(SIGNUP_URL, dto);
    }

    @Test
    public void signupInvalidUserDTO() {
        dto = new UserDTO("javajigitech.com", "123456", "12345679", "javajigi", "010-1234-5678");
        requestFailProcess(SIGNUP_URL, dto);
    }

    @Test
    public void duplicateSignup() {
        requestFailProcess(SIGNUP_URL, signupDTO);
    }

    @Test
    public void login() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(signupDTO.getEmail());
        loginDTO.setPassword(signupDTO.getPassword());

        requestSuccessProcess(LOGIN_URL, loginDTO);
    }

    @Test
    public void loginValidationEmail() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("something");
        loginDTO.setPassword("12345678");
        requestFailProcess(LOGIN_URL, loginDTO);

    }

    @Test
    public void loginValidationPassword() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(signupDTO.getEmail());
        loginDTO.setPassword("somethingwrong");
        requestFailProcess(LOGIN_URL, loginDTO);
    }


    private void requestFailProcess(String url, Object body) {
        ResponseEntity<ResponseModel<List<Error>>> response = requestJson(url, HttpMethod.POST, body, new ParameterizedTypeReference<ResponseModel<List<Error>>>() {
        }, null);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private void requestSuccessProcess(String url, Object body) {
        ResponseEntity<Void> responseEntity = template().postForEntity(url, body, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}