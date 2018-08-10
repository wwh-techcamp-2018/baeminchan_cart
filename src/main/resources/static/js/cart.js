document.addEventListener("DOMContentLoaded", () => {
    initCart();
})

function getCart(data) {
    console.log(data);
}

function initCart() {
    $(".prds_lst_info").addEventListener('click', addCartHandler);

    fetchManager({
        url: "/api/carts",
        method: 'GET',
        headers: {'content-type': 'application/json'},
        callback: getCart,
    });
}

function addCartHandler(e) {
    e.preventDefault();

    if (e.target.classList.contains('prd_thumb_btn')) {
        const productName = e.target.dataset.name;
        const count = 1;
        fetchManager({
                url: "/api/carts",
                method: 'POST',
                headers: {'content-type': 'application/json'},
                body: JSON.stringify({productName, count}),
                callback: updateCart,
            });
    }
}

function updateCart(data) {
    $("#cart_display_exist").style="display:block";
    $("#basket-counter").innerText = data.numItem;
}

function $(selector) {
    return document.querySelector(selector);
}

function $_value(selector) {
    return $(selector).value;
}

function fetchManager({url, method, body, headers, callback}) {
    fetch(url, {method, body, headers, credentials: "same-origin"})
        .then((response) => {
            if (response.ok) {
                response.json().then((data) => callback(data));
            }
    });
}
