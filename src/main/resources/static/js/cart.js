let cartProductCount = {};
let TotalProduct = 0;

class Cart {
    constructor() {
        this.initEvent();
    }

    initEvent() {
        const addButton = $("#products")
        addButton.addEventListener("click", this.productsListener.bind(this));
    }

    productsListener(evt) {
        if(evt.target.classList.contains('cart') || evt.target.parentElement.classList.contains('cart')) {
            this.fetchCart(evt.target.closest('li'));
        }
    }

    fetchCart(target) {
        const productId = target.getAttribute('data-id');
        this.countTotalProduct(productId);
        const postObject = {
            "productId" : productId,
            "count" : target.querySelector(".buy_cnt").value
        };
        fetchManager({
            url: "/api/carts",
            method: "POST",
            headers: {"content-type": "application/json"},
            body: JSON.stringify(postObject),
            callback: this.renderCart
        });

    }

    renderCart(result) {
        const full = $(".sticky_box .full");
        if(full === null) {
            const cart_area = $(".sticky_box .empty");
            cart_area.classList.remove('empty');
            cart_area.classList.add('full');
            $("#cart_display_none").style.display = 'none';
            $("#cart_display_exist").style.display = '';
        }
        $("#basket-counter").innerText = TotalProduct;
    }

    countTotalProduct(productId) {
        console.log(cartProductCount[productId]);
        if(!cartProductCount[productId]) {
            cartProductCount[productId] = productId;
            TotalProduct += 1;
        }
    }
}

document.addEventListener("DOMContentLoaded", () => {
    new Cart();
});
