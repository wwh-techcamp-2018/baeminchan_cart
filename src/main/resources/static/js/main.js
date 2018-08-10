function $(selector) {
    return document.querySelector(selector);
}

const $All = (selector) => {
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
                return;
            } else {
                return value.json();
            }

        }).then((result) => {
            callback(result);
    });
}

const fetchAsync = ({ url, method, body, headers} ) => (
    fetch(url, {method,body,headers,credentials: "same-origin"})
        .then(res => res.json())
);

const numberToLocaleString = (number) => {
    return number.toLocaleString();
};

const localeStringToNumber = (string) => {
    return Number(string.replace(/[,원]/gi, ""));
};


const registClickEvent = (target, whatToDo) => {
    target.addEventListener("click", (event) => {
        event.preventDefault();
        whatToDo(event);
    });
};