const signupSubmitButton = document.getElementById("signup-submit");
signupSubmitButton.disabled = true;

const signupFirstName = document.getElementById("signup-firstname");
const signupLastName = document.getElementById("signup-lastname");
const signupEmail = document.getElementById("signup-email");
const signupBirthdate = document.getElementById("signup-bdate");
const signupUsername = document.getElementById("signup-username");
const signupPassword = document.getElementById("signup-password");
const signupPasswordRepeat = document.getElementById("signup-repeat-password");
const invalidIcon = document.querySelectorAll("[id='invalid-icon']");
let invalidMessage = document.querySelectorAll("[id='signup-invalid-message']");
let validateCounter = 0;

const specialChar1 = /[ `!@#$%^&*()_+=\[\]{};':"\\|,.<>\/?~]/;
const specialChar2 = /[ `^*()+=\[\]{};':"\\|,<>\/~]/;
const emailChar = /[@.]/;

let jsonUserObject = {
    firstName: "",
    lastName: "",
    email: "",
    username: "",
    passcode: "",
    userBirthdate: 0
};


// VALIDATE ALL THE INPUTS
// Validate first name input.
signupFirstName.addEventListener("focusin", validateFirstName());
function validateFirstName(){
    signupFirstName.addEventListener("focusout", function() {
        if (signupFirstName.value == "") {
            invalidIcon[0].style.display = "";
            invalidMessage[0].textContent = 'First name is missing.';
        }
        else if (specialChar1.test(signupFirstName.value)){
            invalidIcon[0].style.display = "";
            invalidMessage[0].textContent = 'Cannot contain spaces or special characters.';
        }
        else {
            invalidIcon[0].style.display = "none";
            invalidMessage[0].textContent = '';
            jsonUserObject.firstName = signupFirstName.value;
            validateCounter = validateCounter + 1;
            console.log(validateCounter);
            // console.log(jsonUserObject.firstName);
            if(validateCounter > 5){
                signupSubmitButton.disabled = false;
            }
        }
    });
};


// Validate last name input.
signupLastName.addEventListener("focusin", validateLastName());
function validateLastName(){
    signupLastName.addEventListener("focusout", function() {
        if (signupLastName.value == "") {
            invalidIcon[1].style.display = "";
            invalidMessage[1].textContent = 'Last name is missing.';
        }
        else if (specialChar1.test(signupLastName.value)){
            invalidIcon[1].style.display = "";
            invalidMessage[1].textContent = 'Cannot contain spaces or special characters.';
        }
        else {
            invalidIcon[1].style.display = "none";
            invalidMessage[1].textContent = '';
            jsonUserObject.lastName = signupLastName.value;
            validateCounter = validateCounter + 1;
            console.log(validateCounter);
            // console.log(jsonUserObject.lastName);
            if(validateCounter > 5){
                signupSubmitButton.disabled = false;
            }
        }
    });
};


// Validate email input.
signupEmail.addEventListener("focusin", validateEmail());
function validateEmail(){
    signupEmail.addEventListener("focusout", function() {
        if (signupEmail.value == "") {
            invalidIcon[2].style.display = "";
            invalidMessage[2].textContent = 'Email is missing.';
        }
        else if (specialChar2.test(signupEmail.value)){
            invalidIcon[2].style.display = "";
            invalidMessage[2].textContent = 'Cannot contain spaces or special characters: `^*()+=[]{}"<>~|;:';
        }
        else if (!emailChar.test(signupEmail.value)) {
            invalidIcon[2].style.display = "";
            invalidMessage[2].textContent = 'This must be an email.';
        }
        else {
            invalidIcon[2].style.display = "none";
            invalidMessage[2].textContent = '';
            jsonUserObject.email = signupEmail.value;
            validateCounter = validateCounter + 1;
            console.log(validateCounter);
            // console.log(jsonUserObject.email);
            if(validateCounter > 5){
                signupSubmitButton.disabled = false;
            }
        }
    });
};



// Validate birthday input.
signupBirthdate.addEventListener("focusin", validateBirthdate());
function validateBirthdate(){
    signupBirthdate.addEventListener("focusout", function() {
        if (signupBirthdate.value == "") {
            invalidIcon[3].style.display = "";
            invalidMessage[3].textContent = 'Birthdate is missing.';
        }
        else {
            invalidIcon[3].style.display = "none";
            invalidMessage[3].textContent = '';
            let userBDay = new Date(signupBirthdate.value).getTime();
            jsonUserObject.userBirthdate = userBDay;
            validateCounter = validateCounter + 1;
            console.log(validateCounter);
            // console.log(jsonUserObject.userBirthdate);
            if(validateCounter > 5){
                signupSubmitButton.disabled = false;
            }
        }
    });
};


// Validate username input.
signupUsername.addEventListener("focusin", validateUsername());
function validateUsername(){
    signupUsername.addEventListener("focusout", function() {
        if (signupUsername.value == "") {
            invalidIcon[4].style.display = "";
            invalidMessage[4].textContent = 'Username is missing.';
        }
        else if (specialChar2.test(signupUsername.value)){
            invalidIcon[4].style.display = "";
            invalidMessage[4].textContent = 'Cannot contain spaces or special characters: `^*()+=[]{}"<>~|;:';
        }
        else {
            invalidIcon[4].style.display = "none";
            invalidMessage[4].textContent = '';
            jsonUserObject.username = signupUsername.value;
            validateCounter = validateCounter + 1;
            console.log(validateCounter);
            // console.log(jsonUserObject.username);
            if(validateCounter > 5){
                signupSubmitButton.disabled = false;
            }
        }
    });
};



// Validate first password input.
signupPassword.addEventListener("focusin", validatePassword());
function validatePassword(){
    signupPassword.addEventListener("focusout", function() {
        if (signupPassword.value == "") {
            invalidIcon[5].style.display = "";
            invalidMessage[5].textContent = 'Password is missing.';
        }
        else if (specialChar2.test(signupPassword.value)){
            invalidIcon[5].style.display = "";
            invalidMessage[5].textContent = 'Cannot contain spaces or special characters: `^*()+=[]{}"<>~|;:';
        }
        else {
            invalidIcon[5].style.display = "none";
            invalidMessage[5].textContent = '';
            let validatedPass = signupPassword.value;
            // console.log(validatedPass);
            signupPasswordRepeat.addEventListener("focusin", validateRepeatPassword(validatedPass));
        }
    });
};


// Validate both passwords match.
function validateRepeatPassword(password){
    signupPasswordRepeat.addEventListener("focusout", function() {
        if (signupPasswordRepeat.value != password) {
            invalidIcon[6].style.display = "";
            invalidMessage[6].textContent = 'Passwords do not match.';
        }
        else {
            invalidIcon[6].style.display = "none";
            invalidMessage[6].textContent = '';
            jsonUserObject.passcode = password;
            validateCounter = validateCounter + 1;
            // console.log(jsonUserObject.passcode);
            console.log(validateCounter);
            if(validateCounter > 5){
                signupSubmitButton.disabled = false;
            }
        }
    });
};


// ROUTE TO REGISTER/CREATE USER
signupSubmitButton.addEventListener("click", registerUser);
async function registerUser(event){
    event.preventDefault();
    event.stopPropagation();
    let userFirstName = jsonUserObject.firstName;
    let userLastName = jsonUserObject.lastName;
    let userEmail = jsonUserObject.email;
    let userUsername = jsonUserObject.username;
    let userPasscode = jsonUserObject.passcode;
    let userBirthdate = jsonUserObject.userBirthdate;
    const registerRoute = "http://localhost:8080/user/registration";
    let response = await fetch(registerRoute, {
        headers:{'Content-Type':'application/json'},
        method:["POST"],
        body:JSON.stringify(
            {"userId":0,
             "firstName":userFirstName,
             "lastName":userLastName,
             "email":userEmail,
             "username":userUsername,
             "passcode":userPasscode,
             "userAbout":"",
             "userBirthdate":userBirthdate,
             "imageFormat":""}) });
    if(response.status === 201){
        let registeredUserBody = await response.json();
        window.location.href = "../profilepage/profile-page.html";
        console.log(registeredUserBody);
    }
    else {
        console.log("Not working!");
    }
}





