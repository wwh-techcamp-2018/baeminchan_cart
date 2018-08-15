document.addEventListener("DOMContentLoaded", () => {
    $("#products").addEventListener("click", handleAllEvent);
});

function putMerchandise(element){

    const pId = element.firstElementChild.value;
    const amount =  element.closest('.prd_to_basket').querySelector('.buy_cnt').value;

    // 숫자 1로 리셋
    element.closest('.prd_to_basket').querySelector('.buy_cnt').value = 1;

    fetchManager({
        url: "/api/merchandise",
        method: "POST",
        headers: { 'content-type': 'application/json; charset=utf-8' },
        body: JSON.stringify({
            'id' : pId,
            'amount' : amount
        }),
        callback : putMerchandiseSuccess
    });
}

function putMerchandiseSuccess(response) {
    const data = response.data;
    data ? displayCount(data) :  displayNotCount();
}

function displayCount(count) {
    $("#cart_display_none").style.display = "none";
    $("#cart_display_exist").style.display = "block";
    $("#basket-counter").innerText = count;
}

function displayNotCount() {
    $("#cart_display_none").style.display = "block";
    $("#cart_display_exist").style.display = "none";
}

function handleAllEvent(element) {
    if(element.target.classList.contains('prd_thumb_btn')) {
        element.preventDefault();
        putMerchandise(element.target);
        return;
    }
    if (element.target.classList.contains('up')) {
        element.preventDefault();
        plusAmount(element.target);
    }
    if (element.target.classList.contains('down')) {
        element.preventDefault();
        minusAmount(element.target);
    }
}

function plusAmount(target) {
    const inputTarget = target.closest('.prd_account').querySelector('.buy_cnt');
    inputTarget.value = parseInt(inputTarget.value) + 1;
}

function minusAmount(target) {
    const inputTarget = target.closest('.prd_account').querySelector('.buy_cnt');
    inputTarget.value = parseInt(inputTarget.value) - 1;
}

