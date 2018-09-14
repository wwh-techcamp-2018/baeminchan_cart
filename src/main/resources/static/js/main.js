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

function popUpErrorMessage(text) {
    const errorBox = $('.error-box');
    errorBox.innerHTML = text;
    errorBox.classList.toggle('show-error-box');
    setTimeout(() => {
        errorBox.classList.toggle('show-error-box');
    }, 3000);
}

function errorMessage(errors) {
    return errors.map((e) => e.message).reduce((prev, curr) => prev + "<br/>" + curr);
}
