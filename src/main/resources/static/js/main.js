function $(selector) {
    return document.querySelector(selector);
}

function $_all(selector) {
    return document.querySelectorAll(selector);
}

function $_value(selector) {
    return $(selector).value;
}

function fetchManager({url, method, body, headers, callback}) {
    fetch(url, {method, body, headers, credentials: "same-origin"})
        .then((response) => {
            const value = response;
            if (value.status == 200) {
                return;
            } else {
                return value.json();
            }

        }).then((result) => {
        callback(result);
    });
}


function myFetchManager({url, method, body, onSuccess, onFailure}) {
    fetch(url, {method, body, headers: {"Content-type": "application/json"}, credentials: "same-origin"})
        .then((response) => {
            if (100 <= response.status && response.status < 400)
                onSuccess(response);
            else onFailure(response);
        });
}