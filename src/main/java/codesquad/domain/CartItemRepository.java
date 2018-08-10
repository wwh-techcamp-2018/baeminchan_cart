package codesquad.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    //유저 별 장바구니가 생기기 때문에 유저, 상품으로 조회
    Optional<CartItem> findByUserAndProduct(User user, Product product);
}
