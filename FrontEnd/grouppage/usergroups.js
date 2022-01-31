const tableUserGroups = document.getElementById("userGroupTable")
const tableUserGroupsBody = document.getElementById("userGroupBody")

async function getUserGroupData(){
    let url = "http://127.0.0.1:5000/group/user/10";

    let response = await fetch(url);
    
    if (response.status === 200){
        let body = await response.json();
        console.log(body);
        populateUserGroupData(body);  
    }else {
        alert("Problem loading group data");
    }
}

function populateUserGroupData(responseBody1){


    for (let group1 of responseBody1){
        let tableRow1 = document.createElement("tr");
        tableRow1.innerHTML = `<td>${group1.groupName}</td>`;
        tableUserGroupsBody.appendChild(tableRow1);
    }
}
getUserGroupData()

