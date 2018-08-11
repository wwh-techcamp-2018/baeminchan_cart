class ShoppingCartSideBar {
    constructor() {
        this.noItemAddedLabel = $('.top_box_number > span');
        this.totalItemKindLabel = $('#basket-counter');
        this.basketToaster = $('#basket-toaster');
    }

    fetchCurrentItemCountLabel(totalKind) {
        this.noItemAddedLabel.style.display = 'none';
        $('#cart_display_exist').style.display = 'block';
        this.totalItemKindLabel.innerText = totalKind;
    }

    notifyItemAdded(title) {
        clearTimeout(this.currentTimeout);
        this.basketToaster.querySelector('span').innerText = title;
        this.basketToaster.classList.add('show');
        this.currentTimeout = setTimeout(() => this.basketToaster.classList.remove('show'), 1500);
    }
}