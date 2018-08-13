class BuyersGuide {
    constructor(buyerGuide) {
        this.node = buyerGuide;
    }

    updateChangeInfo(cart) {
        this.node.querySelector("#salePrice").innerHTML = cart.totalSalesPrice;
        this.node.querySelector("#shippingFee").innerHTML = cart.shipmentPrice;
        this.node.querySelector("#totalPrice").innerHTML = cart.totalPrice;
    }
}