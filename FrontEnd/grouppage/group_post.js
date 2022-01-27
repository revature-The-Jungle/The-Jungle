let url = "http://127.0.0.1:5000";

let tableBody = document.getElementById("postBody");

async function createGroupPost() {
  const postjson = {
    postId: "0",
    userId: Number(sessionStorage.getItem("userId")),
    groupId: Number(sessionStorage.getItem("groupId")),
    postText: document.getElementById("postText").value,
    imageFormat: document.getElementById("imageFormat").value,
    likes: Number(likes),
    dateTimeOfCreation: ""
  };

  let response = await fetch(url + "/group_post", {
    method: "POST",
    mode: "cors",
    headers: {
      "Content-Type": "application/json",
    },
    redirect: "follow",
    body: JSON.stringify(postjson),
  });
  let responseBody = await response.json();
  if (responseBody == true) {
    document.getElementById("postInfo").innerHTML = `Post sent`
    document.getElementById("postInfo").innerHTML = ``

  } else {
    document.getElementById("postInfo").innerHTML = `Post could not be sent`
  }
}

async function getPost() {
  let response = await fetch(url + "/group_post/group/" + `group_id`, { //replace with get_all_group_posts_by_group_id
    method: "GET",
    mode: "cors",

  });
  if (response.status === 200) {
    let body = await response.json();
    console.log(body)
    populateData(body);
  } else {
    alert("There was a problem trying to get the reimbursement information!")
  }
}

function populateData(responseBody) {
  for (let groupPost of responseBody) {
    let tableRow = document.createElement("tr");
    tableRow.innerHTML = `<td>${groupPost.post_id}</td>
                            <td>${groupPost.user_id}</td>
                            <td>${groupPost.group_id}</td>
                            <td>${groupPost.post_text}</td>
                            <td>${groupPost.likes}</td>
                            <td>${groupPost.date_time_of_creation}</td>`
    tableBody.appendChild(tableRow)
  }
}

getPost()

async function deletePost() { }