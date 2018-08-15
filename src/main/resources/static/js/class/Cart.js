class Cart {

    constructor() {
        this.count = this.count.bind(this);
        this.save = this.save.bind(this);
        this.update = this.update.bind(this);
        this.delete = this.delete.bind(this);
        this.renderCounter = this.renderCounter.bind(this);
        this.animateToaster = this.animateToaster.bind(this);
    }

    setCounterView(counterView) {
        this.counterView = counterView;
    }

    setToasterView(toasterView) {
        this.toasterView = toasterView;
    }

    count() {
        return fetch('/api/cart/products/count', { credentials: "same-origin" })
                .then(this.validateResponse)
                .then(this.renderCounter)
                .then(this.animateToaster);
    }

    save(productId, count) {
        return fetch(`/api/cart/products/${productId}${count > 0 ? '?count=' + count : ''}`, {
                    method: 'post',
                    credentials: "same-origin"
                })
                .then(this.validateResponse)
                .then(this.renderCounter)
                .then(this.animateToaster);
    }

    update(productId, count) {
        return fetch(`/api/cart/products/${productId}?count=${count}`, {
                    method: 'put',
                    credentials: "same-origin"
                })
                .then(this.validateResponse);
    }

    delete(productId) {
        return fetch(`/api/cart/products/${productId}`, {
                    method: 'delete',
                    credentials: "same-origin"
                })
                .then(this.validateResponse);
    }

    validateResponse(response) {
        if (!response.ok) {
            throw new Error('Error occured!');
        }
        return response.json();
    }

    renderCounter({ data }) {
        this.counterView && this.counterView.renderCounter(data);
    }

    animateToaster() {
        this.toasterView && this.toasterView.animateToaster();
    }

}