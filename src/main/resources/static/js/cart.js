class CartTooltip{
    constructor(){
        this.$cartDisplayNone =  $('#cart_display_none');
        this.$cartDisplayExist = $('#cart_display_exist');
        this.$basketToaster = $('#basket-toaster');
        this.$cartTooltip =  $('.put_in_basket');
    }

    renderCartTooltip( cart = { cartProductCnt : 0}){
        const cartProductCnt = cart.cartProductCnt;
        $('#basket-counter', this.$cartTooltip).innerText = cartProductCnt;

        if (cartProductCnt === 0) {
            this.renderFullToEmptyCart($('.full'), this.$cartTooltip);
            return;
        }
        this.renderEmptyToFullCart($('.empty', this.$cartTooltip));
    }

    ajaxSetAmount(productId, amount) {
        return fetchJsonPost({url: '/api/cart', body: {count: amount, productId}});
    }
    ajaxChangeAmount(cartId, amount) {
        return fetchJsonRequest({url: '/api/cart', method: 'put',body: {count: amount, cartId}});
    }
    updateAmount(amount, minAmount = 1) {
        if (amount < minAmount) return;
        this.value = amount;
    }

    renderAfterAddCart({data = {cartProductCnt: 0}}) {
        this.renderCartTooltip(data);
        this.showToaster();
    }

    renderFullToEmptyCart(elem){
        if(!elem) return;
        removeClass('full', elem);
        addClass('empty', elem);
        removeClass('hidden', this.$cartDisplayNone);
        addClass('hidden', this.$cartDisplayExist);
    }
    renderEmptyToFullCart(elem){
        if(!elem) return;
        removeClass('empty', elem);
        addClass('full', elem);
        addClass('hidden', this.$cartDisplayNone);
        removeClass('hidden', this.$cartDisplayExist);
    }
    setToasterHtml(prd_name){
        $('.prd_name', this.$basketToaster).innerHTML = prd_name;
    }
    showToaster(){
        addClass('visible',this.$basketToaster);
        setTimeout( ()=> removeClass('visible', this.$basketToaster), 1000);
    }
}
class Cart{
    initCartTooltip(cart) {
        this.cartTooltip = new CartTooltip();
        //this.cartTooltip.renderCartTooltip(cart)''
    }
    registerEvent() {
        $('table.tb_order_style').addEventListener('click', (event)=>{
            const elem = event.target;
            if ((elem.tagName == 'A' && elem.closest('.prd_account'))|| elem.tagName == 'BUTTON') {
                this.callbackClick(elem);
            }
        });
    }

    callbackClick(elem) {
        event.preventDefault();
        this.$inputBuyCnt = $('.buy_cnt', elem.closest('td'));
        const $tr = elem.closest('tr');
        this.cartNo = $tr.getAttribute('cart_no');
        this.productNo = $tr.getAttribute('product_no');
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
        this.setItemTotalPrice(elem,  Number(this.$inputBuyCnt.value));
    }
    setItemTotalPrice(elem, amount){
        const procutPrice = $('#productPrice', elem.closest('tr')).innerHTML;
        $('#sumSalePrice', elem.closest('tr')).innerHTML =  formatMoney(procutPrice * amount);
    }

    calculateTotalPrice(){

    }
    clickAddToCartBtn() {
        //todo Map-으로 바꾸고, CartProductRepository 도입? > 아래 productNo --> cartNo (CartProduct.id)
        this.cartTooltip.ajaxChangeAmount(this.cartNo, Number(this.$inputBuyCnt.value))
            .then((response) => {
                //총 액수 등 변해야함
                console.log(response);
                this.cartTooltip.updateAmount.call(this.$inputBuyCnt, this.$inputBuyCnt.getAttribute('data-min'));
                this.setCalculation(response.data.calculation)
            });
    }

    getNextAmount(elem, originalAmt) {
        if (elem.classList.contains('down'))
            return originalAmt - 1;
        return originalAmt + 1;
    }
    setCalculation({deliveryFee = 0, deliveryTotalPrice = 0, totalPrice = 0}){
       $('#salePrice').innerHTML = formatMoney(totalPrice);
       $('#shippingFee').innerHTML = formatMoney(deliveryFee);
       $('#totalPrice').innerHTML = formatMoney(deliveryTotalPrice);
    }

}
export {CartTooltip, Cart as default};