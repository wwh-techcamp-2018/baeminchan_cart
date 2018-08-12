const productCounter = new ProductCounter();
productCounter.delegateFrom($('.tb_order_style.cart'));

const cart = new Cart();

function initCartPrices() {
    document.querySelectorAll('.cart_prd_list')
        .forEach(row => updateSumPrice({ target: row }, false));
    updateTotalPrice();
}

function updateSumPrice({ target }, update = true) {
    const productRow = target.closest('tr');
    const productId = productRow.id.split('_').pop();
    const price = parseInt(productRow.querySelector('#productPrice').innerText);
    const count = parseInt(productRow.querySelector('.prd_account .buy_cnt').value);

    productRow.querySelector('#sumSalePrice').innerText = (price * count).toLocaleString();
    update && cart.updateProduct(productId, count).then(updateTotalPrice);
}

function updateTotalPrice() {
    const FREE_SHIP_THRESHOLD = 40000;
    const DEFAULT_SHIP_FEE = 2500;
    const salePrice = [...document.querySelectorAll('#sumSalePrice')]
                         .map(toPrice)
                         .reduce((sum, price) => sum += price, 0);

    const shippingFee = $('#shippingFee');
    shippingFee.innerText = FREE_SHIP_THRESHOLD <= salePrice ? 0 : DEFAULT_SHIP_FEE;
    $('#salePrice').innerText = salePrice.toLocaleString();
    $('#totalPrice').innerText = (salePrice + toPrice(shippingFee)).toLocaleString();
}

function deleteClickedProduct({ target }) {
    if (!target.classList.contains('delete_cart_item')) {
        return;
    }
    const productRow = target.closest('tr');
    const productId = productRow.id.split('_').pop();
    const count = parseInt(productRow.querySelector('.prd_account .buy_cnt').value);
    cart.deleteProduct(productId, count)
        .then(() => productRow.remove());
}

function deleteSelectedProducts() {
    const deleteList = [...document.querySelectorAll('tbody .custom_checkbox.on')]
                        .map(checkbox => {
                            const productId = checkbox.closest('tr').id.split('_').pop();
                            return cart.deleteProduct(productId);
                        });
    Promise.all(deleteList)
        .then(() => location.reload());
}

function toggleSelect(e) {
    if (!e.target.name === 'cart_select') {
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

function toPrice(element) {
    return parseInt(element.innerText.replace(/\D/g, '') || 0);
}

document.addEventListener('DOMContentLoaded', initCartPrices);
$('#cart').addEventListener('click', updateSumPrice);
$('#cart').addEventListener('keyup', updateSumPrice);
$('#cart').addEventListener('click', toggleSelect);
$('#cart').addEventListener('click', deleteClickedProduct);
$('#delete_select').addEventListener('click', deleteSelectedProducts);