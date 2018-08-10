function $(selector) {
    return document.querySelector(selector);
}

function $_value(selector) {
    return $(selector).value;
}

function $_all(selector){
    return document.querySelectorAll(selector);
}

function fetchManager({url, method, body, headers}) {
    fetch(url, {method, body, headers, credentials: "same-origin"})
        .then((response) => {
            const value = response;
            if(value.status == 200) {
                alert("장바구니에 담았습니다.")
                return;
            } 
        })
}

function addProducts(evt){
    const index = evt.target.dataset.index;
    const id = index;
    const postObject = {
        "id": index,
        "ea": Number.parseInt($('.buy_cnt_'+index).value)
        };
    fetchManager({
        url: "/api/product/basket",
        method: "PUT",
        headers: {"content-type": "application/json"},
        body: JSON.stringify(postObject)
    });
}

function upEaBasket(evt){
    const index = evt.target.dataset.index;
    const ea = Number.parseInt($('.buy_cnt_'+index).value);
    $('.buy_cnt_'+index).value = 1+ea;
}


function downEaBasket(evt){
    const index = evt.target.dataset.index;
    const ea = Number.parseInt($('.buy_cnt_'+index).value);
    $('.buy_cnt_'+index).value = ea-1;
}

function addProducts(evt){
    const index = evt.target.dataset.index;
    const postObject = {
        "id": index,
        "ea": Number.parseInt($('.buy_cnt_'+index).value)
        };
    fetchManager({
        url: "/api/product/basket",
        method: "PUT",
        headers: {"content-type": "application/json"},
        body: JSON.stringify(postObject)
    });
}

document.addEventListener("DOMContentLoaded", function(evt) {
   const basketBtn  = $_all('#products li dl dd button')
   const upEa = $_all('#products li dl dd span a.up'); 
   const downEa = $_all('#products li dl dd span a.down'); 
   
   basketBtn.forEach(btn=>{
         btn.addEventListener('click',addProducts)
   })
   upEa.forEach(btn=>{
       btn.addEventListener('click',upEaBasket)
   })
   downEa.forEach(btn=>{
       btn.addEventListener('click',downEaBasket)
   })
   
});
