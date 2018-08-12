const cart = new Cart();
cart.getProducts()
    .then(cart.renderCartProducts);

const productCounter = new ProductCounter();
productCounter.delegateFrom($('.desc_option_calc .prd_account'));

function addProductToCart(e) {
    const productId = location.pathname.split('/').pop().replace(/\D/g, '');
    const count = $('.prd_account .buy_cnt').value;
    cart.addProduct(productId, count)
        .then(cart.renderCartProducts)
        .then(cart.animateBasketToaster)
}

function updateTotalPrice(e) {
    const totalPrice = $('#detail_total_price');
    const salePrice = parseInt($('.desc_price .sale-price').innerText.replace(/\D/g, ''));
    const quantity = parseInt($('.prd_account .buy_cnt').value);
    totalPrice.innerText = (salePrice * quantity).toLocaleString();
}

$('.option_only_quantity').addEventListener('click', updateTotalPrice);
$('.option_only_quantity').addEventListener('keyup', updateTotalPrice);
$('.option_only_quantity').addEventListener('input', updateTotalPrice);
$('.option_only_quantity').addEventListener('change', updateTotalPrice);
$('.btn_into_basket.purchageable').addEventListener('click', addProductToCart);