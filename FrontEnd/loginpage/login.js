
const usernames = document.getElementById("usernameInput");
const passcodes = document.getElementById("passcodeInput");
const submitLogin = document.getElementById("submitLogin");
submitLogin.disabled = true;
let loginStatus = false;
const specialChar2 = /[ `^*()+=\[\]{};':"\\|,<>\/~]/;
const invalidIcon = document.querySelectorAll("[id='invalid-icon']");
let invalidMessage = document.querySelectorAll("[id='signup-invalid-message']");
const url = "http://localhost:8080";
let validateCounter = 0;


const div = document.getElementById("errorMessageArea");
div.textContent = "";

async function login() {
    let response = await fetch("http://localhost:8080/user/login", {
        method:"POST",
        mode:"cors",
        headers:{"Content-Type": "application/json"},
        body: JSON.stringify({
            username: usernames.value,
            passcode: passcodes.value
        })
    })
    
if (response.status === 200){
    let body = await response.json();
    //  Storing information for later
    localStorage.setItem("userId", body.userId);
    console.log(body);
    console.log(body.userId);
    console.log("Success");
    window.location.href="../profilepage/profile-page.html"
} else {
    div.textContent = "Incorrect Username or Password";
    // displayErrorMessage("Incorrect Username or Password", "errorMessageGoesHere", loginStatus);
    // div.textContent = "";
    // username.value="";
    // passcode.value="";
    }
}

let jsonLoginObject = {
    username: "",
    passcode: ""
};

//  ----------------------------  Validation for inputs ----------------------------
usernames.addEventListener("focusin", loginUsername);
function loginUsername(){
    usernames.addEventListener("focusout", function() {
        if (usernames.value == "") {
            invalidIcon[0].style.display = "";
            invalidMessage[0].textContent = 'Username is incorrect or missing';
        }
        else if (specialChar2.test(usernames.value)){
            invalidIcon[0].style.display = "";
            invalidMessage[0].textContent = 'Cannot contain spaces or: `^*()+=[]{}"<>~|;:';
        } else {
            validateCounter += 1;
            invalidIcon[0].style.display = "none";
            invalidMessage[0].textContent = '';
            jsonLoginObject.username = usernames.value;
            console.log(jsonLoginObject.username);
        }
    });
}

passcodes.addEventListener("focusin", loginPasscode);
function loginPasscode(){
    passcodes.addEventListener("focusout", function() {
        if (passcodes.value == "") {
            invalidIcon[1].style.display = "";
            invalidMessage[1].textContent = 'Password is incorrect or missing';
        }
        else if (specialChar2.test(passcodes.value)){
            invalidIcon[1].style.display = "";
            invalidMessage[1].textContent = 'Cannot contain spaces or: `^*()+=[]{}"<>~|;:';
        } else {
            validateCounter += 1;
            invalidIcon[1].style.display = "none";
            invalidMessage[1].textContent = '';
            jsonLoginObject.passcode = passcodes.value;
            console.log(jsonLoginObject.passcode);
            if (validateCounter > 1){
                submitLogin.disabled = false;
            }
        }
    });
}
submitLogin.addEventListener("click", login);
