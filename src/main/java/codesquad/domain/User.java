package codesquad.domain;

import codesquad.dto.UserDTO;
import codesquad.exception.UserVerificationException;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Entity
@Builder
@AllArgsConstructor
@ToString
public class User {
    public static final String FIELD_NAME_EMAIL = "email";
    public static final String FIELD_NAME_PASSWORD = "password";

    public static User defaultUser = User.builder()
            .id(1)
            .email("junsulime@woowahan.com")
            .password("junsulime")
            .build();

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Column(length = 40, unique = true, nullable = false, updatable = false)
    private String email;

    @Getter
    @Column(nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 14, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserPermissions permissions;

    public User() {
        permissions = UserPermissions.NORMAL;
    }

    public User(long id, String email, String password, String name, String phoneNumber) {
        this(email, password, name, phoneNumber);
        this.id = id;
    }

    public User(String email, String password, String name, String phoneNumber) {
        this();
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void matchPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(rawPassword, password))
            throw new UserVerificationException(FIELD_NAME_PASSWORD, "비밀번호를 확인하기 바랍니다.");
        ;
    }

    public static User valueOf(UserDTO userDTO, PasswordEncoder passwordEncoder) {
        if (!userDTO.passwordConfirm())
            throw new UserVerificationException(FIELD_NAME_PASSWORD, "비밀번호를 확인하기 바랍니다.");
        return new User(userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()), userDTO.getName(), userDTO.getPhoneNumber());
    }


//    public ProductBundle saveProductBundle(ProductBundle productBundle) {
//        Optional<ProductBundle> maybeBundle = productBundles
//                .stream()
//                .filter(productBundle1 -> productBundle.isSameProduct(productBundle))
//                .findFirst();
//
//        if (maybeBundle.isPresent())
//            return maybeBundle.get().update(productBundle);
//
//        productBundles.add(productBundle);
//        return productBundle;
//    }
//
//    public ProductBundle removeProductBundle(ProductBundle productBundle) {
//        ProductBundle bundle = productBundles
//                .stream()
//                .filter(productBundle1 -> productBundle.isSameProduct(productBundle))
//                .findFirst()
//                .orElseThrow(RuntimeException::new);
//        productBundles.remove(bundle);
//        return productBundle;
//    }
}
