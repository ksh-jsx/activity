var infos = 
[
    {Name:"후쿠오카 공항",time_h:5,time_m:20,lat:33.55,lng:130.4}
];

infos.push({Name:"hello"});
console.log(infos[1].Name);

if(confirm("저장하시겠습니까?")==true)
    {
        document.getElementById("addInfo").style.display="none";
        document.getElementById("timeLine").style.display="block";
        infos.push({Name:document.getElementById("placeName").value});
        infos.push({time_h:document.getElementById("hour").value});
        infos.push({time_m:document.getElementById("minute").value});
        infos.push({lat:document.getElementById("Lat").value});
        infos.push({lng:document.getElementById("Lng").value});
        console.log(infos[0].Name);
    }
    else
        return;