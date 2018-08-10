//package codesquad.domain;
//
//import lombok.*;
//
//import javax.persistence.*;
//import javax.validation.constraints.Min;
//
//@Entity
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//public class Cart {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    @Min(1)
//    private Long numberOfProduct;
//
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cart")
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//}


