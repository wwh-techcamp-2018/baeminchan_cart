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
            onSuccess: () => {
                location.href = '/';
            },
            onFailed: displayErrors,
            onError: () => {
                alert('요청중 문제가 발생하였습니다. 재접속 후 시도해주세요.');
            }
        });
    });


});

function displayErrors(result) {
    let appendText = "";
    $(".error-message-holder").innerHTML = ""
    for(message of result.json.errors) {
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