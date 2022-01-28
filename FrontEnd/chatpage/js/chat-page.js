const userId = sessionStorage.getItem("userId");
const chatGroupDiv = document.getElementById("chatGroupName");


//Logout function for the user to logout...
function logout(){
    sessionStorage.clear();
    window.location.href = "../loginpage.html" //Change the link here to the login page html...
}

// This is a sketch goal...
// function darkMode(){
//     var element = document.body;
//     element.classList.toggle("dark-mode");
// }

//Displaying the Group Names by grabbing the userId's to display the names on the top right...
async function getAllGroupByUserId(){
    let url = "http://localhost:8080/user/groupNames/" + 13;
    let response = await fetch(url);

    if(response.status === 200){
        let body = await response.json();
        console.log(body);
        populateGroupNameByUserId(body);
    }
    else{
        alert("There was a problem trying to display the group data: apologies!");
    }
}

//This is to populate the Group Names from grabbing the UserId's...
function populateGroupNameByUserId(groupName){
    for(let user in groupName){
        let chatDivGroupNames = document.createElement("div");
        chatDivGroupNames.setAttribute("class", "chat-in-list");

        let chatDivImage = document.createElement("img");
        chatDivImage.setAttribute("class", "friend");

        let chatDiv = document.createElement("div");
        chatDiv.setAttribute("class", "name valign-text-middle poppins-bold-astronaut-22px");
        chatDiv.innerText = groupName[user];
        chatGroupDiv.appendChild(chatDivGroupNames);
        chatDivGroupNames.appendChild(chatDivImage);
        chatDivGroupNames.appendChild(chatDiv);
        }
    }
getAllGroupByUserId();