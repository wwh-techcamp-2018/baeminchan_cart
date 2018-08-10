class Cart {
    constructor() {
        this.addEventListeners();
        this.getCart();
    }

    addEventListeners() {
        // URI: /products
        const products = $("#products")
        if (products) {
            products.addEventListener("click", (event) => this.add(event));
        }
    }

    toCartProductDTO(productId, quantity) {
        return {"productId": productId, "quantity": quantity};
    }

    add(event) {
        if (event.target.getAttribute("data-role") !== "add-to-cart-button") {
            return;
        }
        const product = event.target.parentElement.querySelector(".buy_cnt");
        fetchManager({
            url: "api/cart",
            method: "POST",
            headers: { "content-type": "application/json" },
            body: JSON.stringify(this.toCartProductDTO(product.getAttribute("data-product-id"), product.value)),
            callback: result => this.updateCart(result)
        });
    }

    getCart() {
        fetchManager({
            url: "api/cart",
            method: "GET",
            headers: { "content-type": "application/json" },
            callback: result => this.updateCart(result)
        });
    }

    updateCart(cartProducts) {
        this.products = cartProducts;
        console.log(this.products);
        this.updateHeaderCartInfo();
        this.updateSideBarCartInfo();
    }

    updateHeaderCartInfo() {
        if (this.products.length > 0) {
            const header = $("#snb > div > ul");
            const parent = header.lastElementChild.lastElementChild;
            if (parent.children.length > 0) {
                parent.lastChild.remove();
            }
            parent.insertAdjacentHTML("beforeEnd", `<span class="cart_num">${this.products.length}</span>`);
        }
    }

    updateSideBarCartInfo() {
        if (this.products.length > 0) {
            $("#cart_display_exist").removeAttribute("style");
            $("#cart_display_none").removeAttribute("style");
            $("#cart_display_none").setAttribute("style", "display: none;");
            $("#cart_display_exist").firstElementChild.textContent = this.products.length;
            return;
        }
        $("#cart_display_none").removeAttribute("style");
        $("#cart_display_exist").removeAttribute("style");
        $("#cart_display_exist").setAttribute("style", "display: none;");
    }
}

cart = new Cart();