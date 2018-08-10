function $(selector) {
    return document.querySelector(selector);
}

function $All(selector) {
    return document.querySelectorAll(selector);
}

function $_value(selector) {
    return $(selector).value;
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