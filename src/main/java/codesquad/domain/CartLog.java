package codesquad.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CartLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, updatable = false)
    private String methodName;

    @Column(nullable = false, updatable = false)
    private Long productId;

    @Column(nullable = false, updatable = false)
    private Integer count;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    public CartLog() {

    }

    public CartLog(String methodName, Long productId, Integer count, LocalDateTime createdAt) {
        this.methodName = methodName;
        this.productId = productId;
        this.count = count;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CartLog{" +
                "id=" + id +
                ", methodName='" + methodName + '\'' +
                ", productId=" + productId +
                ", count=" + count +
                ", createdAt=" + createdAt +
                '}';
    }
}
