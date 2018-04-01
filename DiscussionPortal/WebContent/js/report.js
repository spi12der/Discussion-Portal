var tp,tp1,tp2;

function getReportData(id)
{
  var url='/DiscussionPortal/FeedbackFetch?requestId='+id;
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() 
  {
      if (xhr.readyState == 4) 
      {
          var data = xhr.responseText;
          makeReport(data);
      }
  }
  xhr.open('POST', url, true);
  xhr.send(null);
}

function makeReport(data)
{
  $('#contentArea').empty();
  $("#preloader").fadeIn();
  $("#preloader").delay(600).fadeOut();
  var c=document.getElementById('contentArea');
  var content=JSON.parse(data);
  var hTag=document.createElement('h4');
  hTag.innerHTML="Category wise report";
  c.appendChild(hTag);
  var dTag=document.createElement('div');
  dTag.setAttribute('id','chart_div');
  dTag.setAttribute('style','width: 1000px;height: 500px;');
  c.appendChild(dTag);
  hTag=document.createElement('h4');
  hTag.innerHTML="Overall Rating<br>";
  c.appendChild(hTag);
  dTag=document.createElement('div');
  dTag.innerHTML='<table><tr><td><div id="chart_span1" style="width: 500px;height: 500px;"></div></td><td><div id="chart_span2" style="width: 500px;height: 500px;"></div></td></tr></table>';
  c.appendChild(dTag);
  if(content.areas.length!=0)
  {
    hTag=document.createElement('h4');
    hTag.innerHTML="Areas of Improvements<br>";
    var dTag=document.createElement('div');
    dTag.setAttribute('class','widget category-widget');
    var astr="";
    for(var i=0;i<content.areas.length;i++)
    {
      astr+=(i+1)+". "+content.areas[i]+"<br>";  
    }
    dTag.innerHTML=astr;
    c.appendChild(dTag);
  }
  makeGraph(content);
}

function makeGraph(content)
{
  google.charts.load('current', {packages: ['corechart', 'bar']});
  tp=content.category;
  google.charts.setOnLoadCallback(drawBasic);
  google.charts.load('current', {'packages':['corechart']});
  tp1=content.prof;
  google.charts.setOnLoadCallback(profChart);
  google.charts.load('current', {'packages':['corechart']});
  tp2=content.course;
  google.charts.setOnLoadCallback(courseChart);
}

function drawBasic() 
{
  var data = google.visualization.arrayToDataTable([
    ['Categories', 'Rating',],
    ['Communicating skills', tp[0]],
    ['Teaching Skills', tp[1]],
    ['Subject Knowledge', tp[2]],
    ['Course Relevance', tp[3]],
    ['Course Content', tp[4]],
    ['Reading Material', tp[5]],
    ['Workload of course', tp[6]],
    ['Exam Quality', tp[7]],
    ['Students Background Knowledge', tp[8]]
  ]);

  var options = {
    title: '',
    chartArea: {width: '50%'},
    hAxis: {
      title: 'Rating',
      minValue: 0
    },
    vAxis: {
      title: 'Categories'
    }
  };

  var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

  chart.draw(data, options);
}

function profChart(prof) {

  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Category');
  data.addColumn('number', 'Rating');
  data.addRows([
    ['Excellent', tp1[4]],
    ['Very Good', tp1[3]],
    ['Good', tp1[2]],
    ['Fair', tp1[1]], 
    ['Poor', tp1[0]]
  ]);

  var options = {
    title: 'Overall Rating of Professor'
  };

  var chart = new google.visualization.PieChart(document.getElementById('chart_span1'));
  chart.draw(data, options);
}

function courseChart(course) {

  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Category');
  data.addColumn('number', 'Rating');
  data.addRows([
    ['Excellent', tp2[4]],
    ['Very Good', tp2[3]],
    ['Good', tp2[2]],
    ['Fair', tp2[1]], 
    ['Poor', tp2[0]]
  ]);

  var options = {
    title: 'Overall Rating of Course'
  };

  var chart = new google.visualization.PieChart(document.getElementById('chart_span2'));
  chart.draw(data, options);
}