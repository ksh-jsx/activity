var i;
var ob;
var count = 0;
var ob1,img1,modDel1,pName1,lat1,lng1;
var pushArr = [{one:'ob',two:'img',three:'modDel',four:'pName',five:'lat',six:'lng'}];
function mkPlan()
{
    document.getElementById("process_form").action = "/insert_process";
    document.getElementById("cancel").style.display="block";
    document.getElementById("add").style.display="none";
    document.getElementById("backbtn").style.display="block";
    document.getElementById("plusbtn").style.display="none";
    document.getElementById("addInfo").style.display="block";
    document.getElementById("timeLine").style.display="none";

    return 'insert';
}

function back()
{
    document.getElementById("cancel").style.display="none";
    document.getElementById("add").style.display="block";
    document.getElementById("backbtn").style.display="none";
    document.getElementById("plusbtn").style.display="block";
    document.getElementById("addInfo").style.display="none";
    document.getElementById("timeLine").style.display="block";
}

function push(ob,img,modDel,pName,lat,lng,loop)
{
    if(document.getElementById(ob).style.backgroundColor ===  "" || document.getElementById(ob).style.backgroundColor ===  "rgb(255, 255, 255)")
    {
        if(count===2)
        {
            pushArr.splice(2,1);
            removeRoute(pushArr[1].four);
            count--;
        }
        if(count<2)
        {
            count++;
            pushArr.push({one:ob,two:img,three:modDel,four:pName,five:lat,six:lng});
            
        }
        if(count===1)
            getinfos(pushArr[1].one,pushArr[1].two,pushArr[1].three,pushArr[1].four,pushArr[1].five,pushArr[1].six);
        
        reset(loop);
        document.getElementById(modDel).style.display = "block";        
        document.getElementById(ob).style.backgroundColor = "#A9E2F3";
        document.getElementById(ob).style.color = "#FFFFFF";
        if( document.getElementById(img).src.indexOf('Above') != -1)
        {
            document.getElementById(img).src = "../images/line_sky_nonAbove.png";
        }
        else if( document.getElementById(img).src.indexOf('Bottom') != -1)
        {
            document.getElementById(img).src = "../images/line_sky_nonBottom.png";
        }
        else if( document.getElementById(img).src.indexOf('dot') != -1)
        {
            document.getElementById(img).src = "../images/line_sky_dot.png";
        }
        else
        {
            document.getElementById(img).src = "../images/line_sky.png";
        }
        goto(pushArr[pushArr.length-1].four,pushArr[pushArr.length-1].five,pushArr[pushArr.length-1].six,'o')
    }
    else
        pull(ob,modDel,img,loop);
    
}

function pull(ob,modDel,img,loop)
{
    count--;
    if(pushArr[1].one === ob)
        pushArr.splice(1,1);
    else if(pushArr[2].one != '' && pushArr[2].one === ob)
        pushArr.splice(2,1);
    document.getElementById(ob).style.backgroundColor = "#FFFFFF";
    document.getElementById(modDel).style.display = "none";        
    document.getElementById(ob).style.color = "#000000";
   
    if( document.getElementById(img).src.indexOf('Above') != -1)
    {
        document.getElementById(img).src = "../images/line_blue_nonAbove.png";
    }
    else if( document.getElementById(img).src.indexOf('Bottom') != -1)
    {
        document.getElementById(img).src = "../images/line_blue_nonBottom.png";
    }
    else if( document.getElementById(img).src.indexOf('dot') != -1)
    {
        document.getElementById(img).src = "../images/line_blue_dot.png";
    }
    else
    {
        document.getElementById(img).src = "../images/line_blue.png";
    }
    setMapOnAll(null);
    if(pushArr.length != 1)
        removeRoute(pushArr[1].four);
    else
        removeRoute('null');
    if(count===1)
    {
        getinfos(pushArr[1].one,pushArr[1].two,pushArr[1].three,pushArr[1].four,pushArr[1].five,pushArr[1].six);
        goto(pName1,lat1,lng1,'x');
    }
}

function reset(loop)
{
    for(i=0;i<loop;i++)
    {
        document.getElementsByClassName("obj")[i].style.backgroundColor = "#FFFFFF";
        document.getElementsByClassName("modDel")[i].style.display = "none";
        document.getElementsByClassName("obj")[i].style.color = "#000000";
        if( document.getElementsByClassName("imgs")[i].src.indexOf('Above') != -1)
        {
            document.getElementsByClassName("imgs")[i].src = "../images/line_blue_nonAbove.png";
        }
        else if( document.getElementsByClassName("imgs")[i].src.indexOf('Bottom') != -1)
        {
            document.getElementsByClassName("imgs")[i].src = "../images/line_blue_nonBottom.png";
        }
        else if( document.getElementsByClassName("imgs")[i].src.indexOf('dot') != -1)
        {
            document.getElementsByClassName("imgs")[i].src = "../images/line_blue_dot.png";
        }
        else
        {
            document.getElementsByClassName("imgs")[i].src = "../images/line_blue.png";
        }

        document.getElementById(modDel1).style.display = "block";        
        document.getElementById(ob1).style.backgroundColor = "#A9E2F3";
        document.getElementById(ob1).style.color = "#FFFFFF";
        if( document.getElementById(img1).src.indexOf('Above') != -1)
        {
            document.getElementById(img1).src = "../images/line_sky_nonAbove.png";
        }
        else if( document.getElementById(img1).src.indexOf('Bottom') != -1)
        {
            document.getElementById(img1).src = "../images/line_sky_nonBottom.png";
        }
        else if( document.getElementById(img1).src.indexOf('dot') != -1)
        {
            document.getElementById(img1).src = "../images/line_sky_dot.png";
        }
        else
        {
            document.getElementById(img1).src = "../images/line_sky.png";
        }
        
        
    }   
}

function getinfos(ob,img,modDel,pName,lat,lng)
{
    ob1 = ob;
    img1 = img;
    modDel1 = modDel;
    pName1 = pName;
    lat1 = lat;
    lng1 = lng;
}

function modInfos(Name,Month,Date,Hour,Minute,Lat,Lng,id)
{
    mkPlan();
    Month_str = Month.toString();
    Date_str = Date.toString();
    if(Month_str.length === 1)Month_str = '0'+Month_str;
    if(Date_str.length === 1)Date_str = '0'+Date_str;
    document.getElementById("process_form").action = "/modify_process";
    document.getElementById("placeName").value = Name;
    document.getElementById("datepicker").value = "2019년 "+Month_str+"월 "+Date_str+"일";
    document.getElementById("hour").value = Hour;
    document.getElementById("minute").value = Minute;
    document.getElementById("Lat").value = Lat;
    document.getElementById("Lng").value = Lng;
    document.getElementById("placesId").value = id;
}
