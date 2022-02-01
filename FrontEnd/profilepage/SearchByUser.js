const url = "http://localhost:8080";
const searchField = document.getElementById("searchInputBox");
const searchListResults = document.getElementById("searchList");
function referToProfile(userId) {
    localStorage.setItem("targetId", userId);
    location.href="profile-page.html"; //  <--------  Users Profile Page
}
const searchUserButton = document.getElementById("searchButton");


const searchByUsername = async() => {
    // searchListResults.innerHTML = "";
    const username = searchField.value
    const response = await fetch(url + "/user/search/" + username,{
        method: "GET", 
        mode: "cors" 
    });
    
    document.getElementById("searchList").innerHTML = "";
    if (response.status === 200) {
        const body = await response.json();

        console.log(body);
        console.log(response.status);
        // console.log(response.body);
        // console.log(response.body.value);
        // console.log(response.body.length + " 2");
        // console.log(searchListResults.searchResult + " 3");
        if (body.searchResult.length === 0) {
            // console.log("User Body Value is "+user);
            searchListResults.style.display="block";
            document.getElementById("searchList").innerHTML += `<li class="list-group-item">No Results</li>`;
        } else {
            for (let user of body.searchResult) {
                searchListResults.style.display="block";
                document.getElementById("searchList").innerHTML += `<li class="list-group-item"><a onclick=referToProfile(${user.userId})>${user.username}</a></li>`;
                console.log(user.Object);
                console.log(user);
            // if (user.Object === null){
            //     console.log("User Body Value is "+user.value);
            //     searchListResults.style.display="block";
            //     document.getElementById("searchList").innerHTML += `<li class="list-group-item">No Results</li>`;
            // }
         }
        }
    
        // searchListResults.addEventListener("focusout", searchListResults.style.display="none");
    } 
}
searchUserButton.addEventListener("click", searchByUsername);
