//菜单
$(document).ready(function() {
        $(".menu1").click(function(){
            $(".menu2").hide();
            $(this).next().show();
            $(".menu1").removeClass("menuhover");
            $(this).addClass("menuhover");
            $(".menu2 a").css("color","#666");
        });

        $(".menu2 a").click(function() {
            $(".menu2 a").css("color","#666");
            $(this).css("color","#09f");
        })

        $(".menu2").hide();
        //默认展开第1个大菜单
        $(".menu1").first().click();

        //添加默认选择首个子菜单
        $(".menu3 a").first().click();
        $(".menu3:eq(0)").attr("class","menu3 menu3hover");

        showtime();

    $(".menu3").click(function(){
        $(".menu3").removeClass("menu3hover");
        $(this).addClass("menu3hover");
    });
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