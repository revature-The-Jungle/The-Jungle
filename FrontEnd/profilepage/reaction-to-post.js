// let likeButton = document.getElementById("trigger");
console.log("JS is connected to HTML");

// likeButton.onclick = 
async function likePost(e){
    let fetchJson = JSON.stringify({ "postId": e })

    let response = await fetch(`http://127.0.0.1:5000/postfeed`, {
        method : "POST",
        body : fetchJson
    })

    let responseData = await response.json()
    console.log(responseData)
    document.getElementById("likedComment" + e).innerText.replace(response);
  
}

/*
commentButton.onclick = async function(e){
    e.preventDefault();

 

    let response = await fetch(`http://127.0.0.1:5000/postfeed/comment`, {
        method : "POST",
        body : JSON.stringify({
            commentId: 2
           
        })
        
    })

    let responseData = await response.json()
    console.log(responseData)


}

*/