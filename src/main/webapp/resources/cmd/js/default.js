$(document).ready(function() {

    $("#boxscroll4").niceScroll("#boxscroll4 .wrapper", {
        boxzoom: false
    });
    $("#boxscroll2").niceScroll("#boxscroll2 .wrapper", {
        boxzoom: false
    });

     // menu('rightclose');//（默认关闭车船列表--收缩）貌似之前商浩加的，造成首页右下角链接点了失效

    // NOTE: 显示船
    $(".tags a").toggle(function() {
            $(this).addClass("hoveradd");
            $(this).removeClass("hoverrf");
            var id = $(this).attr('id');
            $("."+id).show();
        },
        function() {
            $(this).addClass("hoverrf");
            $(this).removeClass("hoveradd");
            var id = $(this).attr('id');
            $("."+id).hide();
        }
    );

    //右侧更多
    $(".more_open").hide();
    $(".cardtitle:eq(0)").removeClass("cardtitlebg");
    $('.listcard .more').on('click',function(){
        $(this).parent().next().toggle();
    });
    // $(".more").click(function(){
    //   $(this).parent().next().toggle();
    // });

    // NOTE: 清空
    $(".clear").click(function() {
        $(".tags a").addClass("hoverrf");
        $(".tags a").removeClass("hoveradd");
        $(".listcard button").addClass("button_bg");
        $(".listcard button").removeClass("button_bg_y");
        $(".leftlist .listcard").removeClass("card_bg");
        $(".rightlist .listcard").removeClass("card_bg");
        $(".more_open").hide();
    });


});

function menu(x) {
    switch (x) {
        case "leftopen":
            var x = document.getElementById("leftlisticon");
            var y = document.getElementById("leftlist");
            x.style.display = "none";
            y.style.display = "";
            break;
        case "rightopen":
            var x = document.getElementById("rightlisticon");
            var y = document.getElementById("rightlist");
            var z = document.getElementById("zoom");
            x.style.display = "none";
            y.style.display = "";
            z.style.right = "373px";
            break;
        case "leftclose":
            var x = document.getElementById("leftlisticon");
            var y = document.getElementById("leftlist");
            y.style.display = "none";
            x.style.display = "";
            break;
        case "rightclose":
            var x = document.getElementById("rightlisticon");
            var y = document.getElementById("rightlist");
            var z = document.getElementById("zoom");
            y.style.display = "none";
            x.style.display = "";
            z.style.right = "10px";
            break;
        default:
    }
}

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


function hasClasss(elem, cls) {
    cls = cls || '';
    if (cls.replace(/\s/g, '').length == 0) return false; //当cls没有参数时，返回false
    return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
}

function addClasss(ele, cls) {
    if (!hasClass(elem, cls)) {
        ele.className = ele.className == '' ? cls : ele.className + ' ' + cls;
    }
}

function removeClasss(ele, cls) {
    if (hasClass(elem, cls)) {
        var newClass = ' ' + elem.className.replace(/[\t\r\n]/g, '') + ' ';
        while (newClass.indexOf(' ' + cls + ' ') >= 0) {
            newClass = newClass.replace(' ' + cls + ' ', ' ');
        }
        elem.className = newClass.replace(/^\s+|\s+$/g, '');
    }
}