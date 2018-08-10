function $(selector) {
    return document.querySelector(selector);
}

function getCartList() {
    fetch('/api/cart', { credentials: "same-origin" })
        .then(response => response.json())
        .then(renderCartList)
}

function addCart(e) {
    const target = e.target.closest('.btn.cart');
    if (!target || !target.classList.contains('btn') || !target.classList.contains('cart')) {
        return;
    }

    const productRef = target.closest('li').querySelector('.imgthumb > a');
    const productId = productRef.href.split('/').pop();
    const productCount = target.closest('li').querySelector('input.buy_cnt').value;

    fetch(`/api/cart/products/${productId}${productCount && '?count=' + productCount}`,
    { method: 'post', credentials: "same-origin" })
        .then(response => response.json())
        .then(renderCartList)
        .then(animateBasketToaster)
}

function renderCartList({ data }) {
    const totalCount = Object.values(data).reduce((sum, count) => sum += count);
    totalCount ? displayExist(totalCount) : displayNotExist();
}

function displayExist(length) {
    $('#basket-counter').innerText = length;
    $('#cart_display_none').style.display = 'none';
    $('#cart_display_exist').style.display = 'block';
}

function displayNotExist() {
    $('#cart_display_exist').style.display = 'none';
    $('#cart_display_none').style.display = 'block';
}

function animateBasketToaster() {
    const toaster = $('#basket-toaster');
    toaster.classList.add('active');
    setTimeout(() => toaster.classList.remove('active'), 1000);
}

document.addEventListener('DOMContentLoaded', getCartList);
$('#products').addEventListener('click', addCart);