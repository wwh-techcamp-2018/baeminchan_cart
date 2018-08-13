package codesquad.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.jnlp.PersistenceService;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long categoryId);
}
