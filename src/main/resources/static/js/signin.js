document.addEventListener("DOMContentLoaded", function(evt) {
    const submitButton = $(".btn.btn_mint.btn_login");
    submitButton.addEventListener("click", function(evt) {
        evt.preventDefault();
        const postObject = {
            "email": getUserEmail(),
            "password": getUserPassword()
        };

        requestLogin(postObject)
            .then(() => {
                location.href = '/';
            }).catch(({status, errors}) => {
                displayErrors(errors);
            });
    });


});

function requestLogin(postObject) {
    return fetchManager({
        url: "/users/login",
        method: "POST",
        headers: {"content-type": "application/json"},
        body: JSON.stringify(postObject),
    });
}

function displayErrors(errors) {
    $(".error-message-holder").innerHTML = errorMessage(errors);

}

function getUserEmail() {
    return $_value("#member_id");
}

function getUserPassword() {
    return $_value("#pwd");
}