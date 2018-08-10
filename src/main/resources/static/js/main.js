function $(selector) {
    return document.querySelector(selector);
}

function $_all(seletor) {
    return document.querySelectorAll(seletor);
}

function $_value(selector) {
    return $(selector).value;
}

function fetchManager({url, method, body, headers, callback}) {
    fetch(url, {method, body, headers, credentials: "same-origin"})
        .then((response) => {
            if(response.status === 200){
                return;
            }else if(response.status === 500){
                return;
            }else {
                return response.json();
            }
        }).then((result) => {
            callback(result);
    });
}