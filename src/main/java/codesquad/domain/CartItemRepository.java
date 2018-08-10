package codesquad.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public List<CartItem> findByUserId(Long id);

    public Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
}
