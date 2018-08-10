class Cart {

    constructor() {
    }

    render(response) {
        $('#cart_display_none').style.display = "none";
        $('#cart_display_exist').style.display = "block";
        $('#basket-counter').innerText = response.totalSize;
    }

    add (id) {
        console.log(this);
        new FetchManager({
            url: '/api/cart/' + id,
            method: 'POST',
        }).onSuccess(this.render).doFetch();
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const cart = new Cart();
    $$('button.cart').forEach((e) => {
        e.addEventListener('click', ({target}) => {
            const pid = target.parentElement.getAttribute("data-id") || target.getAttribute("data-id");
            cart.add(pid);
        });
    });
});