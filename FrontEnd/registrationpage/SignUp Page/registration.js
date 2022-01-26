const signupFirstName = document.getElementById("signup-firstname");
const signupLastName = document.getElementById("signup-lastname");
const signupEmail = document.getElementById("signup-email");
const signupBirthdate = document.getElementById("signup-bdate");
const signupUsername = document.getElementById("signup-username");
const signupPassword = document.getElementById("signup-password");
const signupPasswordRepeat = document.getElementById("signup-repeat-password");


function getBirthDateInput(){

};


let invalidIcon = document.getElementById("invalid-icon");
let invalidMessageFirstName = document.getElementById("signup-invalid-message-firstname");
let invalidMessageLastName = document.getElementById("signup-invalid-message-lastname");
let invalidMessageEmail = document.getElementById("signup-invalid-message-email");
let invalidMessageBDay = document.getElementById("signup-invalid-message-bday");
let invalidMessageUsername = document.getElementById("signup-invalid-message-username");
let invalidMessagePassword = document.getElementById("signup-invalid-message-password");
let invalidMessagePasswordRepeat = document.getElementById("signup-invalid-message-repeat-password");




function validateSignUpInput(){
    if (signupFirstName.value == "") {
        invalidIcon.style.display = "";
        invalidMessageFirstName.textContent = 'First name is missing.';
    }
    else if (signupLastName.value == "") {
        invalidIcon.style.display = "";
        invalidMessageLastName.textContent = 'Last name is missing.';
    }
    else if (signupEmail.value == "") {
        invalidIcon.style.display = "";
        invalidMessageEmail.textContent = 'Email is missing.';
    }
    else if (signupBirthdate.value == "") {
        invalidIcon.style.display = "";
        invalidMessageBDay.textContent = 'Birthday is missing.'
    }
    else if (signupUsername.value == "") {
        invalidIcon.style.display = "";
        invalidMessageUsername.textContent = 'Username is missing.'
    }
    else if (signupPassword.value == "") {
        invalidIcon.style.display = "";
        invalidMessagePassword.textContent = 'Password is missing.'
    }
    else if (signupPasswordRepeat.value == "") {
        invalidIcon.style.display = "";
        invalidMessagePasswordRepeat.textContent = 'Matching password is missing.'
    }
    else {
        if (signupPassword.value != signupPasswordRepeat.value) {
            invalidIcon.style.display = "";
            invalidMessagePassword.textContent = 'Passwords do not match!';
            invalidMessagePasswordRepeat.textContent = 'Passwords do not match!';
        }
        else {
            registerUser();
        }
    }
};