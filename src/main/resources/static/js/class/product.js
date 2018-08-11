document.addEventListener("DOMContentLoaded", function (evt) {
    $_all("#products").forEach((v) => {
        const liNodes = v.getElementsByTagName("li");
        for (const liNode of liNodes) {
            new Product(liNode);
        }
    });
});


class Product {
    constructor(product) {
        this.node = product;
        this.init();
    }

    init() {
        this.addUpAndDownEvent();
        this.addSaveCartEvent();
    }

    addUpAndDownEvent() {
        this.node.querySelector(".up").addEventListener("click", (evt) => {
            evt.preventDefault();
            const buyCount = this.node.querySelector(".buy_cnt");
            buyCount.value = parseInt(buyCount.value) + 1;
        })

        this.node.querySelector(".down").addEventListener("click", (evt) => {
            evt.preventDefault();
            const buyCount = this.node.querySelector(".buy_cnt");
            if (parseInt(buyCount.value) > 1)
                buyCount.value = parseInt(buyCount.value) - 1;
        })
    }

    addSaveCartEvent() {
        this.node.querySelector(".btn.btn_gray.prd_thumb_btn.cart").addEventListener("click", (evt) => {
            const productId = this.getProductId();
            const buyCount = this.node.querySelector(".buy_cnt").value;
            const body = {
                "productId" : productId,
                "count" : buyCount
            }
            myFetchManager({
                url : "/carts",
                method : "POST",
                body : JSON.stringify(body),
                onSuccess : this.requestSucess.bind(this),
                onFailure : this.requestFailure.bind(this)
            })

        });

    }

    getProductId() {
        return this.node.getAttribute("id").split("-")[1];
    }

    requestSucess(response){
        response.json().then(this.handleSaveCart.bind(this));

    }
    requestFailure(response){

    }

    handleSaveCart(cart) {
        const stickyBox = $(".put_in_basket.sticky_box");
        stickyBox.querySelector("#cart_display_none").style.display = "none";
        stickyBox.querySelector("#cart_display_exist").style.display = "";
        stickyBox.querySelector("#basket-toaster").style.display = "";

        setTimeout(()=>{
            stickyBox.querySelector("#basket-toaster").style.display = "none";
        },3000);

        stickyBox.querySelector("#cart_display_exist")
            .querySelector("#basket-counter").innerHTML = cart.selectedItems.length;
    }

}
