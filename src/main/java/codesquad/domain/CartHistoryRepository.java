package codesquad.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartHistoryRepository extends JpaRepository<CartHistory,Long> {
}
