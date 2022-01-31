// small helper function for selecting element by id
let id = id => document.getElementById(id);

//Establish the WebSocket connection and set up event handlers

sessionStorage.setItem("userId","10000");
sessionStorage.setItem("userName", "Irfan")
groupdId = Math.floor(Math.random() * 10 + 10);
async function getGroups(){

    sessionStorage.getItem("userId");

    // CALL THE FETCH API FOR GETTING GROUPS 

}
let ws;
function createChatConnection(groupId){
console.log(groupdId)
ws = new WebSocket("ws://" + "localhost" + ":" + "8080" + "/chat/" + groupId + "/" + localStorage.getItem("userInfo").username);

ws.onmessage = msg => updateChat(msg,ws);
ws.onclose = () => updateChat("WebSocket connection closed");

// Add event listeners to button and input field
id("send").addEventListener("click", () => sendAndClear(id("message").value));
id("message").addEventListener("keypress", function (e) {
    if (e.keyCode === 13) { // Send message if enter is pressed in input field
        sendAndClear(e.target.value);
    }
});
}

function sendAndClear(message) {

    if (message !== "") {
        let msg = {
            userId: sessionStorage.getItem("userId"),
            userName:sessionStorage.getItem("userName"),
            chatContent: id("message").value,

          };
        ws.send(JSON.stringify(msg));
        id("message").value = "";
    }
}

function updateChat(msg) { 
    let data = JSON.parse(msg.data);

    if(data.userList === undefined ){

        //if data is a string then it's Base64 code for an image
        if(typeof data === 'string'){
        //if its a message from current user then make right side chat bubble
        console.log(data.userName);
        console.log(sessionStorage.getItem("userName"));
        if(data.userName === sessionStorage.getItem("userName")){
            id("chat").insertAdjacentHTML("beforeend", "<div class='overlap-group5'>" +
                "<div class='check-the-documentation valign-text-middle poppins-medium-white-18px'>" +
                data.chatContent + 
                "</div>");
        }
        else{
            id("chat").insertAdjacentHTML("beforeend", "<div class='received-message-1'>" +
            "<img class='ellipse-1' src='img/ellipse-1@2x.png' />" +
            "<div class='overlap-group6'>" +
              "<div class='where-are-the-user-stories valign-text-middle poppins-medium-black-18px'>" +
                data.chatContent +
              "</div>" +
            "</div>" +
          "</div>");
        }
    }
    //Else it's a normal message object
    else{
        //if its a message from current user then make right side chat bubble
        console.log(data.userName);
        console.log(sessionStorage.getItem("userName"));
        if(data.userName === sessionStorage.getItem("userName")){
            id("chat").insertAdjacentHTML("beforeend", "<div class='overlap-group5'>" +
                "<div class='check-the-documentation valign-text-middle poppins-medium-white-18px'>" +
                data.chatContent + 
                "</div>");
        }
        else{
            id("chat").insertAdjacentHTML("beforeend", "<div class='received-message-1'>" +
            "<img class='ellipse-1' src='img/ellipse-1@2x.png' />" +
            "<div class='overlap-group6'>" +
              "<div class='where-are-the-user-stories valign-text-middle poppins-medium-black-18px'>" +
                data.chatContent +
              "</div>" +
            "</div>" +
          "</div>");
        }
    }
    }else {
        updateUserList(data.userList);
    }
 
}

function updateUserList(userList){
    id("chatList").innerHTML = ""
    const unique = [...new Set(userList)];
    for(let a of unique){
      id("chatList").innerHTML += `<li class="list-group-item" style="margin-right: 0.2em;"><img src="img/online.png" style="width:0.5em;height:0.5em;"> <span style="color:#20316ee8"> ${a}</span></li>`
    }
}

//inputing file img type
window.addEventListener('load', function() {

    if(this.files === undefined){
            id("send").addEventListener("click", () => sendAndClear(id("message").value));
            id("message").addEventListener("keypress", function (e) {
                if (e.keyCode === 13) { // Send message if enter is pressed in input field
                    sendAndClear(e.target.value);
                }
            });
        }
    document.querySelector('input[type="file"]').addEventListener('change', function() {
        if (this.files && this.files[0]) {
            var img = document.querySelector('img');
            // img.onload = () => {
            //     URL.revokeObjectURL(img.src);  // no longer needed, free memory
            // }
  
            // img.src = URL.createObjectURL(this.files[0]); // set src to blob url
            console.log(this.files[0]);
            id("send").addEventListener("click", () => ws.send(this.files[0]));
            
        }
    });
  });

const userId = sessionStorage.getItem("userId");
const chatGroupDiv = document.getElementById("chatGroupName");

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