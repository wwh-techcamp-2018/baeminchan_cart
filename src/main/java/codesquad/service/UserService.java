package codesquad.service;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.LoginDTO;
import codesquad.dto.UserDTO;
import codesquad.exception.UserVerificationException;
import codesquad.security.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserService {

    @Resource(name = "userRepository")
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void save(UserDTO userDTO) {
        isUniqueUser(userDTO.getEmail());
        User user = User.valueOf(userDTO, passwordEncoder);
        userRepository.save(user);
    }

    public User login(LoginDTO loginDTO) {
        User validUser = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UserVerificationException(User.FIELD_NAME_EMAIL, "입력된 email이 존재하지 않습니다."));
        validUser.matchPassword(loginDTO.getPassword(), passwordEncoder);
        return validUser;
    }

    void isUniqueUser(String email) {
        if (userRepository.findByEmail(email).isPresent())
            throw new UserVerificationException(User.FIELD_NAME_EMAIL, "이미 등록된 사용자 email 입니다.");
    }

}
