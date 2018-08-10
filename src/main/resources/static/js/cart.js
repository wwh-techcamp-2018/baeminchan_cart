function displayCartNumber() {
     const cartExistTag = $("#cart_display_exist");
     if(cartExistTag.style.display === "none") {
         cartExistTag.style.display = "";
         $("#cart_display_none").style.display = "none";
     }
 }

class Cart {
    constructor() {
        this.parent = $("#products");
        this.registerEvents();
    }

    registerEvents() {
        this.parent.addEventListener("click", this.cartEventHandler.bind(this));
    }

    amountDownHandler(target) {
        const inputTag = target.parentElement.parentElement.firstElementChild.firstElementChild;
        const inputTagValue = inputTag.value;
        if(inputTagValue !== "1")
            inputTag.value = parseInt(inputTag.value, 10) - 1;
    }

    amountUpHandler(target) {
        const inputTag = target.parentElement.parentElement.firstElementChild.firstElementChild;
        const inputTagValue = inputTag.value;
        inputTag.value = parseInt(inputTag.value, 10) + 1;
    }

    getAmount(target) {
        return target.previousElementSibling.firstElementChild.firstElementChild.value;
    }

    getId(target) {
        return target.previousElementSibling.firstElementChild.firstElementChild.getAttribute("data-no");
    }

    addCartCallback(response) {
        if(response.status === 200) {
            const cartNumberInput = $(".number_txt");
            displayCartNumber();
            cartNumberInput.textContent = parseInt(cartNumberInput.textContent, 10) + 1;
        }
    }

    cartBtnHandler(target) {
        const postObject = {
                    "id": this.getId(target),
                    "amount": this.getAmount(target)
                };

        fetch("/cart", {method: "POST", body: JSON.stringify(postObject),
                headers: {"content-type": "application/json"}, credentials: "same-origin"})
                .then((response) => {
                    const value = response;
                    return response;

                }).then((result) => {
                    this.addCartCallback(result);
            });
    }

    cartEventHandler(evt) {
        const target = evt.target;
        const targetClassName = target.className;
        const CART_BTN = "btn btn_gray prd_thumb_btn cart";
        const CART_BTN_SPAN = ""
        const UP = "up";
        const DOWN = "down";

        if(!(targetClassName === CART_BTN || targetClassName === UP || targetClassName === DOWN))
            return;

        if(targetClassName === CART_BTN){
            this.cartBtnHandler(target);
            return;
        }

        if(targetClassName === UP) {
            this.amountUpHandler(target);
            return;
        }

        if(targetClassName === DOWN) {
            this.amountDownHandler(target);
            return;
        }
    }
}

document.addEventListener("DOMContentLoaded",() => {
    new Cart();
})