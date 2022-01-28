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
function createChatConnection(){
console.log(groupdId)
ws = new WebSocket("ws://" + "localhost" + ":" + "8080" + "/chat/" + 16 + "/" + "irfanold");

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