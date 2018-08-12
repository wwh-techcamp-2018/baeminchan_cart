class Cart {
    constructor() {
        this.cartNumber = 0;
        this.products = {};
        this.registerEvent();
    }

    changeCartNumber() {
        this.cartNumber += 1;
        $('p.top_box_number').textContent = this.cartNumber + ' 개';
    }

    addProductNumber(productId) {
        if (!(productId in this.products)) {
            this.products[productId] = 0;
        }
        this.products[productId] += 1;
    }

    showSelectedProduct(target) {
        let prdDescription = target.closest('dl').querySelector('.prd_tlt > a > span').textContent;
        let basketToaster = $('#basket-toaster');
        basketToaster.childNodes.forEach(element => {
            if (element.className === 'prd_name') {
                element.innerHTML = prdDescription;
            }
        });
        basketToaster.style.display = 'block';
        setTimeout(() => {
            basketToaster.style.display = 'none';
        }, 2000);
    }

    addProductInCart() {
        $('#products').addEventListener('click', (e) => {
            let target = e.target;
            if (target.tagName === 'BUTTON') {
                let productId = e.target.getAttribute('data-id');
                this.addProductNumber(productId);
                this.changeCartNumber();
                this.showSelectedProduct(target);
            }
        })
    }

    registerEvent() {
        document.addEventListener('DOMContentLoaded', () => {
            this.addProductInCart();
        });
    }
}

new Cart();



