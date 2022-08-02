module.exports = {
    HTML:function(list)
    {
      return `
      <!DOCTYPE html>
      <html>
        <head>
          <title>Simple Map</title>
          <meta name="viewport" content="initial-scale=1.0">
          <meta charset="utf-8">
          <link rel="stylesheet" type="text/css" href="../css/main.css">
          <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
          <style>
            .ui-datepicker{ font-size: 5px; width: 165px; }
            .ui-datepicker table{width:100%;}
          </style>
          <script src="https://maps.googleapis.com/maps/api/js?"></script>
          <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyCMby8FkLkR-W_THN2plzHj4QkIxEGWvhM&callback=initMap&sensor=false&libraries=places" async defer></script>
          <script type="text/javascript" src="../js/google.js"></script>
          <script type="text/javascript" src="../js/main.js"></script>
          <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
          <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        </head>
        <body>
        <div class="article">
          <div id="map"></div>
          <div id="tree">
            <div id="mkTimeLine">
              <div id="add">일정 추가하기</div>
              <div id="cancel">취소하기</div>
              <a id="plusbtn" onclick="mkPlan()"><img src="../images/plus.png"></a>
              <a id="backbtn" onclick="back()"><img src="../images/back.png"></a>
            </div>
            <div id="timeLine">
              <ul>
              ${list}
              </ul>
            </div>
            <div id="addInfo">
              <form action="/_process" method="post" id="process_form">
                <ul>
                  <li>
                    <span>장소명</span>
                    <input type="text" name="placeName" maxlength="12" id="placeName" autocomplete="off">
                  </li>
                </ul>
                <ul>
                  <li>
                    <span>날짜&nbsp;&nbsp;&nbsp;</span>
                    <input type="text" id="datepicker" name ="Date" autocomplete="off">
                  </li>
                </ul>
                <ul>
                  <li>
                    <span>도착 시간</span>
                    <select name="hour" id="hour">
                    </select>:
                    <select name="minute" id="minute">
                    </select>
                  </li>
                </ul>
                <ul>
                  <li>
                    <span>위치 지정</span>
                    <input type="text" name="lat" id="Lat" readonly>
                    <input type="text" name="lng" id="Lng"readonly>
                    <a id="qus"><img src="../images/info.png">
                      <div id="balloon">우측 지도에서 원하는 위치에 마커를 찍으세요</div>
                    </a>
                  </li>
                </ul>
                <input type="hidden" name="placeId" id="placesId">
                <div id="okbtn">
                    <input type="submit">
                </div>
              </form>
            </div>
          </div>
        </div>
        <div id="search">
          <label for="locationTextField"><img src="../images/searcher.png"></label>
          <input id="locationTextField" type="text" size="50">
        </div>
        <div id="getRoute">
          <input id="origin-input" class="controls" type="text"
            placeholder="출발지">
          <input id="destination-input" class="controls" type="text"
            placeholder="도착지">
            
          <div id="mode-selector" class="controls">
            <input type="radio" name="type" id="changemode-walking" checked="checked">
            <label for="changemode-walking">Walking</label>
            <input type="radio" name="type" id="changemode-transit">
            <label for="changemode-transit">Transit</label>
            <input type="radio" name="type" id="changemode-driving">
            <label for="changemode-driving">Driving</label>
          </div>
        </div>
        <script>
          $('#datepicker').datepicker({
              altField : '#getdate',
              dateFormat : 'yy년 mm월 dd일',
              maxDate : 3650,
              minDate : -3650
          });
         
      
          var select1 = '';
          for (i=0;i<=23;i++)
          {
            select1 += '<option val=' + i + '>' + i + '</option>';
          }
          $('#hour').html(select1);
      
          var select2 = '';
          for (i=0;i<=60;i++)
          {
            select2 += '<option val=' + i + '>' + i + '</option>';
          }
          $('#minute').html(select2);
          </script>
        </body>
      </html>      
      `;
    }
  }
  