class Product {
    constructor(productId, productPrice) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.$amountInput = $('input[name=amount]');
    }

    registerEvent() {
        $('#amount_manager').addEventListener('click', (event) => {
            const elem = event.target;
            if (elem.tagName === 'A'){
                event.preventDefault();
                const nextAmount = this.getNextAmount(elem, Number(this.$amountInput.value));
                if( nextAmount == 0) return;
                this.updateAmount(nextAmount);
                this.updatePrice(this.productPrice, nextAmount);
            }
        });
        $('#addToCartBtn').addEventListener('click', (event) =>{
            this.ajaxSetAmount(this.productId, Number(this.$amountInput.value));
        });

/*

 */

    }
    getNextAmount(elem, originalAmt){
        if(elem.classList.contains('down'))
            return originalAmt - 1;
        return originalAmt + 1;
    }
    ajaxSetAmount(productId, amount) {
        fetchManager({
            url: '/api/cart/1',
            method: 'post',
            body: JSON.stringify({count: amount, productId: productId}),
            headers: {'content-type': 'application/json'},
            callback: (data) => {
                console.log(data)
            }
        });
    }

    updateAmount(amount){
        this.$amountInput.value = amount;
    }

    updatePrice(price, amount){
        $('#detail_total_price').innerText = formatMoney(price * amount);
    }

    renderIfCartExists(count){
        const elem = $('.empty');
        if(elem) {
            removeClass(elem, 'empty');
            addClass(elem, 'full');
            addClass($('#cart_display_none'), 'hidden');
            removeClass($('#cart_display_exist'), 'hidden');
        }
    }
}

//export default Product;