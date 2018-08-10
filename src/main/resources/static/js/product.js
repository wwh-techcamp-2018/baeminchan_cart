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

const clickAddCart = (event) => {
    console.log(event.target);
};


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