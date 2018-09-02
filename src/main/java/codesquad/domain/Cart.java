package codesquad.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long user_id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="basket_id")
    private List<Basket> basket;

    @Column
    private boolean guest;

    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean deleted;
}
