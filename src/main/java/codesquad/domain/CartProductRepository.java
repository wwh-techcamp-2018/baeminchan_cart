package codesquad.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartProductRepository extends CrudRepository<CartProduct, Long> {
    Optional<CartProduct> findByUserAndProduct(User loginUser, Product product);
}
