const url = "http://127.0.0.1:5000/";

/** -----------------------------------------------------Create Group------------------------------------------------------------ */
async function createGroup() {
    // const userId = sessionStorage.getItem("userId");
    const groupName = document.getElementById("groupName");
    const groupAbout = document.getElementById("groupAbout");
    let groupCreated = {"groupId": 0, "userId": 9000, "groupName": groupName.value.trim(), "groupAbout": groupAbout.value.trim(), "imageFormat": "imageFormat"};
    const htmlBody = document.getElementById("htmlBody");

    if (groupName.length == 0 || groupAbout.length == 0) {
        alert("You must fill in all inputs!");
        return;
    }
    if (groupName.length < 3) {
        alert("Group name should be at least three characters long!");
        return;
    }
    if (groupName > 40) {
        alert("You have exceeded the 40-character limit!");
        return;
    }
    if (groupAbout > 500) {
        alert("You have exceeded the 500-character limit!");
        return;
    }

    let response = await fetch(url + "group", {method: "POST", mode: "cors", headers: {"Content-Type": "application/json"},
        body: JSON.stringify(groupCreated)});

    let groupObject = await response.json();
    // console.log(groupObject);
    if (groupObject.message) {
        alert(groupObject.message);
    }
    // else {
    //     populateNewGroup(body);
    // }
}

// function populateNewGroup(createGroupBody) {
//     for (let property of createGroupBody) {
//         let 
//     }
// }

const submitCreateGroup = document.getElementById("submitCreateGroup");
submitCreateGroup.addEventListener("click", createGroup);



/** -----------------------------------------------------Join Group------------------------------------------------------------ */
async function joinGroup(groupId, userId) {
    let groupId = document.getElementById("groupId");
    let userId = document.getElementById("userId");
    let joinGroup = {"groupId": groupId.value, "userId": userId.value};

    let response = await fetch(url + `group/join/${groupId}/${userId}`, {method: "POST", mode: "cors", headers: {"Content-Type": "application/json"},
        body: JSON.stringify(joinGroup)});
    
    let response = await response.json();

    if (response.ok) {
        alert("You have joined successfully!");
    }
}