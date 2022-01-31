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
    // document.getElementById("postInfo").innerHTML = `Post sent`
    document.getElementById("postInfo").innerHTML = `
      <div class="overlap-group1" id="headingNew${post.post_id}">
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
    let Date = post.date_time_of_creation.slice(0, -7);
    let postBox = document.createElement('div');
    postBox.innerHTML = `
    <p style=" float:right;font-size: 1em;color:grey;"> ` + Date + `</p>
    <div class="overlap-group1" id="newPost${post.post_id}">
    
    <span style="font-weight: bold; margin-right: 18em; margin-top: 1em; font-size: 1.2em;"><p> ` + post.user_id + `</p></span>
    <img class="avatar" src="img/feed-avatar-1@2x.png" style="height:5em;width:5em; margin-right: 32em; margin-top: -2.8em;">
    <span style="margin-top: 1em; font-size: 1.4em;"><p> ` + post.post_text + `</p></span>
    <p> <button type="button" class="btn btn-sm btn-labeled btn-primary"> <span class="btn-label"><i class="fa fa-thumbs-up"></i></span> Like: ` + post.likes + `</i></button>
    <button class="btn btn-sm btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
    <i class="fa fa-comment"></i>
      Comment
    </button>
    </p>
  <div class="collapse" id="collapseExample">
    <div class="card card-body">
      Some placeholder content for the collapse component. This panel is hidden by default but revealed when the user activates the relevant trigger.
    </div>
  </div>
    
    <button id="deletePost${post.post_id}" onclick="deleteGroupPost(${post.post_id})" class="btn btn-sm btn-danger" style="margin-top: 10em;"><i class="fa fa-trash"></i> Delete</button>
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