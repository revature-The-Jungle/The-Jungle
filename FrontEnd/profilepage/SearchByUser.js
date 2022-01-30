const url = "http://localhost:8080";
const searchField = document.getElementById("searchInputBox");
function referToProfile(userId) {
    localStorage.setItem("targetId", userId);
    location.href="NewProfilePage.html"; //  <--------  Users Profile Page
}
const searchUserButton = document.getElementById("searchButton");


const searchByUsername = async() => {
    console.log("Hello");
    const username = searchField.value
    const response = await fetch(url + "/user/search/" + username,{
        method: "GET", 
        mode: "cors" 
    });
    console.log(response);
    console.log(username);
    document.getElementById("searchList").innerHTML = "";
    if (response.status === 200) {
        const body = await response.json();

        console.log(body);
        
        for (let user of body.searchResult) {
            document.getElementById("searchList").innerHTML += `<li class="list-group-item"><a onclick=referToProfile(${user.userId})>${user.username}</a></li>`
        }
    }
}
searchUserButton.addEventListener("click", searchByUsername);
// document.getElementById("magnifyGlass").addEventListener("click", searchByUsername);

// Make a div where searches will go (with id)
//  put a ul inside of div, holding the results
    // position it to match input box
    // display none
// Make event listener for input
    // change to submit 
    //  when the event listener gets triggered, it opens the div box
    // inside 