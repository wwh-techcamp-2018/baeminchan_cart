package codesquad.dto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CartDtoTest {

    private CartDto cartDto;

    @Before
    public void setUp() throws Exception {
        cartDto = new CartDto(1L, 10L);
    }
}