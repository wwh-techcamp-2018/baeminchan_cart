function $(selector) {
    return document.querySelector(selector);
}

function $All(query) {
    return document.querySelectorAll(query);
}

function $_value(selector) {
    return $(selector).value;
}

function fetchManager({url, method, body, headers, callback}) {
    fetch(url, {method, body, headers, credentials: "same-origin"})
        .then((response) => {
            const value = response;
            if(value.status == 200) {
                return;
            } else {
                return value.json();
            }

        }).then((result) => {
            callback(result);
    });
}

function initProductAdd() {
    $('#products').addEventListener('click', (e) => {
        if (e.target.tagName === 'BUTTON') {
            let productId = e.target.getAttribute('data-id');
            fetchManager({
                url :"/api/cart/1",
                method: 'POST',
                headers: {'content-type': 'application/json'},
                body: JSON.stringify({
                    "productId" : productId,
                }),
                callback: ({json}) => {
                    console.log(json);
                }
            })

        }
    })


}

document.addEventListener('DOMContentLoaded', () => {
    initProductAdd();
});
