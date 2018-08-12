const productCounter = new ProductCounter();
productCounter.delegateFrom($('#products'));

const cart = new Cart();
cart.getProducts()
    .then(cart.renderCartProducts);

$('#products').addEventListener('click', addProductToCart);

function addProductToCart({ target }) {
    if (!target.classList.contains('btn') || !target.classList.contains('cart')) {
        return;
    }

    const productTitle = target.closest('li').querySelector('.prds_lst_info .prd_tlt');
    const productId = productTitle.querySelector('a').href.split('/').pop();
    const productCount = target.closest('li').querySelector('input.buy_cnt').value;

    cart.addProduct(productId, productCount)
        .then(cart.renderCartProducts)
        .then(cart.animateBasketToaster)
}