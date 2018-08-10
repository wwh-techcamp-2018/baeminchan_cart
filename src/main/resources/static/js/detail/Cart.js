export class Cart {
    constructor() {
        document.addEventListener("DOMContentLoaded", this.addEventListeners.bind(this));
    }

    addEventListeners() {
        $_All(".prd-quantity-dd").forEach((element) => {element.addEventListener("click", this.onClickButton.bind(this))});
    }

    onClickButton({target}) {
        const classList = target.classList;
        if(classList.contains("quantity-up")) this.onClickUpButton(target);
        if(classList.contains("quantity-down")) this.onClickDownButton(target);
        if(classList.contains("cart-put-btn")) this.onClickCartButton(target);
    }

    onClickUpButton({"parentElement" : { previousElementSibling }}) {
        const quantityInput = previousElementSibling.querySelector("input");
        quantityInput.value = Number(quantityInput.value) + 1;
    }

    onClickDownButton({"parentElement" : { previousElementSibling }}) {
        const quantityInput = previousElementSibling.querySelector("input");
        const quantity =  Number(quantityInput.value);
        if(quantity < 0) return;
        quantityInput.value = quantity - 1;
    }

    onClickCartButton(target) {
        const quantity = target.previousElementSibling.querySelector("input").value;
        const productId = target.nextElementSibling.value;
        fetchManager({
            url: '/carts',
            method: 'POST',
            headers: { 'content-type': 'application/json'},
            body: JSON.stringify({
                "productId": productId,
                "quantity": quantity
            }),
            callback: this.onCompleteCartRequest.bind(this),
            errCallback: this.onFailureCartRequest.bind(this)
        });
    }

    onCompleteCartRequest(response) {
        this.toggleInvisibleClass();
        this.addQuantity();
        this.showToast(response.title);
    }

    toggleInvisibleClass() {
        if(!$("#cart_display_none").classList.contains("invisible"))
            $("#cart_display_none").classList.add("invisible");
        if($("#cart_display_exist").classList.contains("invisible"))
            $("#cart_display_exist").classList.remove("invisible");
    }

    addQuantity() {
        const quantitySpan = $("#cart_display_exist").querySelector("span");
        quantitySpan.innerHTML = Number(quantitySpan.innerHTML) + 1;
    }

    showToast(title) {
        $("#basket-toaster").querySelector("span").innerHTML = title;
        this.toggleOpacityOnToast();
        setTimeout(this.toggleOpacityOnToast.bind(this), 3000);
    }

    toggleOpacityOnToast() {
        $("#basket-toaster").classList.toggle("opacity-none");
    }

    onFailureCartRequest() {
        console.log('AJAX 요청 실패');
    }
}