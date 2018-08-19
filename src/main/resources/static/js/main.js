function $(selector) {
    return document.querySelector(selector);
}

function $All(query) {
    return document.querySelectorAll(query);
}

function $_value(selector) {
    return $(selector).value;
}

function fetchManager({url, method, body, headers, onSuccess, onFailed, onError}) {
    fetch(url, {method, body, headers, credentials: "same-origin"})
        .then((response) => {
            let callback;
            const status = response.status;
            if (status >= 400) {
                callback = onFailed;
            } else callback = onSuccess;

            console.log(response);

            response.json().then(json => callback({status, json})).catch(() => callback({status}))
        }).catch(onError)
}

