document.addEventListener("DOMContentLoaded", function(evt) {
   init();
});

function init() {
    checkProducts();
    applyDiscount();

}

function checkProducts() {
    const lists= $_all("#products li");
    for (let i = 0; i <lists.length; i++) {

        lists[i].querySelector("a.up").addEventListener("click", (evt) =>{
            evt.preventDefault();
            lists[i].querySelector(".buy_cnt").value++;
        })

        lists[i].querySelector("a.down").addEventListener("click", (evt) =>{
            evt.preventDefault();
            if(lists[i].querySelector(".buy_cnt").value > 1) {
                    lists[i].querySelector(".buy_cnt").value --;
                    }
        })
        lists[i].querySelector("button").addEventListener("click", (evt)=>{
            let productCount = parseInt(lists[i].querySelector(".buy_cnt").value);
            let productTitle = lists[i].querySelector(".prd_tlt span").innerHTML;
            let productPrice = parseInt(lists[i].querySelector(".prd_thumb_price .money").innerText);
            let discount = parseInt(lists[i].querySelector(".thumb_badge span.num").innerText);
            if(productCount < 10 || discount > 20) {
                createCart(productCount, productTitle, productPrice);
            }
            else {
                productPrice = productPrice * 0.95 ;
                createCart(productCount, productTitle, productPrice);
            }
        })
    }
}

function createCart(count, productTitle, productPrice) {
    const postObject = {
        "productTitle": productTitle,
        "productPrice": productPrice,
        "count": parseInt(count)
    };

    fetchManager({
        url: "/carts",
        method: "POST",
        headers: {"content-type": "application/json"},
        body: JSON.stringify(postObject),
        callback: checkCart
    });
}

function checkCart(cart) {
    $("#cart_display_exist").style.display = "inline";
    $("#cart_display_none").style.display = "none";
    if(cart.isArray) {
        $("#cart_display_exist span").innerHTML = cart.length;
    }
    else {
        $("#cart_display_exist span").innerHTML = 1;
    }
    $("#basket-toaster").style.display = "inline";
    $("#basket-toaster .tooltip_arr").innerHTML = cart.productTitle;

}

function applyDiscount() {
    const lists= $_all("#products li");
    for(list of lists) {
        let originalPrice = parseInt(list.querySelector(".prd_thumb_price .money").innerText);
        let discount = parseInt(list.querySelector(".thumb_badge span.num").innerText);
        list.querySelector(".prd_thumb_price .money").innerText = (originalPrice * (100 - discount)/100);
    }
}