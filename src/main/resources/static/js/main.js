function $(selector) {
    return document.querySelector(selector);
}

function $All(query) {
    return document.querySelectorAll(query);
}

function $_value(selector) {
    return $(selector).value;
}

function fetchManager({url, method, body, headers}) {
    return new Promise((resolve, reject) => {
        fetch(url, {method, body, headers, credentials: "same-origin"})
            .then((response) => {
                const status = response.status;
                const callback = (json) => {
                    if (status >= 400) {
                        const errors = json ? json.errors : undefined;
                        reject({status, errors});
                        return;
                    }

                    const data = json ? json.data : undefined;
                    resolve({status, data});
                }

                response.json().then(callback).catch(callback);
            }).catch(() => reject());
    });
}

