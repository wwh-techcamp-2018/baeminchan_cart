document.addEventListener("DOMContentLoaded", function(evt) {
    const submitButton = $(".btn.btn_mint.btn_login");
    submitButton.addEventListener("click", function(evt) {
        evt.preventDefault();
        const postObject = {
            "email": getUserEmail(),
            "password": getUserPassword()
        };

        fetchManager({
            url: "/users/login",
            method: "POST",
            headers: {"content-type": "application/json"},
            body: JSON.stringify(postObject),
            callback: displayErrors
        });
    });


});

function displayErrors(result) {
    if(result && result.status == 200) {
        window.location.href = "/";
    }
    let appendText = "";
    $(".error-message-holder").innerHTML = ""
    for(message of result.errors) {
        appendText += message.errorMessage + "<br />";

    }
    $(".error-message-holder").innerHTML = appendText;

}

function getUserEmail() {
    return $_value("#member_id");
}

function getUserPassword() {
    return $_value("#pwd");
}