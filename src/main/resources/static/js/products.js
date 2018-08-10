document.addEventListener("DOMContentLoaded", function (evt) {
    $_all("#products").forEach((v) => {
        const liNodes = v.getElementsByTagName("li");
        for (const liNode of liNodes) {
            new Product(this,liNode);
        }
    });
});
