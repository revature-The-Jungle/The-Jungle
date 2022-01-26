const url = "http://127.0.0.1:5000/";

/** Create Group */
async function createGroup() {
    // const userId = sessionStorage.getItem("userId");
    const groupName = document.getElementById("groupName").value.trim();
    const groupAbout = document.getElementById("groupAbout").value.trim();
    const groupCreated = {"groupId": 0, "userId": 9000, "groupName": groupName, "groupAbout": groupAbout, "imageFormat": "imageFormat"};
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

    let body = await response.json();
    console.log(body);
    if (response.message === "The group name you entered is already taken! Please try another group name.") {
        alert("The group name you entered is already taken! Please try another group name.");
    }
    else {
        populateNewGroup(body);
    }
}

// function populateNewGroup(createGroupBody) {
//     for (let property of createGroupBody) {
//         let 
//     }
// }

const submitCreateGroup = document.getElementById("submitCreateGroup");
submitCreateGroup.addEventListener("click", createGroup);