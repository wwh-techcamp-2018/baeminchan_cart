package codesquad.domain;

import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Iterable<Cart> findByBuyer(User buyer);
}
