class Cart {

    constructor() {
        this.renderCartProducts = this.renderCartProducts.bind(this);
    }

    getProducts() {
        fetch('/api/cart', { credentials: "same-origin" })
            .then(this.validateResponse)
            .then(this.renderCartProducts)
    }

    addProduct(id, count) {
        fetch(`/api/cart/products/${id}${count && '?count=' + count}`,
        { method: 'post', credentials: "same-origin" })
            .then(this.validateResponse)
            .then(this.renderCartProducts)
            .then(this.animateBasketToaster)
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
    }
    
    displayNotExist() {
        $('#cart_display_exist').style.display = 'none';
        $('#cart_display_none').style.display = 'block';
    }
    
    animateBasketToaster() {
        const toaster = $('#basket-toaster');
        toaster.classList.add('active');
        setTimeout(() => toaster.classList.remove('active'), 1000);
    }

}