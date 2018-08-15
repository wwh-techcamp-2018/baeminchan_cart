class Cart {
    constructor() {
        this.productNum = 1;
        this.products = {};
        this.registerEvent();
        this.unit = ' 개';
    }

    changeCartNumber(cartNum) {
        if (cartNum !== null) {
            $('p.top_box_number').textContent = cartNum + this.unit;
        }
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
            let productId = target.getAttribute('data-id');
            if (target.tagName === 'BUTTON') {
                this.addProductNumber(productId);
                this.changeCartNumber();
                this.showSelectedProduct(target);
                this.cartFetchManager({
                                    url :'/api/cart',
                                    method: 'POST',
                                    body: JSON.stringify({
                                        'productId' : productId,
                                        'productNum' : this.productNum
                                    }),
                                    headers: {'content-type': 'application/json'},
                                    callback: ({json}) => {
                                        this.changeCartNumber(json.data);
                                    }
                                })
            }
        })
    }

    cartFetchManager({url, method, body, headers, callback}) {
        fetch(url, {method, body, headers, credentials: "same-origin"})
            .then((response) => {
                response.json().then(json => callback({json}))
            });
    }

    registerEvent() {
        document.addEventListener('DOMContentLoaded', () => {
            this.addProductInCart();
        });
    }
}

new Cart();



