const cart = new Cart();
const productCounter = new ProductCounter();

function renderCartProducts() {
    showLoading();
    return cart.findAll()
                .then(({data}) => {
                    const cartList = $('#cart tbody');
                    cartList.innerHTML = '';
                    cartList.insertAdjacentHTML('afterbegin', data.cartProducts.map(productInfoTemplate).join(''));
                    return { totalPrice, shippingFee } = data;
                })
                .then(renderTotalPrice)
                .then(hideLoading)
                .catch(hideLoading);
}

function renderTotalPrice({ sumPrice, shippingFee, totalPrice }) {
    $('#salePrice').innerText = sumPrice.toLocaleString();
    $('#shippingFee').innerText = shippingFee.toLocaleString();
    $('#totalPrice').innerText = totalPrice.toLocaleString();
}

function updateCart({ target }) {
    if (!target.classList.contains('quantity_change_confirm')) {
        return;
    }

    const row = target.closest('tr');
    const productId = row.id.split('_').pop();
    const count = row.querySelector('.prd_account .buy_cnt').value;

    cart.update(productId, count)
        .then(renderCartProducts);
}

function deleteClickedProduct({ target }) {
    if (!target.classList.contains('delete_cart_item')) {
        return;
    }

    const row = target.closest('tr');
    const productId = row.id.split('_').pop();
    const count = row.querySelector('.prd_account .buy_cnt').value;

    cart.delete(productId, count)
        .then(renderCartProducts);
}

function deleteSelectedProducts() {
    const deleteProductIdList = [...document.querySelectorAll('tbody .custom_checkbox.on')]
                        .map(checkbox => checkbox.closest('tr').id.split('_').pop());
    Promise.all(deleteProductIdList.map(cart.delete))
           .then(renderCartProducts);
}

function toggleSelect(e) {
    if (e.target.name !== 'cart_select' && e.target.id !== 'check_all_cart_item') {
        return;
    }
    const checkLabel = e.target.closest('label.custom_checkbox');
    if (e.target.id === 'check_all_cart_item') {
        const allCheckList = [...document.querySelectorAll('label.custom_checkbox')];
        return checkLabel.classList.contains('on') ?
               allCheckList.forEach(checkList => checkList.classList.remove('on')) :
               allCheckList.forEach(checkList => checkList.classList.add('on'));
    }
    checkLabel.classList.toggle('on');
}

function showLoading() {
    $('#loading').style.display = 'block';
}

function hideLoading() {
    $('#loading').style.display = 'none';
}

function productInfoTemplate({ product: {id, title, imgUrl, price, salesPrice, discountRate }, count, sumPrice }) {
    return `<tr id="cart_prd_${id}" class="cart_prd_list">
                <td>
                    <label class="custom_checkbox on">
                        <i class="chk_box"></i>
                        <input type="checkbox" name="cart_select" class="bf hidden_chk">
                    </label>
                </td>
                <td class="thumb">
                    <a href="/product/${id}">
                        <img src="${imgUrl}">
                    </a>
                </td>
                <td class="left">
                    <p class="title prd_name">
                        <a href="/product/${id}">${title}</a>
                    </p>
                    <p class="soldout bf_red" style="display: none;">
                        <strong class="soldout-text"></strong>
                    </p>

                    <p class="cartItemMessage bf_red" style="display: none;"></p>
                    <p class="receipt_limit bf_red" style="display: none;">
                        배송 가능일이 한정되어 있는 상품입니다.
                        <strong class="limit-date">
                            <br>2018-07-19~2018-08-15</strong>
                    </p>
                </td>
                <td>
                    <span cartdata="" id="productPrice">${salesPrice}</span>원</td>
                <td>
                    <div class="prd_account">
                        <label>
                            <input type="text" class="buy_cnt" min="1" max="999" value="${count}" data-quantity=""
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
                    <span cartdata="origin/sum_final_sell_prc" id="sumSalePrice">${sumPrice}</span>원</td>
                <td>
                    <span class="once_only bf_red">정기배송 불가</span>
                </td>
                <td>
                    <a class="ico-delete delete_cart_item" style="cursor:pointer">삭제</a>
                </td>
            </tr>`;
}

productCounter.delegateFrom($('.tb_order_style.cart'));
document.addEventListener('DOMContentLoaded', () => {
    $('#cart').addEventListener('click', updateCart);
    $('#cart').addEventListener('click', toggleSelect);
    $('#cart').addEventListener('click', deleteClickedProduct);
    $('#delete_select').addEventListener('click', deleteSelectedProducts);
    renderCartProducts();
});