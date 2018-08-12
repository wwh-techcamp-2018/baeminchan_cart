class ProductList {

    initCart(cartTooltip) {
        this.cartTooltip = cartTooltip;
    }

    registerEvent() {
        $('#products').addEventListener('click', (event) => {
            const elem = event.target;
            if (elem.closest('.prd_to_basket')) {
                event.preventDefault();
                const $inputBuyCnt = $('.buy_cnt', elem.closest('.prd_to_basket'));
                if (elem.tagName == 'A' || elem.tagName == 'BUTTON') {
                    event.preventDefault();

                    if (elem.tagName == 'A') {
                        const nextAmount = this.getNextAmount(elem, Number($inputBuyCnt.value));
                        this.updateAmount.call($inputBuyCnt, nextAmount);
                        return;
                    }
                    this.ajaxSetAmount($inputBuyCnt.getAttribute('data-no'), Number($inputBuyCnt.value))
                        .then((data) => {
                            this.cartTooltip.setToasterHtml($inputBuyCnt.getAttribute('data-name'));
                            this.renderAfterAddCart(data)
                            this.resetToMinAmount.call($inputBuyCnt)
                        });

                }
            }
        });

    }

    updateAmount(amount) {
        if (amount < this.getAttribute('data-min')) return;
        this.value = amount;
    }

    ajaxSetAmount(productId, amount) {
        return fetchJsonPost({url: '/api/cart/' + productId, body: {count: amount, productId: productId}});
    }

    renderAfterAddCart({data = {cartProductCnt: 0}}) {
        this.cartTooltip.renderCartTooltip(data);
        this.cartTooltip.showToaster();

    }

    getNextAmount(elem, originalAmt) {
        if (elem.classList.contains('down'))
            return originalAmt - 1;
        return originalAmt + 1;
    }
    resetToMinAmount(){
        const minAmount = this.getAttribute('data-min');
        this.value = minAmount;
    }
}

export default ProductList;