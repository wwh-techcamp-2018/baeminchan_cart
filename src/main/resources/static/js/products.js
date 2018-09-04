document.addEventListener('DOMContentLoaded', () => {
    new Products();
});

class Products {
    constructor() {
        this.unit = ' 개';
        this.numberBox = $('p.top_box_number');
        this.basketToaster = $('#basket-toaster');
        this.products = $('#products');

        this.registerEvent();
    }

    registerEvent() {
        this.addProductInCart();
    }

    addProductInCart() {
        this.products.addEventListener('click', (e) => { this.clickEventHandler(e) });
    }

    clickEventHandler({target}) {
        if (target.classList.contains('prd_thumb_btn')) {
            const productId = target.getAttribute('data-id');
            this.requestToAddProduct(target, productId)
                .then(({data}) => {
                    this.changeCartNumber(data);
                    this.showSelectedProduct(target);
                }).catch(({errors}) => {
                    // Todo: Errors
                });
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
                alert("수량이 1보다 작을 순 없습니다.");
                return;
            }
            buyCnt.value = Number(buyCnt.value) - 1;
            return;
        }

        return;
    }

    changeCartNumber(cartNum) {
        if (cartNum !== null) {
            this.numberBox.textContent = cartNum + this.unit;
        }
    }

    showSelectedProduct(target) {
        const prdDescription = target.closest('dl').querySelector('.prd_tlt > a > span').textContent;
        this.basketToaster.querySelector('.prd_name').innerHTML = prdDescription;
        this.basketToaster.style.display = 'block';
        setTimeout(() => {
            this.basketToaster.style.display = 'none';
        }, 2000);
    }

    requestToAddProduct(target, productId) {
        return fetchManager({
            url:'/api/cart/products/' + productId + "?num=" + this.buyCntBox(target).value,
            method: "POST",
        });
    }

    buyCntBox(target) {
        return target.closest('.prd_to_basket').querySelector('.buy_cnt');
    }
}
