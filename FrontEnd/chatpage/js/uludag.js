// small helper function for selecting element by id
let id = id => document.getElementById(id);

//Establish the WebSocket connection and set up event handlers

sessionStorage.setItem("userId","10000");
sessionStorage.setItem("userName", "Irfan")
groupdId = Math.floor(Math.random() * 10 + 10);
async function getGroups(){

    sessionStorage.getItem("userId",);

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
        id("chat").insertAdjacentHTML("beforeend", "<p>" + "<span style='font-weight:700;color:green'>"+data.userName+": </span>"+data.chatContent + "</p>");
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