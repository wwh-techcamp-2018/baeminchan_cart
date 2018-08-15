const cart = new Cart();
const stickyCartView = new StickyCartView();
const productCounter = new ProductCounter();

function addProductToCart(e) {
    const productId = location.pathname.split('/').pop().replace(/\D/g, '');
    const count = $('.prd_account .buy_cnt').value;
    cart.save(productId, count);
}

function updateTotalPrice(e) {
    const totalPrice = $('#detail_total_price');
    const salePrice = parseInt($('.desc_price .sale-price').innerText.replace(/\D/g, ''));
    const quantity = parseInt($('.prd_account .buy_cnt').value);
    totalPrice.innerText = (salePrice * quantity).toLocaleString();
}

document.addEventListener('DOMContentLoaded', () => {
    productCounter.delegateFrom($('.desc_option_calc .prd_account'));
    $('.option_only_quantity').addEventListener('click', updateTotalPrice);
    $('.option_only_quantity').addEventListener('keyup', updateTotalPrice);
    $('.option_only_quantity').addEventListener('input', updateTotalPrice);
    $('.option_only_quantity').addEventListener('change', updateTotalPrice);
    $('.btn_into_basket.purchageable').addEventListener('click', addProductToCart);

    cart.setCounterView(stickyCartView);
    cart.setToasterView(stickyCartView);
    cart.count();

    updateTotalPrice();
});