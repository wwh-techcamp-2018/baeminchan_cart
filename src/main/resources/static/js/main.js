function $(selector) {
    return document.querySelector(selector);
}

function $_value(selector) {
    return $(selector).value;
}

const fetchManager = async ({url, method, body, headers, callback}) => {
    const response = await fetch(url, {
        method,
        body,
        headers,
        credentials: "same-origin"
    });
    const data = await response.json();
    callback(response.status, data);
};