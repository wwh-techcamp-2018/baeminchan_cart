const cart = new Cart();
cart.getProducts();

const productCounter = new ProductCounter();
productCounter.delegateFrom($('.desc_option_calc .prd_account'));

function addProductToCart(e) {
    const productId = location.pathname.split('/').pop().replace(/\D/g, '');
    const count = $('.prd_account .buy_cnt').value;
    cart.addProduct(productId, count);
}

$('.btn_into_basket.purchageable').addEventListener('click', addProductToCart);