package codesquad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BaeminchanApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaeminchanApplication.class, args);
    }
}


// 장바구니 기능 요구사항
//
// 반찬 목록 페이지(필수), 반찬 상세 페이지(선택)
// 수량은 1 to ~
// 수량 변동시 실시간 가격 변동
// 담기 버튼 ajax
// 담기 버튼 resp => UI에 갯수가 표시되고 바로 하단에 담기완료라는 layer가 애니메이션과 함께 나타났다가 사라짐. (실서비스 참고)
// 로그인 하지 않아도 장바구니 가능
// 로그아웃 -> 로그인시 장바구니 유지
//
// 관리자는 각 사용자가 장바구니에 상품 등록 이력을 추적해 추후 상품 발굴, 사용자 패턴을 분석하는데 활용하고 싶다.
// 상품을 등록한 후 구매까지 이어졌는지의 여부, 상품이 추가되었다가 바로 취소되는 경우 등등 향후 데이터 분석을 위해 필요한 데이터를 쌓는다.

// 반찬 가격 결정 정책
//
// 각 반찬마다 할인율이 있다.
// 같은 반찬을 10개이상 주문하면 '기존 할인율'에서 5% 추가 할인한다. (20% 이상은 제외)
// 무료배송 기준은 4만원 이상

// 비즈니스 TDD 필수, Acceptance 는 선택