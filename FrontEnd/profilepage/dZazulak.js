const userBirthDate = document.getElementById("userBirthdateInput");
const userAboutMe = document.getElementById("userAboutMeInput");
const modalMessageDiv = document.getElementById("profileModalMsg");
const followerSectionDiv = document.getElementById("followers-div");
const groupSectionDiv = document.getElementById("groups-div");
const headerUsername = document.getElementById("headerUsername");
const headerDOB = document.getElementById("headerDOB");
const headerEmail = document.getElementById("headerEmail");


async function getUserNameByUserId(){
    let url = "http://127.0.0.1:5000/user/" + 9000;
    let response = await fetch(url);

    if(response.status === 200){
        let body = await response.json();
        console.log(body);
        populateUsernameByUserId(body);
    }
    else{
        alert("There seems to be some sort of issue going on with the database: Apologies!");
    }
}

function populateUsernameByUserId(user){

    let text = user.user_birth_date.split(" ");
    let array = text[1] + " " + text[2] + " " + text[3];
    headerUsername.innerText = `${user.username}`;
    headerDOB.innerText = `${array}`;
    headerEmail.innerText = `${user.email}`;
}

/*
    Grabs the user profile information from the update profile modal and sends it through the layers
*/
async function updateUserProfileData(){
    // Will need to update this to use the current user's ID
    let url = "http://127.0.0.1:5000/user/profile/update/9000"

    let updateUserProfileJSON = JSON.stringify({"firstName": "Shouldn't change",
    "lastName": "Shouldn't change",
    "email": "Shouldn't change",
    "username": "Shouldn't change",
    "passcode": "Shouldn't change",
    "userAbout": userAboutMe.value,
    "userBirthDate": userBirthDate.value,
    "userImageFormat": "Shouldn't change"});

    let response = await fetch(url, {
        method: "PATCH",
        headers:{"Content-Type": 'application/json'},
        body:updateUserProfileJSON})

        if(response.status === 200){
            let body = await response.json();
            successMessageForProfileModal();
            console.log(body);
        }
        else{
            errorMessageForProfileModal();
        }
}

/* 
    Reset the modal data when you close it
*/
function resetProfileModalData(){
    document.getElementById("updateUserProfileForm").reset()
    modalMessageDiv.innerHTML = '';
}

/*
    Function to print error message for update profile modal
*/
function errorMessageForProfileModal(){
    modalMessageDiv.innerHTML = '';
    let profileErrorMessage = document.createElement("p");
    profileErrorMessage.innerText = 'Birthdate may not be blank';
    profileErrorMessage.style.color = 'red';
    modalMessageDiv.append(profileErrorMessage);
}

/*
    Function to print success message for update profile modal
*/
function successMessageForProfileModal(){
    modalMessageDiv.innerHTML = '';
    let profileSuccessMessage = document.createElement("p");
    profileSuccessMessage.innerText = 'Saved';
    profileSuccessMessage.style.color = 'blue';
    modalMessageDiv.append(profileSuccessMessage);
}

/*
    Grabs all the user's followers
*/
async function getUserFollowers(){
    let url = "http://127.0.0.1:5000/user/followers/32"

    let response = await fetch(url);

    if(response.status === 200){
        let body = await response.json();
        // console.log(body);
        populateUserFollowers(body);
        getFollowerImage(body);
    }
    else{
        alert("Error with followers");
    }
}

/*
    Populates the followers section with their profile image and username
*/
function populateUserFollowers(followerBody){
    for(let follower in followerBody){
        // Created div to hold the image and username div and set class name
        let followerDiv = document.createElement("div");
        followerDiv.setAttribute("class", "follower-in-list");

        // Create the image tag and set class name, sets the source to a default profile picture that gets overwritten when it is populated from the database
        let followerImage = document.createElement("img");
        followerImage.setAttribute("class", "friend");
        followerImage.setAttribute("id", `${follower}-image`);
        followerImage.setAttribute("alt", "No Image");
        followerImage.setAttribute("src", "img/default-profile-picture.jpg");

        /* 
        Creates the username div with the username as a link with an onclick function to set the localStorage of the follower when clicked.
        */
        let followerUsernameDiv = document.createElement("div");
        followerUsernameDiv.setAttribute("class", "name valign-text-middle poppins-bold-astronaut-22px");
        followerUsernameDiv.innerHTML = `<a onclick="localStorage.setItem('visitUserIdPage', ${followerBody[follower]})" class="name valign-text-middle poppins-bold-astronaut-22px" href="profile-page.html">${follower}</a>`;

        // Append the created elements to the page
        followerSectionDiv.appendChild(followerDiv);
        followerDiv.appendChild(followerImage);
        followerDiv.appendChild(followerUsernameDiv);

    }
}

/*
    Grabs each followers image from the database and populates them into the list
*/
async function getFollowerImage(followerBody){
    for(follower in followerBody){
        let image_Element = document.getElementById(`${follower}-image`);
        let url = `http://127.0.0.1:5000/user/image/${followerBody[follower]}`;
        let response = await fetch(url);
        if(response.status === 200){
            const image_text = await response.text();
            image_Element.src = image_text;
        }

}
}

/*
    Grabs all the user's groups
*/
async function getGroupsForUser(){
    let url = "http://127.0.0.1:5000/group/user/10"

    let response = await fetch(url);

    if(response.status === 200){
        let body = await response.json();
        // console.log(body);
        populateGroupsForUsers(body);
    }
    else{
        alert("Error with groups");
    }
}

/*
    Populates the groups section with their group image and group name
*/
function populateGroupsForUsers(groupBody){
    

    for (let group in groupBody){
        // Creates div to hold the image and group name div and set class name
        let groupsDiv = document.createElement("div");
        groupsDiv.setAttribute("class", "group-in-list");

        // Create the image tag and set class name, sets the source to a default picture that gets overwritten when it is populated from the database
        let groupImage = document.createElement("img");
        groupImage.setAttribute("class", "friend");
        groupImage.setAttribute("alt", "No Image");
        groupImage.setAttribute("src", "img/default-profile-picture.jpg");

        let groupNameDiv = document.createElement("div");
        groupNameDiv.setAttribute("class", "name valign-text-middle poppins-bold-astronaut-22px");
        groupNameDiv.innerHTML = `<a onclick="goToGroupPage(${groupBody[group].groupId})" id="groupLink-${groupBody[group].groupId}" class="name valign-text-middle poppins-bold-astronaut-22px" href="../individualgrouppage/individual-group-page.html">${groupBody[group].groupName}</a>`;
        groupSectionDiv.appendChild(groupsDiv);
        groupsDiv.appendChild(groupImage);
        groupsDiv.appendChild(groupNameDiv);


    }
}

    function goToGroupPage(groupId){
    // let groupLink = getElementById("groupLink-" + groupId);
    // groupLink.setAttribute("href", "")
    localStorage.setItem("groupId", groupId);
}

// async function getGroupImage(groupBody){
//     for(group in groupBody){
//         let imageElement = document.getElementById(`${groupBody[group].groupName}-image`);
//         let url = ""
//     }
// }



getUserFollowers();
getGroupsForUser();
getUserNameByUserId();