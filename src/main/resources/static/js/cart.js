class CartTooltip{
    //todo cart 지우기?
    constructor(cart){
        this.cart = cart;
    }

    renderCartTooltip({cartProductCnt}){
        $('#basket-counter').innerText = cartProductCnt;

        if (cartProductCnt === 0) {
            this.renderFullToEmptyCart($('.put_in_basket > .full'));
            return;
        }
        this.renderEmptyToFullCart($('.put_in_basket > .empty'));



    }
    renderFullToEmptyCart(elem){
        if(!elem) return;
        removeClass('full', elem);
        addClass('empty', elem);
        removeClass('hidden', $('#cart_display_none'));
        addClass('hidden', $('#cart_display_exist'));
    }
    renderEmptyToFullCart(elem){
        if(!elem) return;
        removeClass('empty', elem);
        addClass('full', elem);
        addClass('hidden', $('#cart_display_none'));
        removeClass('hidden', $('#cart_display_exist'));
    }

    showToaster(){
        addClass('visible', $('#basket-toaster'));
        setTimeout( ()=> removeClass('visible', $('#basket-toaster')), 1000);
    }
}
class Cart{

}
export {CartTooltip, Cart as default};