document.addEventListener('DOMContentLoaded', () => {
    new Cart();
});

class Cart {
    constructor() {
        this.cart = $('#cart');
        this.productTable = $('.tb_order_style.cart');
        this.salePrice = $('#salePrice');
        this.shippingFee = $('#shippingFee');
        this.totalPrice = $('#totalPrice');
        this.allSelectionBox = $('#check_all_cart_item');
        this.baseUrl = '/api/cart/';
        this.renderCartData();

        this.registerEvent();
    }

    registerEvent() {
        this.updateProductInCart();
    }

    updateProductInCart() {
        this.cart.addEventListener('click', (e) => { this.clickEventHandler(e) });
    }

    clickEventHandler({target}) {
        const targetClasses = target.classList;

        if (targetClasses.contains('quantity_change_confirm')) {
            const productId = this.getProductId(target);
            this.updateProductNum(productId, target);
            return;
        }

        if (targetClasses.contains('up')) {
            const buyCnt = this.buyCntBox(target);
            buyCnt.value = Number(buyCnt.value) + 1;
            return;
        }

        if (targetClasses.contains('down')) {
            const buyCnt = this.buyCntBox(target);
            if (buyCnt.value <= 1) {
                popUpErrorMessage("수량이 1보다 작을 순 없습니다.");
                return;
            }
            buyCnt.value = Number(buyCnt.value) - 1;
            return;
        }

        if (targetClasses.contains('delete_cart_item')) {
            this.updateProductDeletion(target);
            return;
        }

        if (targetClasses.contains('btn-select-delete')) {
            const selected = this.getCheckBoxArray().filter(e => e.checked);
            this.updateProductListDeletion(selected);
            return;
        }

        if (targetClasses.contains('all_product_chk')) {
            this.toggleAllSelectionBox();
            return;
        }

        if (targetClasses.contains('product_chk')) {
            this.confirmAllCheckBox();
            return;
        }
        return;
    }

    getCheckBoxArray() {
        return Array.prototype.slice.call($All('input[type=checkbox].product_chk'));
    }

    getProductId(target) {
        return target.closest('.cart_prd_list').getAttribute('data-id');
    }

    updateProductNum(productId, target) {
        const productNum = Number(this.buyCntBox(target).value);
        if (productNum <= 0) {
            return;
        }

        this.request(this.baseUrl + 'products/' + productId + "?num=" + productNum, 'PUT')
            .then(() => {
                return this.request(this.baseUrl + 'products/' + productId, 'GET');
            }).then(({data}) => {
                this.changeProductData(data, target)
                return this.renderTotalPrice();
            }).catch(({errors}) => {
                popUpErrorMessage(errorMessage(errors));
            });
    }

    updateProductDeletion(target) {
        return this.request(this.baseUrl + 'products/' + this.getProductId(target), 'DELETE')
                .then(() => {
                    return this.removeProductInTable(target);
                }).then(() => {
                    return this.renderTotalPrice();
                }).catch(({errors}) => {
                    popUpErrorMessage(errorMessage(errors));
                });
    }

    updateProductListDeletion(targetList) {
        return this.request(
                this.baseUrl + 'products',
                'DELETE',
                JSON.stringify(targetList.map(this.getProductId)),
                { 'Content-Type': 'application/json' }
            ).then(() => {
                targetList.forEach((target) => this.removeProductInTable(target));
            }).then(() => {
                return this.renderTotalPrice();
            }).catch(({errors}) => {
                popUpErrorMessage(errorMessage(errors));
            });
    }

    getRequestTargetList(targetList) {
        const requestList = [];
        for (let target of targetList) {
            requestList.push(this.request(this.baseUrl + 'products/' + this.getProductId(target), 'DELETE'));
        }
        return requestList;
    }

    removeProductInTable(target) {
        target.closest('tr').remove();
        this.allSelectionBox.checked = false;
    }

    toggleAllSelectionBox() {
        if (this.allSelectionBox.checked) {
            this.getCheckBoxArray().forEach(e => { e.checked = true; });
            return;
        }
        this.getCheckBoxArray().forEach(e => { e.checked = false; });
    }

