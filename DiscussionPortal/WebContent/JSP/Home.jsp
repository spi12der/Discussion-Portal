<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HOME PAGE</title>
<script>
function getCourseList()
{
	var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() 
    {
        if (xhr.readyState == 4) 
        {
            var data = xhr.responseText;
            alert(data);
        }
    }
    xhr.open('POST', '/DiscussionPortal/CourseList', true);
    xhr.send(null);
}
</script>
</head>
<body onload="getCourseList();">
<form action="/DiscussionPortal/Logout">
<input type="submit" value="Logout">
</form>
</body>
</html>