var sql = require('mssql'); //
var express = require('express');
var app = express();
var fs = require('fs');
var template = require('./lib/template1.js');

const hostname = '49.247.9.42';
const port = 8000;
 
var config = { //데이터베이스 정보 
    user: 'onlinesql',
    password: 'yahoda!^)*',
    server: '115.68.5.183',
    database: 'vietmed',
    options: {
        encrypt: true // Use this if you're on Windows Azure 
    }
}

app.get('/',function(request,response){
    fs.readFile('./include/html/Main.html', 'utf8', function(err, data){ //주어진 경로의 파일 내용을 읽어서 data인자에 삽입
        var html = template.HTML(data,'');
        response.writeHead(200);
        response.end(html); //response.send말고 이런 형식으로도 가능
    });//일반적으로 데이터 를 제공하지 않고 응답을 종료할 때 사용, 404 페이지에서 유용 할 수 있다.
});

app.get('/select_process',function(request,response){
    fs.readFile('./include/html/Main.html', 'utf8', function(err, data){
        var connection = new sql.ConnectionPool(config, function(err) { //config의 정보를 이용하여 데이터베이스와 연결
            if (err) return console.error('error is', err)
            var _url = req.url;

            // or: var request = connection.request();
            var req = new sql.Request(connection); // 연결된 데이터베이스에 request보냄
        
            var strQuery = `select name,age,gender from customer_info`; // 사용할 퀴리문
            var result = ``;
            req.query(strQuery, function(err, Query_data)               //퀴리문의 결과는 객체배열 형태로 Query_data에 저장
            {                                                               //{    recordsets: [ [ [Object], [Object] ] ],    Query_data는 다음과같은 구조 가짐
                for(var i=0; i<Query_data.recordset.length;i++)             //     recordset: [ { name: 'ksh', age:'22', gender:'M' }, { name: 'abc', age:'33', gender:'W' } ],
                {                                                           //     output: {},
                    result +=`<div>${Query_data.recordset[i].name}</div>`;  //     rowsAffected: [ 2 ] }
                }                                                  
                                                            
                var html = template.HTML(data,result);
                response.send(html);                                               
            });
        });
    });
});

app.post('/insert_process',function(request,response){
    
   
});


app.get('/delete_process',function(request,response){

});
  
app.listen(port, hostname, () => {
    console.log(`Server running at http://${hostname}:${port}/`);
  });
  