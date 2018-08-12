import {CartTooltip} from "/js/cart.js";
class Product {
    constructor(productId, productPrice) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.$amountInput = $('input[name=amount]');

    }
    //todo 이게 맞는 방법인지.. 모르곘다?
    initCart(cartTooltip){
        this.cartTooltip = cartTooltip;
    }
    registerEvent() {
        $('#amount_manager').addEventListener('click', (event) => {
            const elem = event.target;

            if (elem.tagName === 'A') {
                event.preventDefault();
                const nextAmount = this.getNextAmount(elem, Number(this.$amountInput.value));
                this.updateAmount.call(this.$amountInput, nextAmount);
                this.updatePrice(this.productPrice, nextAmount);
            }
        });
        $('#addToCartBtn').addEventListener('click', (event) => {
            this.ajaxSetAmount(this.productId, Number(this.$amountInput.value)).then((data) => this.renderAfterAddCart(data));
        });
    }

    getNextAmount(elem, originalAmt) {
        if (elem.classList.contains('down'))
            return originalAmt - 1;
        return originalAmt + 1;
    }

    ajaxSetAmount(productId, amount) {
        return fetchJsonPost({url: '/api/cart/' + productId, body: {count: amount, productId: productId}});
    }

    updateAmount(amount) {
        if (amount == 0) return;
        this.value = amount;
    }

    updatePrice(price, amount) {
        $('#detail_total_price').innerText = formatMoney(price * amount);
    }

    renderAfterAddCart({data = {cartProductCnt: 0}}) {
        this.cartTooltip.renderCartTooltip(data);
        this.cartTooltip.showToaster();
    }


}

export default Product;