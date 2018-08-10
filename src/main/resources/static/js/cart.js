const totalAmount = $("#basket-counter");

function initEvents() {
    totalAmount.value = 0;
    $('.btn.btn_gray.prd_thumb_btn.cart').addEventListener('click', holdItem);

}

function holdItem(e) {
    const listElement = e.target.closest("li");
    const productId = listElement.querySelector("div > a").href.split('/').pop();
    const amount = listElement.querySelector(".buy_cnt").value; //$(".buy_cnt").value;

    fetch('/cart/hold', {
        method: 'post',
        headers: {'content-type': 'application/json'},
        credentials: 'same-origin',
        body: JSON.stringify({
            productId,
            amount
            })
        })
        .then(response => {
            if (!response.ok) {
                return;
            }
            alert("장바구니에 물건이 담겼습니다.");
            return updateCart(response.json());
        })
        .catch(handleError);

}

function updateCart(data) {
     totalAmount.value = data.count;
}

function handleError() {

}

document.addEventListener('DOMContentLoaded', initEvents);
