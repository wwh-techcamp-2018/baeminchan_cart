class Product {
    constructor(products,product) {
        this.products = products;
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
            const stickyBox = $(".put_in_basket.sticky_box");
            const url = stickyBox.getAttribute("id") ? `/carts/${stickyBox.getAttribute("id")}`: "/carts";
            const body = {
                "productId" : productId,
                "count" : buyCount
            }
            myFetchManager({
                url : url,
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
        stickyBox.setAttribute("id",cart.id);
        stickyBox.querySelector("#go_to_cart").setAttribute("href", `/cart/${cart.id}`);

        const cartDisplayNone = stickyBox.querySelector("#cart_display_none");
        cartDisplayNone.style.display = "none";
        const cartDisplayExist = stickyBox.querySelector("#cart_display_exist");
        cartDisplayExist.style.display = "";
        cartDisplayExist.querySelector("#basket-counter").innerHTML = cart.selectedItems.length;
    }

}