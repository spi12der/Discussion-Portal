function getCourseList()
{
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() 
    {
        if (xhr.readyState == 4) 
        {
            var data = xhr.responseText;
            //alert(data);
            makeCourses(data);
        }
    }
    xhr.open('POST', '/DiscussionPortal/CourseList', true);
    xhr.send(null);
}

function makeCourses(data)
{
	var c=document.getElementById('courseList');
	var courseList=JSON.parse(data);
	for(var i=0;i<courseList.length;i++)
	{
		var aTag = document.createElement('a');
		aTag.setAttribute('class',"category");
		aTag.innerHTML = courseList[i].courseName;
		c.appendChild(aTag);
	}
	makePostList(data);
}

function makePostList(data)
{
	var da='[{"postId":"123","description":"Dear students,<br>The next lecture on February","username":"Rohit Dayama","date":"18 Oct, 2017"},{"postId":"123","description":"Dear students,<br>The next lecture on February","username":"Rohit Dayama","date":"18 Oct, 2017"},{"postId":"123","description":"Dear students,<br>The next lecture on February","username":"Rohit Dayama","date":"18 Oct, 2017"}]';
	var postList=JSON.parse(da);
	var comp=document.getElementById('postList');
	for(var i=0;i<postList.length;i++)
	{
		var dTag=document.createElement('div');
		dTag.setAttribute('class',"col-md-6");
		var diTag=document.createElement('div');
		diTag.setAttribute('class',"single-blog");
		var hTag=document.createElement('h4');
		var aTag=document.createElement('a');
		aTag.innerHTML=postList[i].description;
		aTag.setAttribute('href',"https://www.iiit.ac.in/");
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