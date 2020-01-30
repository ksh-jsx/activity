<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
    <head>
        <title>웹서버 프로그래밍</title>
        <meta name="viewport" content="width=device-width" content="initial-scale=1.0">
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="/static/slick/slick.css"/>
        <link rel="stylesheet" type="text/css" href="/static/slick/slick-theme.css"/>
        <link rel="stylesheet" href="../css/import.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c32c1763e65921610b0b14021a17138f&libraries=services,clusterer"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <link rel="stylesheet" href="https://cdn.pannellum.org/2.3/pannellum.css"/>
        <script type="text/javascript" src="https://cdn.pannellum.org/2.3/pannellum.js"></script>
       
        <script>
        </script>
    </head>
    <body>
        <nav>
            <div class="nav_container" >
                <a href="/">
                    <img src="../img/main_logo.png">
                </a>
                <div id="web_nav">
                    <ul class="ul1">
                            <li>&nbsp;&nbsp;방방?</li>
                            <li>&nbsp;&nbsp;이용방법</li>
                            <li><a href="{% url 'cust_serv' %}">&nbsp;&nbsp;고객센터</a></li>
                            <!-- 
                            <li><a href="{% url 'mypage' %}">&nbsp;&nbsp;내정보</a></li>
                            <li><a href="{% url 'logout' %}">&nbsp;&nbsp;로그아웃</a></li>
                            -->
                            <li><a href="{% url 'signup' %}">&nbsp;&nbsp;회원가입</a></li>
                            <li><a href="{% url 'login' %}?next={{request.path}}">&nbsp;&nbsp;로그인</a></li>
                        </ul>

                    <ul class="ul2">
                        <li onclick="is_active()">&nbsp;&nbsp;방 내놓기</li>
                        <li>&nbsp;·&nbsp;</li>
                        <li onclick="location.href='/search_univ'">&nbsp;&nbsp;매물보기</li>
                    </ul>
                </div>
                <div id="wrapper" onclick="setTime1()">
                    <div id="line-wrapper">
                        <!-- 추가된 부분 -->
                        <div id="line-top" class="line init top-reverse"></div>
                        <div id="line-mid" class="line init mid-reverse"></div>
                        <div id="line-bot" class="line init bot-reverse"></div>
                    </div>
                </div>
            </div>
        </nav>
        <a href="{% url 'check' %}" style="display: none" id="get_username_submit"></a>
        </form>
        <div class="overlay"></div>
        <div id="sideBar" style="display: none;overflow: auto">
            <div style="min-height:100%">
                <div class="side_top">
                    <div>
                        <img src="../img/main_logo2.png" style="position: relative;top:10px;left:10px;max-width:100px;width:50%;height:48px;">
                        <span onclick="document.getElementById('wrapper').click();"><img src="../img/close-button.png" width="30" height="30"></span>
                        {% if user.is_authenticated %}
                        
                        {% else %}
                        <div style="height: 0;padding:0"></div>
                        {% endif %}
                    </div>
                    {% if user.is_authenticated %}
                    <div class="user_info">
                        <div class="user_img">
                            <img src="../img/user.png" width="100%" height="100%">
                            <div class="right"><a href="{% url 'logout' %}">로그아웃</a></div>
                        </div>
                        <div class="user_name">{{user.name}} | {{user.username}}</div>
                    </div>
                    {% else %}
                    <div class="not_authen">
                        <div class="left"><img src="../img/login.png" width="30" height="30"><a href="{% url 'login' %}?next={{request.path}}">&nbsp;로그인</a></div>
                        <div class="right"><img src="../img/join.png" width="30" height="30"><a href="{% url 'signup' %}">&nbsp;회원가입</a></div>
                    </div>
                    {% endif %}
                </div>
                <div id="sideBarList">
                    <div><a>매물보기</a></div>
                    <div><span onclick="is_active()">방내놓기</span></div>
                    <div id="mainDiv1" onclick="openSide(1)"><a id="main_a1">고객센터</a></div>
                    <div id="sideDiv1" style="display: none;height: 92px">
                        <div onclick="location.href='cust_service1/'"> - <a>FAQ</a></div>
                        <div onclick="location.href='cust_service1/'"> - <a>고객의 소리</a></div>
                    </div>
                    <div><a>방방?</a></div>
                    {% if user.is_authenticated %}
                    <div id="mainDiv2" onclick="openSide(2)"><a id="main_a2">내 정보 & 방 관리</a></div>
                    <div id="sideDiv2" style="display: none;height: 138px">
                        <div> - <a>내정보 보기</a></div>
                        <div> - <a>내가 찜한 방</a></div>
                        <div> - <a>내가 올린 방</a></div>
                    </div>
                    {% else %}
                    <div id="mainDiv2"><a href="#">로그인 후 이용해주세요</a></div>
                    {% endif %}
                    <div id="mainDiv3" onclick="openSide(3)"><a id="main_a3">계약관리</a></div>
                    <div id="sideDiv3" style="display: none;height: 92px">
                        <div> - <a>방방 안전계약이란?</a></div>
                        <div> - <a>나의 계약 관리</a></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container" id="container">