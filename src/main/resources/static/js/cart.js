class CartManager {
    constructor() {
        this.registerEvents();
    }

    registerEvents() {
        $('#products').addEventListener('click', ({target}) => {
            if (target.classList.contains('cart')) {
                this.addCartRequest(this.makeCartItem(target));
                return;
            }
        });
    }

    makeCartItem(target) {
        const container = target.closest('li');
        return {
            productId: container.getAttribute('data-id'),
            count: container.querySelector('.buy_cnt').value
        };
    }

    addCartRequest(cartItem) {
        fetchManager({
            url: '/carts',
            method: 'POST',
            body: JSON.stringify(cartItem),
            headers: {"content-type": "application/json"},
            callback: this.renderCartAddResult
        });
    }

    renderCartAddResult(response) {
        console.log(response);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    new CartManager();
});