var data='[{"options":["Excellent","Very Good","Good","Fair","Poor"],"text":"Professor speaks clearly and is audible","id":"collapse1"}]';
var requestId;

function getCollapseDiv(id,text)
{
	var dTag=document.createElement('div');
	dTag.setAttribute('class','panel-group');
	var d1Tag=document.createElement('div');
	d1Tag.setAttribute('class','panel panel-default');
	var d2Tag=document.createElement('div');
	d2Tag.setAttribute('class','panel-heading');
	var hTag=document.createElement('h4');
	hTag.setAttribute('class','panel-title');
	var aTag=document.createElement('a');
	aTag.setAttribute('data-toggle','collapse');
	aTag.setAttribute('href','#'+id);
	aTag.innerHTML=text;
	hTag.appendChild(aTag);
	d2Tag.appendChild(hTag);
	d1Tag.appendChild(d2Tag);
	d2Tag=document.createElement('div');
	d2Tag.setAttribute('id',id);
	d2Tag.setAttribute('class','panel-collapse collapse');
	d1Tag.appendChild(d2Tag);
	dTag.appendChild(d1Tag);
	return dTag;
}

function getTextArea(id)
{
	var tTag=document.createElement('textarea');
	tTag.setAttribute('placeholder','Write your text here..');
	tTag.setAttribute('rows','4');
	tTag.setAttribute('style','height:auto;');
	return tTag;
}

function makeFeedbackPage(course,name,id)
{
	requestId=id;
	$('#contentArea').empty();
	var c=document.getElementById('contentArea');
	var hTag=document.createElement('h2');
	hTag.innerHTML=course+"<br>";
	c.appendChild(hTag);
	var h1Tag=document.createElement('h4');
	h1Tag.innerHTML=name+"<br><br>";
	c.appendChild(h1Tag);
	var pTag=document.createElement('p');
	pTag.innerHTML="Click on the panel to open and close it.";
	c.appendChild(pTag);
	c.appendChild(getCollapseDiv('collapse1','About the faculty member'));
	c.appendChild(getCollapseDiv('collapse2','About the course'));
	c.appendChild(getCollapseDiv('collapse3','Overall'));
	var s1Tag=document.createElement('span');
	s1Tag.innerHTML="<br><a>Suggestions/Comments to professor</a><br><br>";
	c.appendChild(s1Tag);
	c.appendChild(getTextArea(1));
	var s2Tag=document.createElement('span');
	s2Tag.innerHTML="<br><br><a>Suggestions/Comments on syllabus</a><br><br>";
	c.appendChild(s2Tag);
	c.appendChild(getTextArea(1));
	var s3Tag=document.createElement('span');
	s3Tag.innerHTML="<br><br><a>Suggestions/Comments on materials provided during course</a><br><br>";
	c.appendChild(s3Tag);
	c.appendChild(getTextArea(1));
	var s4Tag=document.createElement('span');
	s4Tag.innerHTML="<br><br><a>Any other Suggestions/Comments</a><br><br>";
	c.appendChild(s4Tag);
	c.appendChild(getTextArea(1));
	var bTag=document.createElement('button');
	bTag.setAttribute('class','main-button icon-button');
	bTag.innerHTML="Submit Feedback";
	var s5Tag=document.createElement('span');
	s5Tag.innerHTML="<br><br>";
	c.appendChild(s5Tag);
	c.appendChild(bTag);
	addElement(data);
}

function addElement(data)
{
	var elementList=JSON.parse(data);
	for(var i=0;i<elementList.length;i++)
	{
		var dTag=document.createElement('div');
		dTag.setAttribute('class','panel-body');
		dTag.innerHTML=elementList[i].text;
		var d1Tag=document.createElement('div');
		d1Tag.setAttribute('class','pull-right');
		for(var j=0;j<elementList[i].options.length;j++)
		{
			var iTag=document.createElement('input');
			iTag.setAttribute('type','radio');
			iTag.setAttribute('name','r'+i);
			iTag.setAttribute('value',elementList[i].options[j]);
			var sTag=document.createElement('span');
			sTag.innerHTML="&nbsp;&nbsp;"+elementList[i].options[j]+"&nbsp;&nbsp;";
			d1Tag.appendChild(iTag);
			d1Tag.appendChild(sTag);
		}
		dTag.appendChild(d1Tag);
		var c=document.getElementById(elementList[i].id);
		c.appendChild(dTag);
	}
}

function getRequests()
{
	$('#contentArea').empty();
	var c=document.getElementById('contentArea');
	var hTag=document.createElement('h2');
	hTag.innerHTML="Feedback Requests<br><br>";
	c.appendChild(hTag);	
	c.appendChild(getCollapseDiv("a1","Distributed Systems"));
	c.appendChild(getCollapseDiv("a2","Software Engineering"));
	addRequest("Feedback Request-1","Oct 18, 2017 - 4:00 PM","green","a1");
	addRequest("Feedback Request-2","Nov 20, 2017 - 8:10 PM","red","a1");
}

function addRequest(name,date,type,id)
{
	var dTag=document.createElement('div');
	dTag.setAttribute('class','panel-body');
	var d1Tag=document.createElement('div');
	var aTag=document.createElement('a');
	aTag.innerHTML=name+"<br>Initiated on : "+date
	var d2Tag=document.createElement('div');
	d2Tag.setAttribute('class','pull-right');
	var iTag=document.createElement('img');
	iTag.setAttribute('width','40px');
	iTag.setAttribute('height','40px');
	if(type=="green")
	{
		iTag.setAttribute('src','./img/green.png');
		d2Tag.innerHTML="Submitted&nbsp;&nbsp;";
	}	
	else	
	{
		aTag.setAttribute("onClick","makeFeedbackPage()");
		iTag.setAttribute('src','./img/red.png');
		d2Tag.innerHTML="Pending&nbsp;&nbsp;";
	}	
	d1Tag.appendChild(aTag);
	d2Tag.appendChild(iTag);
	dTag.appendChild(d1Tag);
	dTag.appendChild(d2Tag);
	var c=document.getElementById(id);
	c.appendChild(dTag);
}