function $(selector) {
    return document.querySelector(selector);
}

function getCartList() {
    fetch('/api/cart', { credentials: "same-origin" })
        .then(response => response.json())
        .then(renderCartList)
}

function handleProductClicked({ target }) {
    const cartButton = target.closest('.btn.cart');
    const countUpButton = target.closest('.prd_account .up');
    const countDownButton = target.closest('.prd_account .down');

    cartButton && addCart({ target });
    countUpButton && updateProductCount({ target }, 1);
    countDownButton && updateProductCount({ target }, -1);
}

function addCart({ target }) {
    if (!target.classList.contains('btn') || !target.classList.contains('cart')) {
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

function updateProductCount({ target }, count) {
    const countInput = target.closest('.prd_account').querySelector('.buy_cnt');
    countInput.value = parseInt(countInput.value) + count;
    validateProductCount({ target: countInput });
}

function validateProductCount({ target }) {
    if (!target.classList.contains('buy_cnt')) {
        return;
    }
    if (isNaN(target.value) || parseInt(target.value) < 1) {
        target.value = 1;
    }
}

function countProductByArrowKey({ target, key }) {
    switch (key) {
        case "ArrowUp":
            updateProductCount({ target }, 1);
            break;
        case "ArrowDown":
            updateProductCount({ target }, -1);
            break;
    }
}

function renderCartList({ data }) {
    const totalCount = Object.values(data).reduce((sum, count) => sum += count, 0);
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
$('#products').addEventListener('click', handleProductClicked);
$('#products').addEventListener('input', validateProductCount);
$('#products').addEventListener('change', validateProductCount);
$('#products').addEventListener('keyup', countProductByArrowKey);