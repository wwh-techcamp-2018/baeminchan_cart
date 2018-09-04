document.addEventListener('DOMContentLoaded', () => {
    new Cart();
});

class Cart {
    constructor() {
        this.cart = $('#cart');
        this.productTable = $('.tb_order_style.cart');
        this.salePrice = $('#salePrice');
        this.shippingFee = $('#shippingFee');
        this.totalPrice = $('#totalPrice');

        this.registerEvent();
    }

    registerEvent() {
        this.updateProductInCart();
    }

    updateProductInCart() {
        this.cart.addEventListener('click', (e) => { this.clickEventHandler(e) });
    }

    clickEventHandler({target}) {
        if (target.classList.contains('quantity_change_confirm')) {
            const productId = this.getProductId(target);
            this.updateProductNum(productId, target);
            return;
        }

        if (target.classList.contains('up')) {
            const buyCnt = this.buyCntBox(target);
            buyCnt.value = Number(buyCnt.value) + 1;
            return;
        }

        if (target.classList.contains('down')) {
            const buyCnt = this.buyCntBox(target);
            if (buyCnt.value <= 1) {
                popUpErrorMessage("수량이 1보다 작을 순 없습니다.");
                return;
            }
            buyCnt.value = Number(buyCnt.value) - 1;
            return;
        }

        return;
    }

    getProductId(target) {
        return target.closest('.cart_prd_list').getAttribute('data-id');
    }

    updateProductNum(productId, target) {
        const productNum = Number(this.buyCntBox(target).value);
        if (productNum <= 0) {
            return;
        }

        this.requestToUpdateProduct(productId, productNum, target)
            .then(({data}) => {
                this.changeProductData(data, target)
                return this.requestTotalPrice();
            }).then(({data}) => {
                this.changeTotalPrice(data);
            }).catch(({errors}) => {
                // Todo: Errors
            });
    }

    requestToUpdateProduct(productId, productNum, target) {
        return fetchManager({
            url:'/api/cart/product/' + productId + "?num=" + productNum,
            method: "POST",
        });
    }

    requestTotalPrice() {
        return fetchManager({
            url:'/api/cart/price',
            method: "GET",
        });
    }

    changeProductData(data, target) {
        const targetTd = target.closest('td').parentNode;
        targetTd.querySelector('.productPrice').innerHTML = data.price;
        targetTd.querySelector('.sumSalePrice').innerHTML = data.totalPrice;
    }

    buyCntBox(target) {
        return target.closest('td').querySelector('.buy_cnt');
    }

    changeTotalPrice(data) {
        this.salePrice.innerText = data.totalProductPrice;
        this.shippingFee.innerText = data.deliveryCharge;
        this.totalPrice.innerText = data.totalPrice;
    }
}
