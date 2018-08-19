function $(selector) {
    return document.querySelector(selector);
}

function $all(selector){
    return document.querySelectorAll(selector);
}

function fetchManager({ url, method, body, headers, success, error }) {
    fetch(url, {
        method,
        body,
        headers,
        credentials: "same-origin"
    }).then((result) => {
        success(result)
    }).catch(e => {
        error(e);
    })
}