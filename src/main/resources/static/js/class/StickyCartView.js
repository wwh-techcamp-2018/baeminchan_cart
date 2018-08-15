class StickyCartView {

    renderCounter(count) {
        count > 0 ? this.showCounter(count) : this.hideCounter();
    }

    showCounter(count) {
        $('#basket-counter').innerText = count;
        $('#cart_display_none').style.display = 'none';
        $('#cart_display_exist').style.display = 'block';
        $('#go_to_cart').closest('div').classList.remove('empty');
    }

    hideCounter() {
        $('#cart_display_exist').style.display = 'none';
        $('#cart_display_none').style.display = 'block';
        $('#go_to_cart').closest('div').classList.add('empty');
    }

    animateToaster() {
        const toaster = $('#basket-toaster');
        toaster.classList.add('active');
        setTimeout(() => toaster.classList.remove('active'), 1000);
    }

}