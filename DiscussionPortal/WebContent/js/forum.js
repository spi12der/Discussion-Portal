
function getPostDetails(id)
{
	postId=id;
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
    var title=document.getElementById('courseTitle');
    title.innerHTML=postDetail.course;
    var c=document.getElementById('postDetails');
    c.appendChild(getPostDiv(postDetail));
    updateRecentPost(postDetail.recent);
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
    aTag.setAttribute('id',post.postId);
    aTag.setAttribute('class','reply');
    aTag.setAttribute('href','#');
    aTag.setAttribute('onClick','setReplyId(this.id)');
    aTag.setAttribute('data-toggle','modal');
    aTag.setAttribute('data-target','#replyModal');
    aTag.setAttribute('data-backdrop','static');
    aTag.setAttribute('data-keyboard','false');
    aTag.innerHTML="reply";
    d3Tag.appendChild(aTag);
    d2Tag.appendChild(d3Tag);
    dTag.appendChild(d2Tag);
    for(var i=0;i<post.replies.length;i++)
    	dTag.appendChild(getPostDiv(post.replies[i]));
    return dTag;
}

function setReplyId(id)
{
	replyId=id;
}	

function replyPost()
{
	var description=document.getElementById('replyBody').value;
	var url='/DiscussionPortal/ReplyPost?postId='+replyId+'&reply='+description;
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() 
    {
        if (xhr.readyState == 4) 
        {
            var data = xhr.responseText;
            getPostDetails(postId);
        }
    }
    xhr.open('POST', url, true);
    xhr.send(null);
}

function updateRecentPost(post)
{
    $('#recentPost').empty();
    var c=document.getElementById('recentPost');
    var hTag=document.createElement('h3');
    hTag.innerHTML="Recent Posts";
    c.appendChild(hTag);
    if(post.length==0)
    {
        var p1Tag=document.createElement("p");
        p1Tag.innerHTML="No recent post found";
        c.appendChild(p1Tag);
    }
    else
    {
        for(var i=0;i<post.length;i++)
        {
            var dTag=document.createElement('div');
            dTag.setAttribute('class','single-post');
            var aTag=document.createElement('a');
            aTag.setAttribute("href","#");
            aTag.innerHTML=post[i].description;
            aTag.setAttribute('onClick',"getPostDetails("+post[i].postID+")");
            var pTag=document.createElement('p');
            pTag.innerHTML="<small>By : "+post[i].author+" - "+post[i].date+"</small>";
            dTag.appendChild(aTag);
            dTag.appendChild(pTag);
            c.appendChild(dTag);
        }
    }
}