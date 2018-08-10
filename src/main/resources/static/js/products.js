document.addEventListener("DOMContentLoaded", function (evt) {
    setProductListeners();

    $("#basket-toaster").addEventListener("transitionend", function(evt) {
        console.log("Fired");
        this.classList.toggle("basket-toaster-fade");
        this.style.display = "none";
    })

    // request initial cart state!
    addProducts("-1", "-1");

});

function setProductListeners() {
    const productHolder = $("#products");
    const products = productHolder.querySelectorAll("li");

    for(const product of products) {
        product.querySelector(".btn.cart").addEventListener("click", function(evt) {
            console.log("Add Cart clicked!");
            console.log(getProductId.call(this));
            console.log(getProductCount.call(this));
            addProducts(getProductId.call(this), getProductCount.call(this), displayProductCount);
        }.bind(product));
    }
}

function getProductId() {
    const link = this.querySelector(".imgthumb").querySelector("a").getAttribute("href");
    const id = link.substr(9, link.length - 9);
    return id;
}

function getProductCount() {
    const number = this.querySelector(".buy_cnt").value;
    return number;
}

function addProducts(productId, productCount) {
    getManager({
        url: "/addProducts/" + productId + "/" + productCount,
        method: "GET",
        headers: {"Content-type": "application/json"},
        callback : displayProductCount
    });
}

let initialLoad = false;

function displayProductCount(results) {
    if(results.length) {
        $("#cart_display_none").style.display = "none";
        $("#cart_display_exist").style.display = "block";
        $("#basket-counter").innerHTML = results.length;

        if(initialLoad) {
            const basketToaster = $("#basket-toaster");
            basketToaster.querySelector("span").innerHTML = results[results.length - 1].description;
            basketToaster.style.display = "block";

            setTimeout(function() {
                basketToaster.style.display = "none";
            }, 500);
        }
        initialLoad = true;


    }
}