    confirmAllCheckBox() {
        if (this.isAllCheckBoxSelected()) {
            this.allSelectionBox.checked = true;
            return;
        }
        this.allSelectionBox.checked = false;
    }

    isAllCheckBoxSelected() {
        return $All('.product_chk').length === this.getCheckBoxArray().filter(e => e.checked).length;
    }

    renderCartData() {
        this.request(this.baseUrl + 'products', 'GET')
            .then(({data}) => {
                data.forEach((productData) => {
                    $('table.cart > tbody').insertAdjacentHTML('beforeend', this.productDetailsTemplate(productData))
                });
                this.renderTotalPrice();
            }).catch(({errors}) => {
                popUpErrorMessage(errorMessage(errors));
            });
    }

    renderTotalPrice() {
        return this.request(this.baseUrl + 'price', 'GET')
            .then(({data}) => {
                this.changeTotalPrice(data);
            });
    }

    request(path, method, body, headers) {
        return fetchManager({
            url: path,
            method: method,
            body: body,
            headers: headers
        });
    }

    changeProductData(data, target) {
        const targetTd = target.closest('td').parentNode;
        targetTd.querySelector('.productPrice').innerHTML = data.price;
        targetTd.querySelector('.sumSalePrice').innerHTML = data.totalPrice;
    }

    buyCntBox(target) {
        return target.closest('td').querySelector('.buy_cnt');
    }

    changeTotalPrice(data) {
        this.salePrice.innerText = data.totalProductPrice;
        this.shippingFee.innerText = data.deliveryCharge;
        this.totalPrice.innerText = data.totalPrice;
    }

    productDetailsTemplate(data) {
        return `
            <tr class="cart_prd_list" cart_no="12585780" data-hash="I9D6D" data-subscription-flag="0" data-delivery-limit-periods-flag="1"
                data-delivery-limit="2018-07-19~2018-08-15" data-id="${data.productId}">
                <td>
                    <label class="custom_checkbox on">
                        <input type="checkbox" name="cart_select" class="bf product_chk">
                    </label>
                </td>
                <td class="thumb">
                    <a href="/shop/detail.php?hash=I9D6D&amp;cno=30011800">
                        <img src="${data.imgUrl}">
                    </a>
                </td>
                <td class="left">
                    <p class="title prd_name">
                        <a href="/shop/detail.php?hash=I9D6D&amp;cno=30011800">${data.title}</a>
                    </p>
                    <p class="soldout bf_red" style="display: none;">
                        <strong class="soldout-text"></strong>
                    </p>

                    <p class="cartItemMessage bf_red" style="display: none;"></p>
                    <p class="receipt_limit bf_red" style="">
                        배송 가능일이 한정되어 있는 상품입니다.
                        <strong class="limit-date">
                            <br>2018-07-19~2018-08-15</strong>
                    </p>
                </td>
                <td>
                    <span cartdata="" class="productPrice">${data.price}</span>원
                </td>
                <td>
                    <div class="prd_account">
                        <label>
                            <input type="number" class="buy_cnt" min="1" max="999"
                                   value="${data.productNum}" data-quantity="4"
                                   formnovalidate="">
                        </label>
                        <span>
                            <a title="수량 더하기" role="increase" class="up quantity_change">수량 더하기</a>
                            <a title="수량 빼기" role="decrease" class="down quantity_change">수량 빼기</a>
                        </span>
                    </div>
                    <button type="button" class="btn btn_white quantity_change_confirm">변경</button>
                </td>
                <td>
                    <span cartdata="origin/sum_final_sell_prc"
                          class="sumSalePrice">${data.totalPrice}</span>원
                </td>
                <td>
                    <span class="once_only bf_red">정기배송 불가</span>
                </td>
                <td>
                    <a class="ico-delete delete_cart_item" style="cursor:pointer">삭제</a>
                </td>
            </tr>
        `;
    }
}
