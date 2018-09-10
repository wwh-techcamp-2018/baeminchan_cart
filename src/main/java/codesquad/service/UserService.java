package codesquad.service;

import codesquad.common.Message;
import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.dto.LoginDTO;
import codesquad.dto.UserDTO;
import codesquad.exception.UserVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

    public void save(UserDTO userDTO) {
        isUniqueUser(userDTO.getEmail());
        User user = User.valueOf(userDTO, passwordEncoder);
        userRepository.save(user);
    }

    public User login(LoginDTO loginDTO) {
        User validUser = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UserVerificationException(messageSourceAccessor.getMessage(Message.NOT_EXIST_EMAIL)));
        validUser.matchPassword(loginDTO.getPassword(), passwordEncoder);
        return validUser;
    }

    void isUniqueUser(String email) {
        if (userRepository.findByEmail(email).isPresent())
            throw new UserVerificationException(messageSourceAccessor.getMessage(Message.EXIST_DULICATED_EMAIL));
    }

}
