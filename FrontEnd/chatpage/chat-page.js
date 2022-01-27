const userId = sessionStorage.getItem("userId");
const userTableBody = document.getElementById("userT");


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

//Trying to display the group chats on the top right, not the user's names... 
function populateGroupNameByUserId(groupName){
    for(let user in groupName){
        let tableRow = document.createElement("p");
        tableRow.setAttribute("class", "name valign-text-middle poppins-bold-astronaut-22px");
        tableRow.innerText = groupName[user];
        userTableBody.append(tableRow);
        }
    }
getAllGroupByUserId();