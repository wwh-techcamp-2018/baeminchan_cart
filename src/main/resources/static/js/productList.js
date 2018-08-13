import {CartTooltip} from '/js/cart.js';

class ProductList {

    initCartTooltip(cart) {
        this.cartTooltip = new CartTooltip();
        this.cartTooltip.renderCartTooltip(cart);
    }

    registerEvent() {
        $('#products').addEventListener('click', (event) => {
            const elem = event.target;
            if (elem.closest('.prd_to_basket') && (elem.tagName == 'A' || elem.tagName == 'BUTTON')) {
                this.callbackClick(elem);
            }
        });
    }

    callbackClick(elem) {
        event.preventDefault();
        this.$inputBuyCnt = $('.buy_cnt', elem.closest('.prd_to_basket'));
        //<a>
        if (elem.tagName == 'A') {
            this.clickAmtLink(elem, this.$inputBuyCnt);
            return;
        }
        //<button>
        this.clickAddToCartBtn();
    }

    clickAmtLink(elem) {
        this.cartTooltip.updateAmount.call(this.$inputBuyCnt, this.getNextAmount(elem, Number(this.$inputBuyCnt.value)),);
    }

    clickAddToCartBtn() {
        this.cartTooltip.ajaxSetAmount(this.$inputBuyCnt.getAttribute('data-no'), Number(this.$inputBuyCnt.value))
            .then((data) => {
                this.cartTooltip.setToasterHtml(this.$inputBuyCnt.getAttribute('data-name'));
                this.cartTooltip.renderAfterAddCart(data);
                this.cartTooltip.updateAmount.call(this.$inputBuyCnt, this.$inputBuyCnt.getAttribute('data-min'));
            });
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


}

export default ProductList;