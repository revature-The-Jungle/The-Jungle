async function getUserInGroups() {
    groupId = localStorage.getItem("groupId")
    url = `http://ec2-54-161-131-37.compute-1.amazonaws.com:5000/GroupJunction/UserList/${groupId}`
    let response = await fetch(url)

    if(response.status === 200){
        let body = await response.json()
        console.log(body)
         createList(body)
        buttonCheck(body)
         
    }
    
}

function createList(response) {
        let groupSectionDiv = document.getElementById("member-3")
        for (let group of response){
            let groupsDiv = document.createElement("div");
            groupsDiv.setAttribute("class", "group-in-list");
    
            let groupImage = document.createElement("img");
            groupImage.setAttribute("class", "friend");
    
            let groupNameDiv = document.createElement("div");
            groupNameDiv.setAttribute("class", "name valign-text-middle poppins-bold-astronaut-22px");
            groupNameDiv.innerHTML = group.first_name;
    
            groupSectionDiv.appendChild(groupsDiv);
            groupsDiv.appendChild(groupImage);
            groupsDiv.appendChild(groupNameDiv);
    
    
        }
    }

async function deleteRequest() {
    userId = JSON.parse(localStorage.getItem("userInfo")).userId;
    groupId = localStorage.getItem("groupId")
    url = `http://127.0.0.1:5000/group/leave/${userId}/${groupId}`
    let response = await fetch(url, { method: "DELETE", headers: { "Content-Type": "application/json" }});
    if(response.status === 200){
        location.href = "../group-page.html";
        
    }if(response.status === 400){
        let message = document.getElementById("message")
        message.textContent = response.statusText
    }
}

async function creatorOf() {
    groupId = localStorage.getItem("groupId")
    url = `http://127.0.0.1:5000/creator/${groupId}`
    let response = await fetch(url)
    if(response.status === 200){
        let body = await response.json()
        let newSect = document.getElementById("groupCreator")
        newSect.innerHTML = ` <div id="groupCreator" class="creator valign-text-middle">${body[0][0]},${body[0][1]}</div>`
        let username = document.getElementById("creatorUserName")
        username.innerHTML = `<div id="creatorUserName"
        class="creator-username valign-text-middle poppins-medium-dove-gray-18px">
        @${body[0][2]}
      </div>`
    }
    
    
}

async function getGroup() {
    groupId = window.localStorage.getItem("groupId")

    let url = `http://127.0.0.1:5000/group/${groupId}`

    let response = await fetch(url)

    if(response.status === 200){
        let body = await response.json()
        groupdef = document.getElementById("groupName")
        groupdef.innerHTML = body.groupName

    }
    
}

function buttonCheck(response){
    console.log(response)
    let storage = JSON.parse(localStorage.getItem("userInfo"));
    groupId = window.localStorage.getItem('groupId')
    console.log(storage.userId)
    
    for (const users of response) {
            
        if (storage.userId == users.user_id) {
                let button = document.getElementById("tbd")
                button.style.display = "block"
        } else {
            let button = document.getElementById('tbd')
            button.style.display = "none"
        }
            
    }
}
       
    

getUserInGroups()
creatorOf()
getGroup();