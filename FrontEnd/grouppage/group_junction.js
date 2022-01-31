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
     let memList = document.getElementById("members")
    for(member of response){
        memList.insertAdjacentHTML("afterend",`<div>${member.first_name}</div>`)
        
        


    }
}

async function deleteRequest() {
    userId = 13
    groupId = 15
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
        let newSect = document.getElementById("groupCreator")
        newSect.innerHTML = ` <div id="groupCreator" class="creator valign-text-middle">${body[0][0]},${body[0][1]}</div>`
        let username = document.getElementById("creatorUserName")
        username.innerHTML = `<div id="creatorUserName"
        class="creator-username valign-text-middle poppins-medium-dove-gray-18px">
        @${body[0][2]}
      </div>`
    }
    
    
}
getUserInGroups()
creatorOf()