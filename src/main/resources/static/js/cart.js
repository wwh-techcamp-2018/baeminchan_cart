
function initEvents() {
    $('#products').addEventListener('click', handleItemEvent);
    $('#basket-counter').addEventListener('click', handleGoToBasket);
}

function handleItemEvent(e) {
    e.preventDefault();

    targetList = e.target.closest('li');

    inputField = targetList.querySelector('.buy_cnt');
    upButton = targetList.querySelector('.up');
    downButton = targetList.querySelector('.down');
    updateButton = targetList.querySelector('.btn.btn_gray.prd_thumb_btn.cart');


    switch (e.target) {
        case upButton:
            increaseItemCount();
            break;
        case downButton:
            decreaseItemCount();
            break;
        case updateButton:
            updateItemToCart();
    }
}

function increaseItemCount() {
    inputField.value++;
}

function decreaseItemCount() {
    if (inputField.value <= 1) return;
    inputField.value--;
}

function updateItemToCart() {
    const id = targetList.querySelector('div > a').href.split('/').pop();
    const imgUrl = targetList.querySelector('.imgthumb img').src;
    const description = inputField.dataset.name;
    const title = targetList.querySelector('div > a').getAttribute('ga_name');
    const price = targetList.querySelector('.origin-price > del').innerText.replace('원','');
    const salesRate = targetList.querySelector('.num').innerText;
    const amount = inputField.value;

    fetch('cart', {
        method: 'post',
        headers: {'content-type': 'application/json'},
        credentials: 'same-origin',
        body: JSON.stringify({
            id,
            imgUrl,
            description,
            title,
            price,
            salesRate,
            amount
        })
    }).then(response => {
        if (!response.ok) return;

        return response.json();
    }).then(handleCartView)
    .catch(handleError);
}

function handleCartView({data}) {
    const divison = $('.put_in_basket.sticky_box').querySelector('div');
    divison.classList.remove('empty');
    divison.classList.add('full');

    divison.querySelector('.txt').style.display="none";
    divison.querySelector('.number').style.display="";
    divison.querySelector('#basket-counter').innerText = data;
}

function handleGoToBasket() {
    location.href = '/cart.html';
}

function handleError() {

}

document.addEventListener('DOMContentLoaded', initEvents);
