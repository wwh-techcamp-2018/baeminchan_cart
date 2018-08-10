class CartListManager {
    constructor() {
        this.priceSum = 0;
        this.cartItems = [];
        this.updateCartItemsInCookie();
        this.appendCartList();
        this.updateTotalPrice();
    }

    updateTotalPrice() {
        $('#totalPrice').innerHTML = this.priceSum;
    }
    updateCartItemsInCookie() {
        this.cartItems = getCookie('cartList') == '' ? [] : JSON.parse(getCookie('cartList'));
    }

    appendCartList() {
        const parent = $('.cart > tbody');
        const cartList = this.cartItems;
        let sum = 0;
        Object.keys(cartList).map(function(objectKey, index) {
            const cartItem = cartList[objectKey];
            const productPriceSum = (cartItem.amount * 1) * (cartItem.price * 1);
            sum += productPriceSum;
            parent.insertAdjacentHTML('beforeend', `<tr class="cart_prd_list" cart_no="12667557" data-hash="H3FBA" data-subscription-flag="0" data-delivery-limit-periods-flag="0" data-delivery-limit="">
                    <td>
                        <label class="custom_checkbox on">
                            <i class="chk_box"></i>
                            <input type="checkbox" name="cart_select" class="bf hidden_chk">
                        </label>
                    </td>
                    <td class="thumb">
                        <a href="/shop/detail.php?hash=H3FBA&amp;cno=30020200">
                            <img src="${cartItem.imgUrl}">
                        </a>
                    </td>
                    <td class="left">
                        <p class="title prd_name"><a href="/shop/detail.php?hash=H3FBA&amp;cno=30020200">${cartItem.title}</a></p>
                        <p class="soldout bf_red" style="display: none;">
                            <strong class="soldout-text"></strong>
                        </p>
                        
                        <p class="cartItemMessage bf_red" style="display: none;"></p>
                        <p class="receipt_limit bf_red" style="display: none;">
                            배송 가능일이 한정되어 있는 상품입니다.
                            <strong class="limit-date"></strong>
                        </p>
                    </td>
                    <td><span cartdata="" id="productPrice">${cartItem.price}</span>원</td>
                    <td>
                        <div class="prd_account">
                            <label><input type="number" class="buy_cnt" min="1" max="999" value="${cartItem.amount}" data-quantity="1" formnovalidate=""></label>
                            <span>
                                <a title="수량 더하기" role="increase" class="up quantity_change">수량 더하기</a>
                                <a title="수량 빼기" role="decrease" class="down quantity_change">수량 빼기</a>
                            </span>
                        </div>
                        <button type="button" class="btn btn_white quantity_change_confirm">변경</button>
                    </td>
                    <td><span cartdata="origin/sum_final_sell_prc" id="sumSalePrice">${productPriceSum}</span>원</td>
                    <td><span class="once_only bf_red">정기배송 불가</span></td>
                    <td><a class="ico-delete delete_cart_item" style="cursor:pointer">삭제</a></td>
                </tr>`);
        });
        this.priceSum = sum;
    }
}

new CartListManager();

