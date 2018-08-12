
const MIN_PRODUCT_QUANTITY = 1;
const MIN_CART_ITEM_COUNT = 0;

function onDOMContentLoaded() {
    getCartItemCount();

    const addToCartButtons = document.querySelectorAll("#products .btn.cart");
    addToCartButtons.forEach((button) => { button.addEventListener("click", addToCart) });

    const quantityUps = document.querySelectorAll("#products > li .prd_to_basket .up");
    quantityUps.forEach((up) => { up.addEventListener("click", setQuantity) });

    const quantityDowns = document.querySelectorAll("#products > li .prd_to_basket .down");
    quantityDowns.forEach((down) => { down.addEventListener("click", setQuantity) });
}

function getUpQuantity(quantity) {
    return quantity + 1;
}

function getDownQuantity(quantity) {
    if (quantity <= MIN_PRODUCT_QUANTITY)
        return MIN_PRODUCT_QUANTITY;
    return quantity - 1;
}

function setQuantity({ target }) {
    const productLi = target.closest("li");
    const input = productLi.querySelector("input.buy_cnt");

    const quantity = parseInt(input.value);

    if (target.className === "up")
        input.value = getUpQuantity(quantity);
    if (target.className === "down")
        input.value = getDownQuantity(quantity);
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

function hideCartItemCount() {
    $("#basket-counter").innerHTML = MIN_CART_ITEM_COUNT;
    $("#cart_display_exist").style.display = "none";
    $("#cart_display_none").style.display = "block";
}

document.addEventListener("DOMContentLoaded", onDOMContentLoaded);