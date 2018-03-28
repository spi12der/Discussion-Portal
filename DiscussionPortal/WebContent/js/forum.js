
function getPostDetails(id)
{
    var url='/DiscussionPortal/PostDetail?post='+id;
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() 
    {
        if (xhr.readyState == 4) 
        {
            var data = xhr.responseText;
            makePost(data);
        }
    }
    xhr.open('POST', url, true);
    xhr.send(null);
}

function makePost(data)
{
	forumFlag=true;
    var postDetail=JSON.parse(data);
    $("#preloader").fadeIn();
    $("#preloader").delay(600).fadeOut();
    $('#postList').empty();
    $('#postDetails').empty();
    var c=document.getElementById('postDetails');
    c.appendChild(getPostDiv(postDetail));
}

function getPostDiv(post)
{
    var dTag=document.createElement('div');
    dTag.setAttribute('class',"media");
    var d1Tag=document.createElement('div');
    d1Tag.setAttribute('class',"media-left");
    var imgTag=document.createElement('img');
    imgTag.setAttribute('src',"./img/avatar.png");
    d1Tag.appendChild(imgTag);
    dTag.appendChild(d1Tag);
    var d2Tag=document.createElement('div');
    d2Tag.setAttribute('class','media-body');
    var hTag=document.createElement('h4');
    hTag.setAttribute('class','media-heading');
    hTag.innerHTML=post.author;
    var pTag=document.createElement('p');
    pTag.innerHTML=post.description;
    d2Tag.appendChild(hTag);
    d2Tag.appendChild(pTag);
    var d3Tag=document.createElement('div');
    d3Tag.setAttribute('class','date-reply');
    var sTag=document.createElement('span');
    sTag.innerHTML=post.date;
    d3Tag.appendChild(sTag);
    var aTag=document.createElement("a");
    aTag.setAttribute('class','reply');
    aTag.setAttribute('href','#');
    aTag.innerHTML="reply";
    d3Tag.appendChild(aTag);
    d2Tag.appendChild(d3Tag);
    dTag.appendChild(d2Tag);
    for(var i=0;i<post.replies.length;i++)
    {
        dTag.appendChild(post.replies[i]);
    }
    return dTag;
}