function renderCartBoxArea(result) {
    if(parseInt($('#basket-counter').textContent) === 0) {
        $("#cart_display_none").setAttribute("style", 'display:none')
        $("#cart_display_exist").setAttribute("style", 'display:')
        $('#basket-counter').innerText = parseInt($('#basket-counter').textContent,10) + result.count;
        return false;
    }
    $('#basket-counter').innerText = parseInt($('#basket-counter').textContent,10) + result.count;
}

function getCartProductObject(evt) {
    evt.preventDefault();
    if(evt.target.isEqualNode($("#products").querySelector("button"))
        ||  evt.target.isEqualNode($("#products").querySelector("button span"))) {
        let requestCartProductDTO = {"productId" : evt.target.closest("dd").querySelector("#productId").getAttribute("value"), "count" : evt.target.closest("dd").querySelector(".buy_cnt").getAttribute("value")};

        fetchManager({
            url: '/cart',
            method: 'POST',
            headers: { 'content-type': 'application/json'},
            body: JSON.stringify(requestCartProductDTO),
            callback: renderCartBoxArea
        });
    }
}

document.addEventListener("DOMContentLoaded", function() {
    $("#products").addEventListener("click", getCartProductObject);
});

import {$, fetchManager} from "/js/utils.js";
