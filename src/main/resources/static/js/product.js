const cart = new Cart();
cart.getProducts();

const productCounter = new ProductCounter();
productCounter.delegateFrom($('.desc_option_calc .prd_account'));