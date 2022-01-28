const url = "http://localhost:8080";
const searchField = document.getElementById("search");
function referToProfile(userId) {
    localStorage.setItem("targetId", userId);
    location.href="NewProfilePage.html"; //  <--------  Users Profile Page
}

const searchByUsername = async() => {
    const username = searchField.value
    const response = await fetch(url + "/user/search/" + username,{
        method: "GET", 
        mode: "cors" 
    });

    document.getElementById("searchList").innerHTML = "";
    if (response.status === 200) {
        const body = await response.json();
        console.log(body);
        for (let user of body.searchResult) {
            document.getElementById("searchList").innerHTML += `<li class="list-group-item"><a onclick=referToProfile(${user.userId})>${user.username}</a></li>`
        }
    }
}
document.getElementById("searchButton").addEventListener("click", searchByUsername);