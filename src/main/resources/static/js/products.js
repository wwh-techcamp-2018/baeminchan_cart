
function $(selector) {
    return document.querySelector(selector);
}

function addEvents() {
    $("#products").addEventListener("click", updownEventListener);
}

function updownEventListener(event) {
//console.log(event.target);
const target = event.target;
    if(target.classList.contains("up")) {
       event.preventDefault();
       target.parentNode.previousElementSibling.children[0].value++;
    }

    if(target.classList.contains("down")) {
       event.preventDefault();
       if(target.parentNode.previousElementSibling.children[0].value == 1) {
           return;
       }
       target.parentNode.previousElementSibling.children[0].value--;
    }
    if(target.classList.contains("cart")) {
        fetchManager({
                url: '/api/carts',
                method: 'POST',
                headers: { 'content-type': 'application/json'},
                body: JSON.stringify({
                      "productId" : target.getAttribute("data-id"),
                      "quantity" : target.previousElementSibling.children[0].children[0].value,
                              }),
                callback: onSuccessCart,
                errCallback: cartError
            })
    }
}

function onSuccessCart(response) {
    response.json().then((response) => {
        console.log(response);
        updateCartForm(response.quantity);
        $("#basket-toaster").style="display:";
        $(".prd_name").innerText = response.productName;
        setTimeout(() => {
            $("#basket-toaster").style="display:none";
        }, 1500)

    })
}

function updateCartForm(quantity) {
    setCartCountOn();
    $("#basket-counter").innerText = quantity;
}

function setCartCountOn() {
    $("#cart_display_none").style="display:none";
    $("#cart_display_exist").style="display:";
}

function cartError() {
    console.log("Failure!");
}

function initEvents() {
    fetchManager({
        url: '/api/categories',
        method: 'GET',
        headers: { 'content-type': 'application/json'},
        callback: onSuccess,
        errCallback: alertError
    })

    $("#category-type").addEventListener("change", (e) => {
        if(e.target.value == 1) {
            $("#parent-select").classList.remove('invisible');
            return;
        }
        $("#parent-select").classList.add('invisible');
    })
    $("#category-table").addEventListener("click", (e) => {
        deleteCategory(e.target.value);
    })
}

function onSuccessCartRender(response) {
    response.json().then((response) => {
        if(response != 0) {
            updateCartForm(response);
        }
    })
}

function initRendering() {
    fetchManager({
                    url: '/api/carts',
                    method: 'GET',
                    headers: { 'content-type': 'application/json'},
                    callback: onSuccessCartRender,
                    errCallback: cartError
                })
}

function fetchManager({ url, method, body, headers, callback, errCallback }) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
           if(response.status == 200 || response.status == 201 || response.status == 302) {
                callback(response);
                return false;
           } else {
                response.json().then(res => {
                    errCallback(res);
                })
           }
    })
}

document.addEventListener("DOMContentLoaded", () => {
    addEvents();
    initRendering();
})
