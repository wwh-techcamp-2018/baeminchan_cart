function openLayerPopup(result) {
    $("#basket-toaster").style = 'display:'
    $("#basket-toaster .prd_name").textContent = result.count + "개";
    setTimeout(function () {
        $("#basket-toaster").style = 'display:none';
    }, 1500);
}

function renderCartBoxArea(result) {
    openLayerPopup(result);
    if(parseInt($('#basket-counter').textContent) === 0) {
        $("#cart_display_none").style = 'display:none';
        $("#cart_display_exist").style = 'display:';
        $('#basket-counter').innerText = parseInt($('#basket-counter').textContent,10) + result.count;
        return false;
    }
    $('#basket-counter').innerText = parseInt($('#basket-counter').textContent,10) + result.count;

}

function clickEventHandler(evt) {
    evt.preventDefault();
    // 담기 버튼
    if(evt.target.isEqualNode($("#products").querySelector("button"))
        ||  evt.target.isEqualNode($("#products").querySelector("button span"))) {
        let requestCartProductDTO = {
            "productId" : evt.target.closest("dd").querySelector("#productId").value,
            "count" : evt.target.closest("dd").querySelector(".buy_cnt").value
        };

        fetchManager({
            url: 'api/carts',
            method: 'POST',
            headers: { 'content-type': 'application/json'},
            body: JSON.stringify(requestCartProductDTO),
            callback: renderCartBoxArea
        });
    }

    // 수량 증가 버튼
    if(evt.target.isEqualNode($("#products").querySelector(".up"))) {
        quantityUpHandler(evt);
    }

    // 수량 감소 버튼
    if(evt.target.isEqualNode($("#products").querySelector(".down"))) {
        quantityDownHandler(evt);
    }
}

function quantityUpHandler(evt) {
    evt.target.closest("div").querySelector("input").value = parseInt(evt.target.closest("div").querySelector("input").value) + 1;
    // evt.target.closest("dl").querySelector(".selling-price").textContent =
    //     parseInt(evt.target.closest("div").querySelector("input").value)
    //     * parseInt(evt.target.closest("dl").querySelector(".selling-price").textContent);
}

function quantityDownHandler(evt) {
    if(parseInt(evt.target.closest("div").querySelector("input").value) === 1) {
        return false;
    }
    evt.target.closest("div").querySelector("input").value = parseInt(evt.target.closest("div").querySelector("input").value) - 1;
    // evt.target.closest("dl").querySelector(".selling-price").textContent =
    //     parseInt(evt.target.closest("div").querySelector("input").value)
    //     * parseInt(evt.target.closest("dl").querySelector(".selling-price").textContent);
}

document.addEventListener("DOMContentLoaded", function() {
    $("#products").addEventListener("click", clickEventHandler);
});

import {$, fetchManager} from "/js/utils.js";