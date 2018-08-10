class CartEvent {
  constructor() {
    this.cartList = [];
    this.loadCartEvent();
    this.loadCartData();
  }

  loadCartEvent() {
    $_all(".prd_account.clearfix span a").forEach(arrow =>
      this.addUpDownEvent(arrow)
    );
    $("#products").addEventListener("click", this.addCartProducts.bind(this));
  }

  addCartCallback(response) {
    if (response.status === 200) {
      response.json().then(result => {
        this.upCountCartTab(result.id);
        this.onCartTabTransition(result.title);
      });
    }
  }

  onCartExistDisplay() {
    if ($("#cart_display_exist").style.display == "none") {
      $("#cart_display_none").style.display = "none";
      $("#cart_display_exist").style.display = "block";
    }
  }

  upCountCartTab(id) {
    let basketCounter = $("#basket-counter");
    if (!this.cartList.includes(id)) {
      this.onCartExistDisplay();
      basketCounter.textContent = Number.parseInt(basketCounter.textContent) + 1;
      this.cartList.push(id);
    }
  }

  onCartTabTransition(title){
    const basketToastertoolTip = $("#basket-toaster");
    $(".tooltip_arr").textContent = title;
    basketToastertoolTip.style.opacity = 1;
    setTimeout(() => {
      basketToastertoolTip.style.opacity = 0;
    }, 1000);
  }

  addCartProducts(evt) {
    const addbtn = evt.target;
    if (addbtn.dataset.role === "add-to-cart-button") {
      const eaInput = addbtn.previousElementSibling.querySelector("input");

      let cartObject = {
        id: addbtn.dataset.index,
        ea: Number.parseInt(eaInput.value)
      };

      fetchManager({
        url: "/api/product/basket",
        method: "PUT",
        headers: { "content-type": "application/json" },
        body: JSON.stringify(cartObject),
        callback: this.addCartCallback.bind(this)
      });
    }
  }

  addUpDownEvent(arrow) {
    if (arrow.classList.value === "up") {
      arrow.addEventListener("click", this.addUpEvent);
      return;
    }
    arrow.addEventListener("click", this.addDownEvent);
  }

  addUpEvent(evt) {
    const up = evt.target;
    const ea = up.parentNode.previousElementSibling.querySelector("input");
    ea.value = Number.parseInt(ea.value) + 1;
  }

  addDownEvent(evt) {
    const down = evt.target;
    const ea = down.parentNode.previousElementSibling.querySelector("input");
    const count = Number.parseInt(ea.value);

    if (count > 0) {
      ea.value = count - 1;
    }
  }

  countCartCallback(response) {
    if (response.status === 200) {
      response.json().then(result => {
        result.forEach(data => {
            this.upCountCartTab(data.product.id);
        });
      });
    }
  }

  loadCartData() {
    fetchManager({
      url: "/api/product/basket",
      method: "GET",
      headers: { "content-type": "application/json" },
      callback: this.countCartCallback.bind(this)
    });
  }
}

document.addEventListener("DOMContentLoaded", function(evt) {
  cartEvent = new CartEvent();
});
