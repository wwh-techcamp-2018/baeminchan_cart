package codesquad.domain;


import codesquad.exception.UserVerificationException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserTest {

    private static final String USER_PASSWORD = "12345678";
    private User user;

    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        passwordEncoder = new BCryptPasswordEncoder();
        user = new User("javajigi@tech.com", passwordEncoder.encode(USER_PASSWORD), "javajigi", "010-1234-5678");
    }

    @Test(expected = UserVerificationException.class)
    public void passwordMisMatch() {
        user.matchPassword(USER_PASSWORD + "9", passwordEncoder);
    }

    @Test
    public void passwordMatch() {
        user.matchPassword(USER_PASSWORD, passwordEncoder);
    }

}
