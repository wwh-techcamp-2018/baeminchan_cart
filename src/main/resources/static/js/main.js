function $(selector) {
    return document.querySelector(selector);
}

function $_All(selector) {
    return document.querySelectorAll(selector);
}

function $_value(selector) {
    return $(selector).value;
}

function fetchManager({url, method, body, headers, callback}) {
    fetch(url, {method, body, headers, credentials: "same-origin"})
        .then((response) => {
            if(response.status == 200) {
                return callback(response);
            } else {
                return response.json().then((result) => {
                    callback(result);
                });
            }
        })
}