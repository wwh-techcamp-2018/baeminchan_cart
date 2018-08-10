
function successCallback() {
    // animation
    console.log("success");
}

function countUpHandler(evt) {
    const buyCountEl = $(".buy_cnt");
    buyCountEl.value = parseInt(buyCountEl.value) + 1;
}

function countDownHandler(evt) {
    const buyCountEl = $(".buy_cnt");
    buyCountEl.value = parseInt(buyCountEl.value) - 1;
}

function addToCartHandler(evt) {
    const count = $_value(".buy_cnt");
    const addToCartEl = $(".prd_thumb_btn");
    const product_id = addToCartEl.getAttribute("product-id");

    fetchManager({
        url: '/api/cart/' + product_id,
        method: 'PUT',
        body: JSON.stringify({
              count: count
        }),
        headers: { 'content-type': 'application/json'},
        callback: successCallback
    })
}

function initAddToCartEvents() {
    const countUpEl = $(".count_up");
    countUpEl.addEventListener("click", countUpHandler);

    const countDownEl = $(".count_down");
    countDownEl.addEventListener("click", countUpHandler);

    const addToCartEl = $(".prd_thumb_btn");
    addToCartEl.addEventListener("click", addToCartHandler);
}

document.addEventListener("DOMContentLoaded", () => {
    initAddToCartEvents();
})