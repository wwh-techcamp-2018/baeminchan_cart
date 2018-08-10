document.addEventListener("DOMContentLoaded", () => {
    Array.from($All(".prd_thumb_btn")).forEach(child => child.addEventListener("click", putMerchandise.bind(null, child)));
});

function putMerchandise(element){

    const pId = element.firstElementChild.value;
    const amount =  element.previousElementSibling.firstElementChild.firstElementChild.value;

    fetchManager({
        url: "/api/merchandise",
        method: "POST",
        headers: { 'content-type': 'application/json; charset=utf-8' },
        body: JSON.stringify({
            'pId' : pId,
            'amount' : amount
        }),
        success: putMerchandiseSuccess,
    });
}

function putMerchandiseSuccess(response) {
    const data = response.json().data;

    $(".top_box_number > span").replace("<span class=\"txt\" style=\"display:;\" id=\"cart_display_none\">담은상품이<br>없습니다.</span>\n", "<span>" + data +"</span>>");
}