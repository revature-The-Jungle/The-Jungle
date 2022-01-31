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
        let groupSectionDiv = document.getElementById("member-3")
        console.log(response)
        for (let group of response){
            let groupsDiv = document.createElement("div");
            groupsDiv.setAttribute("class", "group-in-list");
    
            let groupImage = document.createElement("img");
            groupImage.setAttribute("class", "friend");
    
            let groupNameDiv = document.createElement("div");
            groupNameDiv.setAttribute("class", "name valign-text-middle poppins-bold-astronaut-22px");
            groupNameDiv.textContent = group.first_name;
    
            groupSectionDiv.appendChild(groupsDiv);
            groupsDiv.appendChild(groupImage);
            groupsDiv.appendChild(groupNameDiv);
    
    
        }
    }

async function deleteRequest() {
    userId = 9000
    groupId = 9000
    url = `http://127.0.0.1:5000/group/leave/${userId}/${groupId}`
    let response = await fetch(url, { method: "DELETE", headers: { "Content-Type": "application/json" }});
    if(response.status === 200){
        location.replace("../group-page.html")
        
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