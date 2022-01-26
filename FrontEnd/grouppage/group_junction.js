async function getUserInGroups() {
    groupId = 15
    url = `http://127.0.0.1:5000/GroupJunction/UserList/${groupId}`
    let response = await fetch(url)

    if(response.status === 200){
        let body = await response.json()
        console.log(body)
        createList(body)

    }
    
}

function createList(response) {
    let memList = document.getElementById("userMemList")
    for(member of response){
        let addToList = document.createElement("li")
        addToList.innerHTML = `<li>${member.first_name}</li>`
        memList.appendChild(addToList)

    }
}

async function deleteRequest() {
    userId = 13
    groupId = 16
    url = `http://127.0.0.1:5000/group/leave/${userId}/${groupId}`
    let response = await fetch(url, { method: "DELETE", headers: { "Content-Type": "application/json" }});
    if(response.status === 200){
        let message = document.getElementById("message")
        message.textContent = "You have left the group"
    }if(response.status === 400){
        let message = document.getElementById("message")
        message.textContent = response.statusText
    }
    
}

async function creatorOf() {
    groupId = 10
    url = `http://127.0.0.1:5000/creator/${groupId}`
    let response = await fetch(url)
    if(response.status === 200){
        let body = await response.json()
        console.log(body)
        let newSect = document.getElementById("test")
        let message = document.createElement("p")
        newSect.appendChild(message)
        let messageContent = document.createTextNode(body)
        message.appendChild(messageContent)
    }
    
    
}
getUserInGroups()
creatorOf()