package codesquad.service;

import codesquad.domain.CartRepository;
import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.LoginDTO;
import codesquad.exception.UserVerificationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
    @Mock
    private CartRepository cartRepository;
    @InjectMocks
    private CartService cartService;

    @Before
    public void setUp() throws Exception {

    }
/*
    @Test(expected = UserVerificationException.class)
    public void addCartProduct_비회원() {
        when(userRepository.findByEmail("javajigi@tech.com")).thenReturn(Optional.ofNullable(user));
        userService.isUniqueUser(user.getEmail());
    }
    */
}
