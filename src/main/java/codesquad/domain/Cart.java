package codesquad.domain;

import codesquad.dto.CartProductDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class Cart {
    private Map<Long, Long> productsWithQuantity = new HashMap<>();

    public void add(CartProductDTO cartProductDTO) {
        Long productId = cartProductDTO.getProductId();
        Long quantity = cartProductDTO.getQuantity();
        if (!hasProduct(productId)) {
            productsWithQuantity.put(productId, 0L);
        }
        productsWithQuantity.put(productId, productsWithQuantity.get(productId) + quantity);
        log.info("{} 카트에 추가하였습니다.", cartProductDTO.toString());
        log.info("카트 현황: {}", productsWithQuantity.toString());
    }

    public void update(CartProductDTO cartProductDTO) {
        Long productId = cartProductDTO.getProductId();
        Long quantity = cartProductDTO.getQuantity();
        if (hasProduct(productId)) {
            productsWithQuantity.put(productId, quantity);
            log.info("{} 카트에서 변경하였습니다.", cartProductDTO.toString());
        }
        log.info("카트 현황: {}", productsWithQuantity.toString());
    }

    public void delete(CartProductDTO cartProductDTO) {
        Long productId = cartProductDTO.getProductId();
        if (hasProduct(productId)) {
            productsWithQuantity.remove(productId);
            log.info("{} 카트에서 삭제하였습니다.", cartProductDTO.toString());
        }
        log.info("카트 현황: {}", productsWithQuantity.toString());
    }

    public boolean hasProduct(Long productId) {
        return productsWithQuantity.containsKey(productId);
    }

    public boolean hasProduct(Long productId, Long quantity) {
        return hasProduct(productId) ? productsWithQuantity.get(productId) == quantity : false;
    }

    public List<CartProductDTO> toProductDTOList() {
        return productsWithQuantity.entrySet().stream()
                .map(x -> new CartProductDTO(x.getKey(), x.getValue()))
                .collect(Collectors.toList());
    }
}
