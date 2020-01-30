var http = require('http');
var fs = require('fs');
var url = require('url');
var express = require('express');
var app = express();
var mysql = require('mysql');
var qs = require('querystring');
var template = require('./lib/template.js');
var process='insert';
var connection = mysql.createConnection({
host     : 'localhost',
user     : 'root',
password : '981106',
database : 'travel'
});

connection.connect();
app.use(express.static('include'));

app.get('/',function(request,response){
  connection.query(`select * from infos order by Month asc,Date asc,time_h asc,time_m asc`, function(error,topics){
    var timeline ='' ;
    var banner = ``;
    var png = "";
    var bannerOn;
    var pngNon = "../images/line_blue_dot.png";
    var pngAbove = "../images/line_blue_nonAbove.png";
    var pngBottom = "../images/line_blue_nonBottom.png";
    var pngOn = "../images/line_blue.png";
    for(var i=0;i<topics.length;i++)
    {
      bannerOn  = `<div id = "planDate">${topics[i].Month}월${topics[i].Date}일</div>`;
      if(i == 0)
      {
        banner = bannerOn;
        if(topics[i].Date != topics[i+1].Date)
          png = pngNon;
        else
          png = pngAbove;
      }
      else if(i == topics.length-1)
      {
        
        if(topics[i].Date != topics[i-1].Date)
        {
          banner = bannerOn;
          png = pngNon;
        }
        else
        {
          banner = ``;
          png = pngBottom;
        }
      }
      else
      {
        if(topics[i].Date != topics[i-1].Date && topics[i].Date != topics[i+1].Date )
        {
          banner = bannerOn;
          png = pngNon;
        }
        else if(topics[i].Date > topics[i-1].Date)
        {
          banner = bannerOn;
          png = pngAbove;
        }
        else if(topics[i+1].Date > topics[i].Date)
        {
          banner = ``;
          png = pngBottom;
        }
        else
        {
          banner = ``;
          png = pngOn;
        }
      }
      timeline = timeline + 
          ` 
          ${banner}
          <li id="Object${i}" class = "obj" onclick="push('Object${i}','img${i}','mod_and_del${i}','${topics[i].Name}',${topics[i].Lat},${topics[i].Lng},${topics.length})">
            <div id="Object_img">
              <img src=${png} id = "img${i}" class="imgs">
            </div>
            <div id="mod_and_del${i}" class="modDel">
              <button onclick="modInfos('${topics[i].Name}',${topics[i].Month},${topics[i].Date},${topics[i].time_h},${topics[i].time_m},${topics[i].Lat},${topics[i].Lng},${topics[i].id});"> 수정 </button>
              <p></p>
              <form action="/delete_process" method="post">
                <input type="hidden" name="placeId" value="${topics[i].id}">
                <input type="submit" value="삭제">
              </form>
            </div>
            <div id="Object_info">${topics[i].Name}</div>
            <div id="Object_time" style="color:blue">${topics[i].time_h}:${topics[i].time_m}</div>
           
          </li>
          `
    }
    var html = template.HTML(timeline);
    response.writeHead(200);
    response.end(html);
  });
});

app.post('/insert_process', function(request, response)
{
  var body = '';
      request.on('data', function(data){
          body = body + data;
      });
      request.on('end', function(){
        var post = qs.parse(body);
        connection.query(`INSERT INTO infos(Name,Year,Month,Date,time_h,time_m,Lat,Lng) VALUES(?,?,?,?,?,?,?,?)`,[post.placeName, 2019,post.Date[6]+post.Date[7], post.Date[10]+post.Date[11], post.hour, post.minute, post.lat, post.lng], function (error, results, fields) {
          if(error){
            throw error;
          }
            response.writeHead(302, {Location: `/`});
            response.end();
          }
        )
    });
});

app.post('/modify_process', function(request, response)
{
  var body = '';
  request.on('data', function(data)
  {
      body = body + data;
  });
  request.on('end', function()
  {
    var post = qs.parse(body);
    connection.query(`UPDATE infos SET Name=?,Month=?,Date=?,time_h=?,time_m=?,Lat=?,Lng=? WHERE id=?`,[post.placeName, post.Date[6]+post.Date[7], post.Date[10]+post.Date[11], post.hour, post.minute, post.lat, post.lng, post.placeId], function (error, results) 
    {
      if(error)
        throw error;
      response.writeHead(302, {Location: `/`});
      response.end();
    })
  })
});

app.post('/delete_process', function(request, response)
{
  var body = '';
  request.on('data', function(data)
  {
      body = body + data;
  });
  request.on('end', function()
  {
    var post = qs.parse(body);
    connection.query(`DELETE FROM infos WHERE id=?`,[post.placeId], function (error, results) 
    {
      if(error)
        throw error;
      response.writeHead(302, {Location: `/`});
      response.end();
    })
  });
});

app.listen(2000);
