document.addEventListener("DOMContentLoaded", function(evt) {
    getCarts();

    // deleteCart();

});



function getCarts() {
    getManager({
        url: "/carts",
        method: "GET",
        headers: {"Content-type": "application/json"},
        callback : drawCarts
    });
}

function drawCarts(result) {
    $("table tbody").innerHTML = "";
    let html = "";
    var template = Handlebars.templates["precompile/cart_product_template"];
    for(cart of result) {
        html += template(cart);
    }
    $("table tbody").innerHTML = html;
    discountPrice();
}

function discountPrice() {
    let totalSalePrice = 0;
    const prices = $_all("#sumSalePrice");
    let deliveryFee = 0;
    for(price of prices) {
        totalSalePrice += parseInt(price.innerText);
    }
     $("#salePrice").innerText = totalSalePrice;
    if(totalSalePrice < 40000) {
        deliveryFee = 2500;
    }
    $("#shippingFee").innerHTML = deliveryFee;
    $("#totalPrice").innerHTML = deliveryFee + totalSalePrice;


}


// function deleteCart() {
//     const lists =  $_all("tbody tr");
//     for (let i = 0; i < lists.length; i++) {
//         lists[i].queryScelector(".delete_cart_item_td").addEventListener("click", (evt)=> {
//             console.log(lists[i]);
//             // fetchManager({url: "/carts/"+i,
//             //     method: "DELETE",
//             //     body: "",
//             //     headers: {"Content-type": "application/json"},
//             //     callback: checkDeleted
//             // })
//         })
//
//     }
//
// }

// function checkDeleted(result) {
//     if(result) console.log(result);
// }