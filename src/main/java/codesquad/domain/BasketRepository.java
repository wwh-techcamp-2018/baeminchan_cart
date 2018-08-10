package codesquad.domain;

import org.springframework.data.jpa.repository.JpaRepository;


public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findByCartIdAndProductIdAndDeletedFalse(Long id, Long id1);
}
