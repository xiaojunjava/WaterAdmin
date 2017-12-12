//菜单
$(document).ready(function() {
    var xx = $(window).width();
    var ss = $(window).height();
    if(ss>=900&&ss<1000){
        $("td img").css("width","266");
    }
    if(ss>=800&&ss<900){
        $("td img").css("width","210");
    }
    if(ss>=700&&ss<800){
        $("td img").css("width","180");
    }
        showtime();
})

//时间
var initializationTime=(new Date()).getTime();
    function showtime()
    {
    var now=new Date();
    var year=now.getFullYear();
    var month=now.getMonth()+1;
    if(month<10){
    month="0"+month;
    }
    var day=now.getDate();
    if(day<10){
    day="0"+day;
    }
    var hours=now.getHours();
    if(hours<10){
    hours="0"+hours;
    }
    var minutes=now.getMinutes();
    if(minutes<10){
    minutes="0"+minutes;
    }
    var seconds=now.getSeconds();
    if(seconds<10){
    seconds="0"+seconds;
    }
    document.all.show.innerHTML=""+year+"-"+month+"-"+day+"<br /> "+hours+":"+minutes+":"+seconds+"";
    var timeID=setTimeout(showtime,1000);
    }

