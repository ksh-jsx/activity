<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="push"></div>
        </div>
        <footer>
            <div class="footer_wrap">
                <br>
                <div>Copyright Nanubang Inc. All right reserved.</div>
                <br>
                <div>상호 : 나누방 | 대표 : 임상욱 | 사업자등록번호 : 000-00-000000</div>
                <div> 주소 : 서울특별시 노원구 공릉로 232 수연관 207호 | 전화번호 : 02)000-0000 | 카카오톡 : 나누방</div>
                <br>
            </div>
        </footer>
        <script>
            var count = 0;
            var client_width = document.body.clientWidth;
            if(client_width<800)
            {
                document.getElementById('web_nav').style.display = "none";
                document.getElementById('wrapper').style.display = "block";
                
            }
            else
            {
                document.getElementById('web_nav').style.display = "block";
                document.getElementById('wrapper').style.display = "none";
                
            }
       


        $(window).resize(function()
        {
            var client_width = document.body.clientWidth;
            if(client_width<800)
            {
                document.getElementById('web_nav').style.display = "none";
                document.getElementById('wrapper').style.display = "block";
                
            }
            else
            {
                document.getElementById('web_nav').style.display = "block";
                document.getElementById('wrapper').style.display = "none";
                
            }

        });

        $('#line-wrapper').click(function(){
            /* 추가된 부분 */
            $('.line').removeClass('init');
            $('#line-top').toggleClass('line-top').toggleClass('top-reverse');
            $('#line-mid').toggleClass('line-mid').toggleClass('mid-reverse');
            $('#line-bot').toggleClass('line-bot').toggleClass('bot-reverse');
        });
        function visibleside()
            {
                document.getElementById("sideBar").style.display = "block";
                document.body.style.overflow = "hidden";
                document.getElementById('wrapper').style.opacity = '0'
                $("body").show();
            }
            function invisibleside()
            {
                document.getElementById("sideBar").style.display = "none";
                document.body.style.overflow = "auto";
                document.getElementById('wrapper').style.opacity = '1'
                
            }
            function setTime1()
            {
                if(document.getElementById('sideBar').style.display === 'none')
                {
                    setTimeout("visibleside()",300);
                    document.getElementById('sideBar').style.animationName = 'sideOpen';
                    document.getElementById('wrapper').style.animationName = 'opacityDown';
                    document.getElementById('line-top').style.animationName = 'line-top1';
                    document.getElementById('line-mid').style.animationName = 'line-mid1';
                    document.getElementById('line-bot').style.animationName = 'line-bot1';
                }
                else
                {
                    setTimeout("invisibleside()",400);
                    document.getElementById('sideBar').style.animationName = 'sideClose';
                    document.getElementById('wrapper').style.animationName = 'opacityUp';
                    document.getElementById('line-top').style.animationName = 'line-top2';
                    document.getElementById('line-mid').style.animationName = 'line-mid2';
                    document.getElementById('line-bot').style.animationName = 'line-bot2';
                }
            }
        function openSide(n)
		{
            var sideName = 'sideDiv'+n;
            var mainName = 'mainDiv'+n;
            var aName = 'main_a'+n;
            var sideInfo = document.getElementById(sideName);
            var mainInfo = document.getElementById(mainName);
            var aInfo = document.getElementById(aName);
            if(sideInfo.style.display === 'none')
            {
                sideInfo.style.display = 'block';
                mainInfo.style.backgroundColor = "#218261";
                aInfo.style.color = "#ffffff";
            }

            else
            {
                sideInfo.style.display = 'none';
                mainInfo.style.backgroundColor = "#ffffff";
                aInfo.style.color = "#676767";
            }

        }
        $("#wrapper").click(function() {
            count++;
            if(count % 2 ===1)
                $(".overlay").fadeIn();
            else
                $(".overlay").fadeOut();
        });
        
        function is_active()
        {
            var authenticated = '{{ user.is_authenticated }}';
            if(authenticated == 'True')
            {
            document.getElementById('get_username_submit').click();
            }
            else
            {
                alert('이 기능을 이용하려면 로그인을 먼저 하세요')
            }
        }
        </script>
    </body>
</html>
