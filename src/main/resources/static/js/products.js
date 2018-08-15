const cart = new Cart();
const stickyCartView = new StickyCartView();
const productCounter = new ProductCounter();

function addProductToCart({ target }) {
    if (!target.classList.contains('btn') || !target.classList.contains('cart')) {
        return;
    }

    const productTitle = target.closest('li').querySelector('.prds_lst_info .prd_tlt');
    const productId = productTitle.querySelector('a').href.split('/').pop();
    const productCount = target.closest('li').querySelector('input.buy_cnt').value;

    cart.save(productId, productCount);
}

productCounter.delegateFrom($('#products'));
$('#products').addEventListener('click', addProductToCart);

cart.setCounterView(stickyCartView);
cart.setToasterView(stickyCartView);
cart.count();