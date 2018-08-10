document.addEventListener("DOMContentLoaded", () => {
    initCartButtonEvents();
    getCartNumber();
})

function initCartButtonEvents() {
    $All(".cart").forEach(btn => btn.addEventListener("click", cartBtnClickHandler));
}

function cartBtnClickHandler(e) {
    const productUrlTokens = e.target.closest('li').firstElementChild.querySelector("a").href.split("/");
    const productId = productUrlTokens[productUrlTokens.length-1];
    console.log(productId);
    addCart(productId);


}

function addCart(productId) {
    fetchManager({
            url: `/carts/${productId}`,
            method: 'POST',
            headers: {'content-type': 'application/json'},
            callback: addCartCallback,
    });
}

function addCartCallback(response) {
    console.log("total:", response);
    console.log("added to cart");
    alertCart(response);
}

function getCartNumber() {
    fetchManager({
                url: "/carts/size",
                method: 'GET',
                headers: {'content-type': 'application/json'},
                callback: alertCart,
        });
}

function alertCart(cartNumber) {
    if (cartNumber == 0)
        return;
    $("#cart_display_none").style.display="none";
    $("#cart_display_exist").style.display="block";
    $("#cart_display_exist").firstElementChild.innerText = cartNumber;

}

