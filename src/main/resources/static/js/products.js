document.addEventListener("DOMContentLoaded", function(evt) {
    $("#products").addEventListener("click", productsClickHandler)
    init();
});

function init() {
    fetchManager({
        url: "/api/carts/init",
        method: "GET",
        headers: {"content-type": "application/json"},
        callback: setCartInfo
    });
}

function productsClickHandler(evt) {
    let target = evt.target;
    const cartBtns = [...$_all(".btn.cart")];
    const upDownBtns = [...$_all(".prd_account .up"), ...$_all(".prd_account .down")];

    if(target.textContent === "담기")
        target = target.parentElement;

    if(!cartBtns.includes(target) && !upDownBtns.includes(target)){
        return;
    }

    if(cartBtns.includes(target)){
        cartBtnHadler(target);
        return;
    }

    if(upDownBtns.includes(target)){
        evt.preventDefault();
        upDownBtnHandler(target);
        return;
    }
}

function upDownBtnHandler(target) {
    const input = $("#buy_cnt_"+target.closest("li").dataset.id);
    if(target.classList.contains("up")){
        countUp(input);
        return;
    }
    countDown(input);
}

function countUp(input) {
    input.value++;
}

function countDown(input) {
    if(input.value > 1)
        input.value--;
}

function cartBtnHadler(target) {
    const pid = target.closest("li").dataset.id;
    const quantity = $_value("#buy_cnt_" + pid);

    // TODO: path에 있는 정보 바디로 보내기
    fetchManager({
        url: "/api/carts/" + pid + "/" + quantity,
        method: "POST",
        headers: {"content-type": "application/json"},
        callback: setCartInfo
    });
}

function setCartInfo(result) {
    const cartDisplayNone = $("#cart_display_none");
    const cartDesplayExist = $("#cart_display_exist");
    if(result.itemNumber < 1){
        cartDisplayNone.style.display = "";
        cartDesplayExist.style.display = "none";
        return;
    }
    cartDisplayNone.style.display = "none";
    cartDesplayExist.style.display = "";

    $("#cart_counter").textContent = result.itemNumber;

    if(!result.title)
        return;

    toast(result.title);
}

function toast(title) {
    const cartToaster = $("#cart_toaster");
    cartToaster.style.opacity = "1";
    cartToaster.querySelector(".prd_name").textContent = title;
    setTimeout(()=>{
        cartToaster.style.opacity ="0";
    }, 2000)
}