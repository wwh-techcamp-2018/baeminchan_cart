document.addEventListener("DOMContentLoaded", () => {
    new ShoppingCart(new ShoppingCartSideBar());
})

class ShoppingCart {
    constructor(shoppingCartSideBar) {
        this.shoppingCartSideBar = shoppingCartSideBar;
        this.countUpBtns = $_all('.prds_lst_info .up');
        this.countDownBtns = $_all('.prds_lst_info .down');
        this.addToCartBtns = $_all('.prds_lst_info button');
        this.registerEvents();
        this.initializeCart();
    }

    initializeCart() {
        fetchManager({
            url: '/api/cart/size',
            method: 'GET',
            onSuccess: ({json}) => {
                if(json.totalKind >= 1) {
                    this.shoppingCartSideBar.fetchCurrentItemCountLabel(json.totalKind);
                }
            }
        })
    }

    registerEvents() {
        this.registerCountUpBtnEvent();
        this.registerCountDownBtnEvent();
        this.registerAddToCartBtnClickEvent();
    }

    registerAddToCartBtnClickEvent() {
        this.addToCartBtns.forEach(e => e.addEventListener('click', (evt) => {
            console.log(evt.target.getAttribute('data-id'))
            console.log(this.getBuyCountLabel(evt.target).value)
            fetchManager({
                url: `/api/cart`,
                method: 'POST',
                headers: {
                    'content-type': 'application/json'
                },
                body: JSON.stringify({
                    productId: evt.target.getAttribute('data-id'),
                    count: this.getBuyCountLabel(evt.target).value
                }),
                onSuccess: ({json}) => {
                    console.log(json);
                    this.shoppingCartSideBar.notifyItemAdded(json.productTitle);
                    this.shoppingCartSideBar.fetchCurrentItemCountLabel(json.totalKind);
                }
            });
        }))
    }

    getBuyCountLabel(element) {
        return element.closest('dd').querySelector('input.buy_cnt');
    }

    registerCountUpBtnEvent() {
        this.countUpBtns.forEach(e => e.addEventListener('click', (evt) => {
            this.getBuyCountLabel(evt.target).value++;
        }))
    }

    registerCountDownBtnEvent() {
        this.countDownBtns.forEach(e => e.addEventListener('click', (evt) => {
            const label = this.getBuyCountLabel(evt.target);
            if (label.value == 1) return;
            evt.target.closest('.prd_account').querySelector('.buy_cnt').value--;
        }))
    }
}