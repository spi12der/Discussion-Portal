//var temp='{"type":"f","course":[{"name":"Distributed Systems","request":[]}]}';

function makeFacultyUI(data)
{
	$('#contentArea').empty();
	var ui=JSON.parse(data).course;
	var c=document.getElementById('contentArea');
	var hTag=document.createElement('h3');
	hTag.innerHTML="Courses";
	c.appendChild(hTag);
	for(var i=0;i<ui.length;i++)
	{
		var dTag=getCollapseDiv("cola"+i,ui[i].name);
		c.appendChild(dTag);
		var d1Tag=document.createElement('div');
		d1Tag.setAttribute('class','panel-footer');
		var bTag=document.createElement('button');
		bTag.setAttribute('class','main-button icon-button');
		bTag.setAttribute('onClick','initRequest('+ui[i].code+')');
		bTag.innerHTML="Initiate Request";
		d1Tag.appendChild(bTag);
		var k=document.getElementById("cola"+i);
		k.appendChild(d1Tag);
		for(var j=0;j<ui[i].request.length;j++)
			addFRequest("cola"+i,ui[i].request[j]);
	}
}

function addFRequest(id,request)
{
	var dTag=document.createElement('div');
	dTag.setAttribute('class','panel-body');
	var sTag=document.createElement('span');
	sTag.setAttribute('class','pull-left');
	sTag.innerHTML="<h3>Feedback Request-"+request.number+"</h3><br>Intiated on : "+request.date;
	dTag.appendChild(sTag);
	sTag=document.createElement('span');
	sTag.setAttribute('class','pull-right');
	var s1Tag=document.createElement('span');
	s1Tag.innerHTML="Responses "+request.replies+"/"+request.total+"&nbsp;&nbsp;&nbsp;";
	sTag.appendChild(s1Tag);
	var bTag=document.createElement('button');
	bTag.setAttribute('class','main-button icon-button');
	bTag.setAttribute('onClick','getReportData('+request.id+')');
	bTag.innerHTML="View Report";
	sTag.appendChild(bTag);
	dTag.appendChild(sTag);
	var c=document.getElementById(id);
	c.appendChild(dTag);
}

function initRequest(courseCode)
{
	var xhr = new XMLHttpRequest();
	var url = '/DiscussionPortal/InitRequest?code='+courseCode;
    xhr.onreadystatechange = function() 
    {
        if (xhr.readyState == 4) 
        {
            var data = xhr.responseText;
            makeUI();
        }
    }
    xhr.open('POST', url, true);
    xhr.send(null);
}