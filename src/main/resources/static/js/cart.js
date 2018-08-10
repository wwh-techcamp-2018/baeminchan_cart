document.addEventListener("DOMContentLoaded", () => {
    new ShoppingCart();
})

class ShoppingCart {
    constructor() {
        this.countUpBtns = $_all('.prds_lst_info .up');
        this.countDownBtns = $_all('.prds_lst_info .down');
        this.addToCartBtns = $_all('.prds_lst_info button');
        this.noItemAddedLabel = $('.top_box_number > span');
        this.totalItemKindLabel = $('#basket-counter');
        this.registerEvents();
        this.initializeCart();
    }

    initializeCart() {
        fetchManager({
            url: '/api/cart/size',
            method: 'GET',
            onSuccess: ({json}) => {
                if(json.totalKind >= 1) {
                    this.fetchCurrentItemCountLabel(json.totalKind);
                }
            }
        })
    }

    fetchCurrentItemCountLabel(totalKind) {
        this.noItemAddedLabel.style.display = 'none';
        $('#cart_display_exist').style.display = 'block';
        this.totalItemKindLabel.innerText = totalKind;
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
                    this.fetchCurrentItemCountLabel(json.totalKind);
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