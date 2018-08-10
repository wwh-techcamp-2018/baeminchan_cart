function $(selector) {
    return document.querySelector(selector);
}

function $All(selector) {
    return document.querySelectorAll(selector);
}

function $_value(selector) {
    return $(selector).value;
}

const cart={};
const upNodeList = $All('.up.amount');
const downNodeList = $All('.down.amount');
const addNodeList = $All('.prd_thumb_btn.cart');
const buyNodeList = $All('.buy_cnt');

function fetchManager({url, method, body, headers, success, error}){
    fetch(url,{
        method,
        body,
        headers,
        credentials:"same-origin"
    }).then((result) =>{
        success(result)
    }).catch(e=>{
        error(e);
    })
}

document.addEventListener('DOMContentLoaded', initCart);

function initCart(){
    upNodeList.forEach(node=>{
        node.addEventListener('click',upAmount);
    })

    downNodeList.forEach(node=>{
        node.addEventListener('click',downAmount);
    })

    addNodeList.forEach(node=>{
        node.addEventListener('click',addInCart);
    })
}

function upAmount(e){
    const {target} = e;
    const amountNode = target.parentElement.previousElementSibling.firstChild;
    amountNode.value = parseInt(amountNode.value)+1;
}

function downAmount(e){
    const {target} = e;
    const amountNode = target.parentElement.previousElementSibling.firstChild;
    parseInt(amountNode.value) >1 ? amountNode.value = parseInt(amountNode.value)-1:amountNode.value =1;
}

function addInCart(e){
    const {target} = e;
    const index = [...addNodeList].indexOf(target);

    const id = target.firstElementChild.value;
    const amount = [...buyNodeList][index].value;

//    id in cart ? cart[id] += amount: cart[id] = amount;

    fetch('/carts',{
        method: 'post',
        headers: {"content-type": "application/json"},
        credentials: 'same-origin',
        body: JSON.stringify({
            "id": id,
            "amount":amount
        })
    }).then(response=>{
        if(!response.ok){
            alert("error ");
            return;
        }
        addInCartSuccess(response);
    })

}

function addInCartSuccess(response){
    console.log(response.json());
}
function error(e){
    alert(e);
}

function statusCart(){

}
