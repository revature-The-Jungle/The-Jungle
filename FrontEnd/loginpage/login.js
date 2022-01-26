const username = document.getElementById("username");
const passcode = document.getElementById("passcode");
const url = "http://localhost:8080";
let loginStatus = false;
let signupStatus = false;

async function login() {

    let response = await fetch("http://localhost:8080/user/login", {
        method:"POST",
        mode:"cors",
        headers:{"Content-Type": "application/json"},
        body: JSON.stringify({
            username: username.value,
            passcode: passcode.value
        })
    })
}

const displayMessage = (errorMessage, hidden) => {
    hidden = true;
    const div = document.getElementById(nameOfContainer); //  Need this name
    const message = document.createElement("p");
    message.setAttribute("id", id);
    message.textContent = errorMessage;
    div.appendChild(message);
    setTimeout(deletemessage, 2500, id, hidden)
}

if (response.status === 200){
    let body = await response.json();
    //  Storing information for later
    sessionStorage.setItem("userId", body.userId);
    sessionStorage.setItem("firstName", body.firstName);
    sessionStorage.setItem("lastName", body.lastName);
    sessionStorage.setItem("email", body.email);
    sessionStorage.setItem("username", body.username);
    sessionStorage.setItem("userAbout", body.userAbout);
    sessionStorage.setItem("userBirthDate", body.userBirthDate);
    sessionStorage.setItem("imageFormat", body.imageFormat);
    console.log(body);
    window.location.href="secondPageWhateverTheTitleIs.html" //  Need this location
} else {
    username.value="";
    passcode.value="";
    if(!loginStatus) {
        displayMessage("Incorrect Username or Password");
    }
}