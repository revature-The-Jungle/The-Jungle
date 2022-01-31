const table = document.getElementById("groupTable")
const tableBody = document.getElementById("groupBody")

async function getAllGroupData(){
    let url = "http://127.0.0.1:5000/group";

    let response = await fetch(url);
    
    if (response.status === 200){
        let body = await response.json();
        console.log(body);
        populateData(body);  
    }else {
        alert("Problem loading group data");
    }
}

function populateData(responseBody){


    for (let group of responseBody){
        let tableRow = document.createElement("tr");
        tableRow.innerHTML = `<td>${group.groupName}</td>`;
        tableBody.appendChild(tableRow);
    }
}
getAllGroupData()

