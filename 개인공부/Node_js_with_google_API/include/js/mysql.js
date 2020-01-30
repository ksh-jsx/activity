var mysql = require('mysql');
// 비밀번호는 별도의 파일로 분리해서 버전관리에 포함시키지 않아야 합니다.

var infoArr = 
[
    {Name:"후쿠오카 공항",Month:1,Date:24,time_h:5,time_m:20,lat:33.5903205871582,lng:130.4467010498047}
];

var savedName = infoArr[0].Name;
var savedMonth = infoArr[0].Month;
var savedDate = infoArr[0].Date;
var savedH = infoArr[0].time_h;
var savedM = infoArr[0].time_m;
var savedLat = infoArr[0].lat;
var savedLng = infoArr[0].lng;

var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : '111111',
  database : 'travel',
  port     : '3307'
});

connection.connect();

connection.query(`INSERT INTO infos VALUES(?,?,?,?,?,?,?)`,[savedName, savedMonth, savedDate, savedH, savedM, savedLat, savedLng], function (error, results, fields) {
  if (error) {
      console.log(error);
  }
  console.log(results);
});

connection.end();
