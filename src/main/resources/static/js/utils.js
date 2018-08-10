export function $(selector) {
    return document.querySelector(selector);
}

export function fetchManager({ url, method, body, headers, callback }) {
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then((response) => {
            return response.json()
        }).then((result) => {
        callback(result)
    })
}
