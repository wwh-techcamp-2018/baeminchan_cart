document.addEventListener('DOMContentLoaded', initEvents);

function initEvents() {
    let cartButton = $all('.btn.btn_gray.prd_thumb_btn.cart');
    Array.from(cartButton).forEach(button => {
        button.addEventListener('click', saveToCart)
    });

    fetchManager({
                url: "/products/cart/count",
                method: "get",
                headers: { 'content-type': 'application/json; charset=utf-8' },
                success: setDefaultCounter,
                error: failRequest
            })
}

function setDefaultCounter(response){
    response.json()
            .then(counter => {
            if(counter != 0){
                $("#cart_display_none").style.display = 'none';
                $("#cart_display_exist").style.display = 'block';
                $("#basket-counter").innerText = counter;
               }
            })
}

function saveToCart(e) {
    e.preventDefault();

    let target = e.target.parentElement.parentElement.parentElement; //li

    let description = target.getElementsByTagName("span").item(2).innerText;
    let title = target.getElementsByTagName("span").item(3).innerText;
    let price = target.getElementsByTagName("p").item(1).innerText;
    let itemCount = parseInt(target.getElementsByTagName("input").item(0).value);
    let imgUrl = target.getElementsByTagName("button").item(0).innerText;
    price = parseInt(price.substring(0,price.length));



    fetchManager({
        url: "/products/cart",
        method: "post",
        headers: { 'content-type': 'application/json; charset=utf-8' },
        body: JSON.stringify({
                        title,
                        description,
                        price,
                        imgUrl,
                        itemCount
                    }),
        success: saveToCartSuccess,
        error: failRequest
    })

}

function saveToCartSuccess(){
    $("#cart_display_none").style.display = 'none';
    $("#cart_display_exist").style.display = 'block';

    fetchManager({
            url: "/products/cart/count",
            method: "get",
            headers: { 'content-type': 'application/json; charset=utf-8' },
            success: countUpCart,
            error: failRequest
        })
}

function countUpCart(response){
    response.json()
        .then(counter => {
            $("#basket-counter").innerText = counter;
        })

}

function failRequest(error){
    alert(error);
}


