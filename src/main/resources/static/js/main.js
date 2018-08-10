function $(selector) {
    return document.querySelector(selector);
}

function $_value(selector) {
    return $(selector).value;
}

function fetchManager({url, method, body, headers, callback}) {
    fetch(url, {method, body, headers, credentials: "same-origin"})
        .then((response) => {
            const value = response;
            if(200 <= value.status && value.status < 400) {
                return value.json();
            } 
        }).then((result) => {
            callback(result);
    });
}