var courseList;
var currentIndex;
var forumFlag;

function getCourseList()
{
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() 
    {
        if (xhr.readyState == 4) 
        {
            var data = xhr.responseText;
            makeCourses(data);
        }
    }
    xhr.open('POST', '/DiscussionPortal/CourseList', true);
    xhr.send(null);
}

function makeCourses(data)
{
	currentIndex=0;
	var c=document.getElementById('courseList');
	courseList=JSON.parse(data);
	var courseTitle=document.getElementById('courseTitle');
	if(courseList==null || courseList.length==0)
	{
		courseTitle.innerHTML="No courses found";
	}
	else
	{
		courseTitle.innerHTML=courseList[0].courseName+"<br><br>";
		for(var i=0;i<courseList.length;i++)
		{
			var aTag = document.createElement('a');
			aTag.setAttribute('class',"category");
			aTag.setAttribute('href',"#");
			aTag.setAttribute('onClick',"updatePost("+i+")");
			aTag.innerHTML = courseList[i].courseName;
			c.appendChild(aTag);
		}
		getPostList(courseList[0].courseCode);
	}
}

function updatePost(index)
{
	if(currentIndex!=index || forumFlag)
	{
		forumFlag=true;
		currentIndex=index;
		$("#preloader").fadeIn();
		$("#preloader").delay(600).fadeOut();
		var courseTitle=document.getElementById('courseTitle');
		courseTitle.innerHTML=courseList[index].courseName+"<br><br>";
		$('#postList').empty();
		$('#postDetails').empty();
		getPostList(courseList[index].courseCode);
	}
}

function getPostList(courseCode)
{
	var xhr = new XMLHttpRequest();
	var url="/DiscussionPortal/PostList?courseCode="+courseCode;
    xhr.onreadystatechange = function() 
    {
        if (xhr.readyState == 4) 
        {
            var data = xhr.responseText;
            makePostList(data);
        }
    }
    xhr.open('GET', url, true);
    xhr.send(null);
}

function makePostList(data)
{
	//var da='[{"postId":"123","description":"Dear students,<br>The next lecture on February","username":"Rohit Dayama","date":"18 Oct, 2017"},{"postId":"123","description":"Dear students,<br>The next lecture on February","username":"Rohit Dayama","date":"18 Oct, 2017"},{"postId":"123","description":"Dear students,<br>The next lecture on February","username":"Rohit Dayama","date":"18 Oct, 2017"}]';
	var postList=JSON.parse(data);
	var comp=document.getElementById('postList');
	if(postList==null || postList.length==0)
	{
		comp.innerHTML="No post found";
	}
	else
	{
		for(var i=0;i<postList.length;i++)
		{
			var dTag=document.createElement('div');
			dTag.setAttribute('class',"col-md-6");
			var diTag=document.createElement('div');
			diTag.setAttribute('class',"single-blog");
			var hTag=document.createElement('h4');
			var aTag=document.createElement('a');
			aTag.innerHTML=postList[i].description;
			aTag.setAttribute('href',"#");
			aTag.setAttribute('onClick',"getPostDetails("+postList[i].postID+")");
			hTag.appendChild(aTag);
			diTag.appendChild(hTag);
			var di2Tag=document.createElement('div');
			di2Tag.setAttribute('class',"blog-meta");
			var sTag=document.createElement('span');
			sTag.setAttribute('class',"blog-meta-author");
			sTag.innerHTML="By : ";
			var a1Tag=document.createElement('a');
			a1Tag.innerHTML=postList[i].username;
			sTag.appendChild(a1Tag);
			di2Tag.appendChild(sTag);
			var di3Tag=document.createElement('div');
			di3Tag.setAttribute('class',"pull-right");
			var s2Tag=document.createElement('span');
			s2Tag.innerHTML=postList[i].date;
			di3Tag.appendChild(s2Tag);
			di2Tag.appendChild(di3Tag);
			diTag.appendChild(di2Tag);
			dTag.appendChild(diTag);
			comp.appendChild(dTag);
		}
	}
}

function createPost()
{
	var courseCode=courseList[currentIndex].courseCode;
	var post=document.getElementById('postBody').value;
	var xhr = new XMLHttpRequest();
	var url="/DiscussionPortal/CreatePost?courseCode="+courseCode+"&description="+post;
    xhr.onreadystatechange = function() 
    {
        if (xhr.readyState == 4) 
        {
            var data = xhr.responseText;
            var temp=currentIndex;
            currentIndex=-1;
            updatePost(temp);
        }
    }
    xhr.open('GET', url, true);
    xhr.send(null);
    
}