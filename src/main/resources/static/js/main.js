function $(selector) {
    return document.querySelector(selector);
}

function $All(selector) {
    return document.querySelectorAll(selector);
} 

function $_value(selector) {
    return $(selector).value;
}

function fetchManager({url, method, body, headers, callback}) {
    fetch(url, {method, body, headers, credentials: "same-origin"})
        .then((response) => {
            const value = response;
            if(value.status == 200) {
                return value.json();
            } else {
                return value.json();
            }

        }).then((result) => {
            callback(result);
    });
}