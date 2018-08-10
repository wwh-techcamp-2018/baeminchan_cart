export class ProductList{
    constructor() {
        document.addEventListener("DOMContentLoaded", this.RequestCartList.bind(this));
    }

    RequestCartList() {
        fetchManager({
            url: '/carts',
            method: 'GET',
            headers: { 'content-type': 'application/json'},
            callback: this.onCompleteCartRequest.bind(this),
            errCallback: this.onFailureCartRequest.bind(this)
        });
    }

    onCompleteCartRequest(response) {
        response.json().then((carts) => {
            let totalPrice = 0;
            carts.forEach((cart) => {
                totalPrice += cart.product.price;
                $("#prd-list-tbody").insertAdjacentHTML("beforeend", this.getProductHTML(cart));
                $("#salePrice").innerText = totalPrice;
            });

        });
    }

    onFailureCartRequest() {
        console.log("실패");
    }

    getProductHTML(cart) {
        return `<tr class="cart_prd_list" cart_no="12585780" data-hash="I9D6D" data-subscription-flag="0" data-delivery-limit-periods-flag="1"
                                data-delivery-limit="2018-07-19~2018-08-15">
                                <td>
                                    <label class="custom_checkbox on">
                                        <i class="chk_box"></i>
                                        <input type="checkbox" name="cart_select" class="bf hidden_chk">
                                    </label>
                                </td>
                                <td class="thumb">
                                    <a href="/shop/detail.php?hash=I9D6D&amp;cno=30011800">
                                        <img src="${cart.product.imgUrl}">
                                    </a>
                                </td>
                                <td class="left">
                                    <p class="title prd_name">
                                        <a href="/shop/detail.php?hash=I9D6D&amp;cno=30011800">${cart.product.title}</a>
                                    </p>
                                    <p class="soldout bf_red" style="display: none;">
                                        <strong class="soldout-text"></strong>
                                    </p>

                                    <p class="cartItemMessage bf_red" style="display: none;"></p>
                                    <p class="receipt_limit bf_red" style="">
                                        배송 가능일이 한정되어 있는 상품입니다.
                                        <strong class="limit-date">
                                            <br>2018-07-19~2018-08-15</strong>
                                    </p>
                                </td>
                                <td>
                                    <span cartdata="" id="productPrice">${cart.product.price}</span>원</td>
                                <td>
                                    <div class="prd_account">
                                        <label>
                                            <input type="number" class="buy_cnt" min="1" max="999" value="${cart.quantity}" data-quantity="4"
                                                formnovalidate="">
                                        </label>
                                        <span>
                                            <a title="수량 더하기" role="increase" class="up quantity_change">수량 더하기</a>
                                            <a title="수량 빼기" role="decrease" class="down quantity_change">수량 빼기</a>
                                        </span>
                                    </div>
                                    <button type="button" class="btn btn_white quantity_change_confirm">변경</button>
                                </td>
                                <td>
                                    <span cartdata="origin/sum_final_sell_prc" id="sumSalePrice">${cart.quantity * cart.product.price}</span>원</td>
                                <td>
                                    <span class="once_only bf_red">정기배송 불가</span>
                                </td>
                                <td>
                                    <a class="ico-delete delete_cart_item" style="cursor:pointer">삭제</a>
                                </td>
                            </tr>
        `;
    }
}