let url = "http://127.0.0.1:5000";

let tableBody = document.getElementById("postBody");

//--------------------------------------------------- CREATE GROUP POST FUNCTION-------------------------------------------------------

document.getElementById("sendGroupPostButton").addEventListener("click", createGroupPost);

async function createGroupPost() {
  let post_text = document.getElementById("postInput").value;
  let data = {
    "post_id": "0",
    // "user_id": Number(sessionStorage.getItem("user_id")),
    // "group_id": Number(sessionStorage.getItem("group_id")),
    "user_id": 9000,
    "group_id": 9000,
    "post_text": post_text,
    "image_data": "",
    "likes": 0,
    "date_time_of_creation": ""
  }

  let response = await fetch(url + "/group_post", {
    method: "POST",
    mode: "cors",
    headers: {
      "Content-Type": "application/json",
      'Accept': 'application/json'
    },
    redirect: "follow",
    body: JSON.stringify(data),
  });

  let responseBody = await response.json();
  if (response.status == 201) {

    console.log(responseBody);
    document.getElementById("postInfo").innerHTML = `Post sent`
    document.getElementById("postInfo").innerHTML = `
      <div class="overlap-group1" id="headingNew">
      <p> ` + post_id + `</p>
      <p> ` + user_id + `</p>
      <p> ` + post_text + `</p> 
      <p> Likes: ` + likes + `</p>
      <p> ` + date_time_of_creation + `</p>
      <button id="deletePost${post.post_id}" onclick="deletePost(${post.post_id})">Delete</button>
      </div>
        `;
  } else {
    document.getElementById("postInfo").innerHTML = `Post could not be sent`
  }
}

//--------------------------------------------------- LOAD GROUP POST FUNCTION-------------------------------------------------------
async function getPost() {
  let response = await fetch(url + "/group_post/group/9000", { //replace with "/group_post/group/" + group_id
    method: "GET",
    mode: "cors",
  });
  if (response.status === 200) {
    let body = await response.json();
    populateData(body);
  }
}

function populateData(responseBody) {
  const allpost = document.getElementById("allpost");
  for (let post of responseBody) {
    let postBox = document.createElement('div');
    postBox.innerHTML = `
    <div class="overlap-group1" id="newPost${post.post_id}">
    <p> ` + post.post_id + `</p>
    <p> ` + post.user_id + `</p>
    <p> ` + post.post_text + `</p> 
    <p> Likes: ` + post.likes + `</p>
    <p> ` + post.date_time_of_creation + `</p>
    <button id="deletePost${post.post_id}" onclick="deleteGroupPost(${post.post_id})">Delete</button>
    </div>`
    allpost.appendChild(postBox)
  }
}

getPost()

//--------------------------------------------------- DELETE GROUP POST FUNCTION-------------------------------------------------------

async function deleteGroupPost(post_id) {
  let response = await fetch(url + "/group_post/" + post_id, {
    method: "DELETE",
    mode: "cors"
  });
  const body = await response.json();
  if (response.status === 200) {
    document.getElementById("newPost" + post_id).remove();
  }
}


//----------------------------------------------- LIKE AND UNLIKE GROUP POST FUNCTION-----------------------------------------------------

// async function likePost() {}

// async function unlikePost() {}

//----------------------------------------------- COMMENTS ON GROUP POST FUNCTION-----------------------------------------------------
// TODO: Need Comments Functions