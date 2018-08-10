package codesquad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/** TODO
 * 1. 서버
 *  - 카트 도메인을 만들고 카트에 관한 CRUD 만들기
 *  - 로그인하지 않은 사용자의 장바구니 목록을 로그인하면 그 사용자의 데이터로 저장해주기
 *
 *  - 로그 테이블 만들기
 *
 *
 * 2. 클라이언트
 *  - 담기 클릭 시 해당 상품의 아이디와 함께 서버에 요청
 *  - 반환된 결과 받기
 *      (1) 개수표시
 *      (2) 담기완료 layer 구현
 */
@SpringBootApplication
@EnableJpaAuditing
public class BaeminchanApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaeminchanApplication.class, args);
    }
}
