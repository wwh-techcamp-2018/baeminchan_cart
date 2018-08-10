function renderCart(result) {
    console.log(result.product);

}

document.addEventListener("DOMContentLoaded", function(evt) {
        const addButton = $("#products")

        addButton.addEventListener("click", function(evt) {
        evt.preventDefault();
        const productInfo = evt.target.closest('li');
        const productHref = productInfo.querySelector(".prd_tlt a").getAttribute('href');
        const productId = productHref.split("/").pop(-1);
        const productCount = productInfo.querySelector(".clearfix .buy_cnt").value;

        const postObject = {
            "count": productCount
        };

        fetchManager({
            url: "/api/cart/" + productId,
            method: "POST",
            headers: {"content-type": "application/json"},
            body: JSON.stringify(productCount),
            callback: renderCart
        });
    });
});

