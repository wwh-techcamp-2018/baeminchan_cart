document.addEventListener("DOMContentLoaded", function (evt) {
    const buyersGuide = new BuyersGuide($("#cart"));
    for(item of $_all(".cart_prd_list")) {
        new SelectedItem(item,buyersGuide);
    }
});


class SelectedItem {

    constructor(selectedItem, buyersGuide) {
        this.node = selectedItem;
        this.buyersGuide = buyersGuide;
        this.init();
    }


    init() {
        this.addRemoveClickEvent();
        this.addUpBtnClickEvent();
        this.addDownBtnClickEvent();
    }

    addRemoveClickEvent() {
        this.node.querySelector(".ico-delete.delete_cart_item").addEventListener("click", (evt) => {
            myFetchManager({
                url : `/carts/${this.getSelectedItemId()}`,
                method : "DELETE",
                body : {},
                onSuccess : this.responseRemoveSucces.bind(this),
                onFailure : this.responseRemoveFailure.bind(this)
            })
        });
    }

    addUpBtnClickEvent() {
        this.node.querySelector(".up.quantity_change").addEventListener("click", (evt) => {
            const buyCount = this.node.querySelector(".buy_cnt");
            const count = parseInt(buyCount.value);
            buyCount.value = count + 1;
            this.requestBuyCountChange(buyCount.value);
        });
    }

    addDownBtnClickEvent() {
        this.node.querySelector(".down.quantity_change").addEventListener("click", (evt) => {
            const buyCount = this.node.querySelector(".buy_cnt");
            const count = parseInt(buyCount.value);
            if(count > 1)
                buyCount.value = count - 1;
            this.requestBuyCountChange(buyCount.value);
        });
    }

    getSelectedItemId() {
        return this.node.getAttribute("id").split("-")[1];
    }
    responseRemoveSucces(response) {
        response.json().then(this.handleRemoveSelectedItem.bind(this));
    }
    responseRemoveFailure(response) {

    }

    handleRemoveSelectedItem(cart) {
        this.node.remove();
        this.buyersGuide.updateChangeInfo(cart);
    }


    requestBuyCountChange(buyCount) {
        myFetchManager({
            url : `/carts/${this.getSelectedItemId()}/${buyCount}`,
            method : "PUT",
            body : JSON.stringify({"buyCount" : buyCount}),
            onSuccess : this.responseBuyCountChange.bind(this),
            onFailure : this.responseRemoveFailure.bind(this)
        })
    }

    responseBuyCountChange(response) {
        response.json().then(this.handleBuyCountChange.bind(this));
    }

    handleBuyCountChange(cart) {
        const sumSalePrice = this.node.querySelector("#sumSalePrice");
        const selectedItem = cart.selectedItems.filter(item => item.id == this.getSelectedItemId())[0];
        sumSalePrice.innerHTML = selectedItem.totalPrice;
        this.buyersGuide.updateChangeInfo(cart);
    }





}