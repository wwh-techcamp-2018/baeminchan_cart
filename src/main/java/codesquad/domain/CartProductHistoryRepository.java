package codesquad.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartProductHistoryRepository extends JpaRepository<CartProductHistory, Long> {
    Optional<CartProductHistory> findByUserAndProduct(User loginUser, Product product);
}
