package codesquad.domain;


import codesquad.exception.UserVerificationException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserTest {

    private User user;

    private PasswordEncoder passwordEncoder;

    private String password;

    @Before
    public void setUp() throws Exception {
        password = "12345678";
        passwordEncoder = new BCryptPasswordEncoder();
        user = new User("javajigi@tech.com", passwordEncoder.encode(password), "javajigi", "010-1234-5678");
    }

    @Test(expected = UserVerificationException.class)
    public void passwordMatch() {
        user.matchPassword(password + 1, passwordEncoder);
    }

}
