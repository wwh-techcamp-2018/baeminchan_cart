
function onDOMContentLoaded() {
    getCartItemCount();

    const addToCartButtons = document.querySelectorAll("#products .btn.cart");
    addToCartButtons.forEach((button) => { button.addEventListener("click", addToCart) });
}

function getCartItemCount() {
    fetchManager({
        url: "/api/cart/item/count",
        method: "GET",
        callback: onGetCartItemCount
    });
}

function onGetCartItemCount(result) {
    result.json().then(count => {
        if (count > 0) {
            showCartItemCount(count);
        } else {
            hideCartItemCount();
        }
    });
}

function addToCart({ target }) {
    const productLi = target.closest("li");
    const url = productLi.querySelector(".prd_tlt > a").href;

    const productId = url.split("/").pop();
    const quantity = productLi.querySelector("input.buy_cnt").value;

    const dto = {
        "productId": productId,
        "quantity": quantity
    };

    fetchManager({
        url: "/api/cart/item",
        method: "POST",
        headers: {"content-type": "application/json"},
        body: JSON.stringify(dto),
        callback: onAddToCart
    });
}

function onAddToCart(result) {
    getCartItemCount();
    animationBasketToaster();
}

function animationBasketToaster() {
    const toaster = $('#basket-toaster');
    toaster.classList.add('active');
    setTimeout(() => toaster.classList.remove('active'), 1000);
}

function showCartItemCount(count) {
    $("#basket-counter").innerHTML = count;
    $("#cart_display_exist").style.display = "block";
    $("#cart_display_none").style.display = "none";
}

const MIN_CART_ITEM_COUNT = 0;
function hideCartItemCount() {
    $("#basket-counter").innerHTML = MIN_CART_ITEM_COUNT;
    $("#cart_display_exist").style.display = "none";
    $("#cart_display_none").style.display = "block";
}

document.addEventListener("DOMContentLoaded", onDOMContentLoaded);