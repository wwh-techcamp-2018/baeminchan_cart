class CartTooltip{
    constructor(cart){
        this.$cartDisplayNone =  $('#cart_display_none');
        this.$cartDisplayExist = $('#cart_display_exist');
        this.$basketToaster = $('#basket-toaster');
        this.$cartTooltip =  $('.put_in_basket');
        this.renderCartTooltip(cart);
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
        return fetchJsonPost({url: '/api/cart/' + productId, body: {count: amount, productId: productId}});
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

}
export {CartTooltip, Cart as default};