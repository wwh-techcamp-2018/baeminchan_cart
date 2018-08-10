document.addEventListener("DOMContentLoaded", function(evt) {


    fetchCartDTO("/cartInfo");

    setCartPageListeners();
});

function setCartPageListeners() {
    $(".tb_order_style.cart").querySelector("tbody").addEventListener("click", function(evt) {
        const tableItem = evt.target.closest(".cart_prd_list");
        if(evt.target.classList.contains("quantity_change_confirm")) {

            fetchCartDTO(getSetProductsUrl(getTableItemId.call(tableItem), getTableItemCount.call(tableItem)));
        } else if(evt.target.classList.contains("delete_cart_item")) {
            fetchCartDTO(getSetProductsUrl(getTableItemId.call(tableItem), "0"));
        }
    });
}

function getSetProductsUrl(id, count) {
    return "/setProducts/" + id + "/" + count;
}

function fetchCartDTO(getUrl) {
    getManager({
        url: getUrl,
        method: "GET",
        headers: {"Content-type": "application/json"},
        callback : displayProductsDTO
    });
}

function getTableItemId() {
    return this.getAttribute("data-hash").trim();
}

function getTableItemCount() {
    return this.querySelector("input.buy_cnt").value;
}

function displayProductsDTO(result) {
    const tableItemTemplate = Handlebars.templates["precompile/cart_item_template"];
    $(".tb_order_style.cart").querySelector("tbody").innerHTML = "";
    let addText = "";
    for(const productDTO of result.productDTOList) {
        addText += tableItemTemplate(productDTO);
    }
    $(".tb_order_style.cart").querySelector("tbody").innerHTML = addText;

    handleDeliveryFees(parseInt(result.displayPrice));

}

function handleDeliveryFees(price) {
    // price where discount is not included
    $(".purchase_price .price #salePrice").innerHTML = price;
    if(price > 40000 || price == 0) {
        $(".purchase_price .total .number").innerHTML = price;

        $(".purchase_price .price #shippingFee").innerHTML = "0";
    } else {
        $(".purchase_price .price #salePrice").innerHTML = price;
        $(".purchase_price .total .number").innerHTML = price + 2500;
    }
}