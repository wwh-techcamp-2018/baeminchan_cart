class CartTooltip{
    //todo cart 지우기?
    constructor(cart){
        this.cart = cart;
    }

    renderCartTooltip({cartProductCnt}){
        $('#basket-counter').innerText = cartProductCnt;

        if (cartProductCnt === 0 && $('.put_in_basket > .full')) {
            const elem = $('.put_in_basket > .full')
            removeClass('full', elem);
            addClass('empty', elem);
            removeClass('hidden', $('#cart_display_none'));
            addClass('hidden', $('#cart_display_exist'));
        } else if ($('.put_in_basket > .empty')) {
            const elem = $('.put_in_basket > .empty');
            removeClass('empty', elem);
            addClass('full', elem);
            addClass('hidden', $('#cart_display_none'));
            removeClass('hidden', $('#cart_display_exist'));
        }
    }
}
class Cart{

}
export {CartTooltip, Cart as default};