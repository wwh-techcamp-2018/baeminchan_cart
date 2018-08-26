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
        this.cart.addEventListener('click', (e) => {
            const target = e.target;

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
                if (buyCnt.value <= 1){
                    alert("수량이 1보다 작을 순 없습니다.");
                    return;
                }
                buyCnt.value = Number(buyCnt.value) - 1;
                return;
            }
        });
    }

    getProductId(target) {
        return target.closest('.cart_prd_list').getAttribute('data-id');
    }

    updateProductNum(productId, target) {
        const productNum = Number(this.buyCntBox(target).value);
        if (productNum <= 0) {
            return;
        }
        this.fetchUpdateProduct(productId, productNum, target);
    }

    fetchUpdateProduct(productId, productNum, target) {
        fetchManager({
            url:'/api/cart/product/' + productId + "?num=" + productNum,
            method: "POST",
            onSuccess: ({json}) => {
                this.changeProductData(json.data, target)
                this.computeTotalPrice();
            },
            onFailed: () => {
                alert('잘못된 요청입니다.');
            },
            onError: () => {
                alert('요청중 문제가 발생하였습니다. 재접속 후 시도해주세요.');
            }
        });
    }

    computeTotalPrice() {
        fetchManager({
            url:'/api/cart/price',
            method: "GET",
            onSuccess: ({json}) => {
                this.changeTotalPrice(json.data);
            },
            onFailed: () => {
                alert('잘못된 요청입니다.');
            },
            onError: () => {
                alert('요청중 문제가 발생하였습니다. 재접속 후 시도해주세요.');
            }
        });
    }

    changeProductData(data, target) {
        const productPrice = target.closest('td').parentNode.querySelector('.productPrice');
        const sumSalePrice = target.closest('td').parentNode.querySelector('.sumSalePrice');

        productPrice.innerHTML = data.price;
        sumSalePrice.innerHTML = data.totalPrice;
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
