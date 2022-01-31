/** -----------------------------------------------------Join Group------------------------------------------------------------ */
async function joinGroup() {
    // const groupId = localStorage.getItem("groupId").value;
    // const userId = localStorage.getItem("userId").value;
    const groupId = 7;
     const userId = 9000;

    let response = await fetch(url + `group/join/${groupId}/${userId}`, {method: "POST", mode: "cors",
        headers: {"Content-Type": "application/json"}});
    
    await response.json();   

    if (response.status === 200) {
        const groupJoined = document.getElementById("groupJoined");
        groupJoined.style.display = "block";
    }
    else {
        const groupNotJoined = document.getElementById("groupNotJoined");
        groupNotJoined.style.display = "block";
    }
}



const submitJoinGroup = document.getElementById("submitJoinGroup");
submitJoinGroup.addEventListener("click", joinGroup);