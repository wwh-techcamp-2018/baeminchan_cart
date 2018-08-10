package codesquad.domain;

import javax.persistence.*;

@Entity
public class CartItemHistory extends AuditorEntity {
    @Id
    @GeneratedValue
    private Long id;
    //실제 존재하는 상품에 대한 연관관계 가짐
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
    //실제 유저에 대한 연관관계 가짐
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    //실제 카트에 있는 상품을 구매하는 경우 true로 변경
    private boolean isBuy = false;
    //취소되는 경우 isCancel true로 변경
    private boolean isCancel = false;
    private int amount = 0;
}
