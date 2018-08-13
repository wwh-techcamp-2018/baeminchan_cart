document.addEventListener("DOMContentLoaded", () => {
    Array.from($All(".prd_thumb_btn")).forEach(child => child.addEventListener("click", putMerchandise.bind(null, child)));
});

function putMerchandise(element){

    const pId = element.firstElementChild.value;
    const amount =  element.previousElementSibling.firstElementChild.firstElementChild.value;

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
