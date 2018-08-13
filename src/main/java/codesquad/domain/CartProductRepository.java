package codesquad.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.OrderBy;
import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    @OrderBy("created_at")
    List<CartProduct> findByCartId(Long cartId);
}
