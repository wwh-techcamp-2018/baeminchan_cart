const clickUp = (event) => {
    const infoBox = event.target.closest('.prds_lst_info');
    const price = findProductPrice(event.target);
    const quantity = changeQuantity(infoBox.querySelector('.buy_cnt'), 1);
    changePrice(infoBox.querySelector('.selling-price'), price, quantity);

};

const clickDown = (event) => {
    const infoBox = event.target.closest('.prds_lst_info');
    const price = findProductPrice(event.target);
    const quantity = changeQuantity(infoBox.querySelector('.buy_cnt'), -1);
    changePrice(infoBox.querySelector('.selling-price'), price, quantity);

};

const clickAddCart = async (event) => {
    const infoBox = event.target.closest('.prds_lst_info');
    const cartBody = getCartInfo(infoBox);
    const num = await fetchAsync({
        url:  '/api/cart',
        method: 'POST',
        headers: { 'content-type': 'application/json'},
        body : JSON.stringify(cartBody),
    });

    $('#basket-counter').innerText = num;
    $('#cart_display_none').style.display = 'none';
    $('#cart_display_exist').style.display = 'block';
    setviewToast();
};

const setviewToast = () => {
    $('#basket-toaster').classList.toggle('on', true);
    setTimeout(()=> {
        $('#basket-toaster').classList.toggle('on', false);
    }, 3000);
}

const getCartInfo = (target) => {
    const quantity = target.querySelector('.buy_cnt').value;
    const productId = urlToId(target.querySelector('.prd_tlt').firstElementChild.pathname);
    const saleRate = Number(target.previousElementSibling.querySelector('.num').innerText);
    return {
        id : productId,
        quantity : quantity,
        saleRate : saleRate,
    }
}


const findProductPrice = (target) => {
    const infoBox = target.closest('.prds_lst_info');
    return Number(infoBox.querySelector('.btn.cart').attributes['data-price'].value);
}

const changePrice = (target, price, quantity) => {
    target.innerHTML = numberToLocaleString(price * quantity) + '원';
}

const changeQuantity = (target, number) => {
    const currentQuantity = Number(target.value);
    if((currentQuantity + number) < 1) return currentQuantity;
    return target.value = currentQuantity + number;
}

const clickEventController = (event) => {
    console.log(event.target);
    const targetClass = event.target.classList;
    if(targetClass.contains('up')){
        clickUp(event);
    } else if(targetClass.contains('down')){
        clickDown(event);
    } else if(targetClass.contains('btn' && 'cart')) {
        clickAddCart(event);
    }
};



document.addEventListener('DOMContentLoaded', () => {
    registClickEvent($('#products'), clickEventController);
}, false);