class Cart {
    constructor() {
        this.cartItems = [];
        this.updateCartItemsInCookie();
        $('#products').addEventListener('click', this.productEventHandler.bind(this));
    }

    updateCartItemsInCookie() {
        this.cartItems = getCookie('cartList') == '' ? [] : JSON.parse(getCookie('cartList'));
        this.updateCart();
    }

    getCartItemLength() {
        return Object.keys(this.cartItems).length;
    }

    activeToCartButton() {
        $('#go_to_cart').parentElement.classList.remove('empty');
    }

    disableToCartButton() {
        $('#go_to_cart').parentElement.classList.add('empty');
    }

    updateCart() {
        const length = this.getCartItemLength();
        if (length !== 0) {
            $('#cart_display_exist').style.display = 'block';
            $('#basket-counter').innerHTML = length;
            this.activeToCartButton();
        } else {
            $('#cart_display_none').style.display = 'none';
            this.disableToCartButton();
        }

    }

    handleSetProductAmount(target) {
        const inputCount = target.parentElement.previousElementSibling.children[0];
        let val = inputCount.value;
        if (target.className === 'up') {
            inputCount.value = ++val;
        } else {
            if (val <= 1) {
                return;
            }
            inputCount.value = --val;
        }
    }

    handlerAddCart(target) {
        const productId = target.getAttribute('data-id');
        const title = $(`#product-title-${productId}`).getAttribute('ga_name');
        const thumbnail = $(`#product-${productId}`).getAttribute('src');
        let price = $(`.selling-price-${productId}`).childNodes[0].cloneNode(false).data.replace(',', '');
        const amount = $(`.buy-cnt-${productId}`).value;
        let cartList = {};
        if (getCookie('cartList')) {
            cartList = JSON.parse(getCookie('cartList'));
        }
        if (cartList[productId]) {
            let prevAmount = cartList[productId].amount * 1;
            cartList[productId].amount = prevAmount + (amount * 1);
        } else {
            cartList[productId] = {
                productId: productId,
                title: title,
                amount: amount,
                imgUrl: thumbnail,
                price: price,
            };
        }

        setCookieHour("cartList", JSON.stringify(cartList), 3);
        this.updateCartItemsInCookie();
        $('#basket-counter').innerHTML = this.getCartItemLength();
        $('#basket-toaster').style.dispaly = "block";
        fetchManager({
            url: '/users/add-cart',
            method: 'POST',
            headers: {'content-type': 'application/json'},
            body: JSON.stringify({
                productId: productId,
                amount: amount
            }),
            onSuccess: this.successCallback,
            onFailure: this.failCallback
        });
    }

    productEventHandler(evt) {
        evt.preventDefault();
        let target = evt.target;
        if (target.tagName === 'A') {
            this.handleSetProductAmount(target);
        }
        if (target.id === 'add-cart' || target.id === 'add-cart-span') {
            target = target.closest('#add-cart');
            this.handlerAddCart(target);
        }
    }

    successCallback() {
        console.log('success');
    }

    failCallback() {
        console.log('fail or user not logined');
    }
}

const cart = new Cart();