package codesquad.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductBundleRepository extends CrudRepository<ProductBundle, Long> {

    List<ProductBundle> findAllByUser(User user);

    Optional<ProductBundle> findByUserAndProduct(User user, Product product);
}
