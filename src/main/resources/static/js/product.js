import {CartTooltip} from "/js/cart.js";
class Product {
    constructor(productId, productPrice) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.$amountInput = $('input[name=amount]');

    }
    initCartTooltip(cart) {
        this.cartTooltip = new CartTooltip();
        this.cartTooltip.renderCartTooltip(cart);
    }
    registerEvent() {
        $('#amount_manager').addEventListener('click', (event) => {
            const elem = event.target;

            if (elem.tagName === 'A') {
                event.preventDefault();
                this.clickAmtLink(elem);
               }
        });
        $('#addToCartBtn').addEventListener('click', (event) => {

        });
    }
    clickAmtLink(elem){
        const nextAmount = this.getNextAmount(elem, Number(this.$amountInput.value));
        this.cartTooltip.updateAmount.call(this.$amountInput, nextAmount);
        this.updatePrice(this.productPrice, nextAmount);

    }
    clickAddToCartBtn(){
        this.cartTooltip.ajaxSetAmount(this.productId, Number(this.$amountInput.value))
            .then((data) => this.cartTooltip.renderAfterAddCart(data));

    }

    getNextAmount(elem, originalAmt) {
        if (elem.classList.contains('down'))
            return originalAmt - 1;
        return originalAmt + 1;
    }
    updatePrice(price, amount) {
        $('#detail_total_price').innerText = formatMoney(price * amount);
    }



}

export default Product;