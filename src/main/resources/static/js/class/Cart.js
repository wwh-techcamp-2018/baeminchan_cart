class Cart {

    constructor() {
        this.renderCartProducts = this.renderCartProducts.bind(this);
        this.deleteProduct = this.deleteProduct.bind(this);
    }

    getProducts() {
        return fetch('/api/cart', { credentials: "same-origin" })
                .then(this.validateResponse)
    }

    addProduct(id, count) {
        return fetch(`/api/cart/products/${id}${count ? '?count=' + count : ''}`,
                { method: 'post', credentials: "same-origin" })
                    .then(this.validateResponse)
    }

    updateProduct(id, count) {
        return fetch(`/api/cart/products/${id}?count=${count}`,
                { method: 'put', credentials: "same-origin" })
                    .then(this.validateResponse)
    }

    deleteProduct(id, count) {
        return fetch(`/api/cart/products/${id}${count ? '?count=' + count : ''}`,
                { method: 'delete', credentials: "same-origin" })
                    .then(this.validateResponse)
    }

    validateResponse(response) {
        if (!response.ok) {
            throw new Error('Error occured!');
        }
        return response.json();
    }    

    renderCartProducts({ data }) {
        const totalCount = Object.values(data).reduce((sum, count) => sum += count, 0);
        totalCount ? this.displayExist(totalCount) : this.displayNotExist();
    }
    
    displayExist(length) {
        $('#basket-counter').innerText = length;
        $('#cart_display_none').style.display = 'none';
        $('#cart_display_exist').style.display = 'block';
        $('#go_to_cart').closest('div').classList.remove('empty');
    }
    
    displayNotExist() {
        $('#cart_display_exist').style.display = 'none';
        $('#cart_display_none').style.display = 'block';
        $('#go_to_cart').closest('div').classList.add('empty');
    }
    
    animateBasketToaster() {
        const toaster = $('#basket-toaster');
        toaster.classList.add('active');
        setTimeout(() => toaster.classList.remove('active'), 1000);
    }

}