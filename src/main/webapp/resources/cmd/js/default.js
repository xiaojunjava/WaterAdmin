
//时间
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
    $("#show").html(""+year+"-"+month+"-"+day+"<br /> "+hours+":"+minutes+":"+seconds+"");
   // document.getElementById("show").html(""+year+"-"+month+"-"+day+"<br /> "+hours+":"+minutes+":"+seconds+"");
   // $("#show").html(""+year+"-"+month+"-"+day+"<br /> "+hours+":"+minutes+":"+seconds+"");
    var timeID=setTimeout(showtime,1000);
}

function loadcss(){

        $("#boxscroll4").niceScroll("#boxscroll4 .wrapper", {
            boxzoom: false
        });
        $("#boxscroll2").niceScroll("#boxscroll2 .wrapper", {
            boxzoom: false
        });

        menu('rightclose');

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

        // NOTE: 按钮
        $(".leftlist .listcard .button button").click(function(){
            if ($(this).parent().find($(".button_bg_y")).length==1) {
                if ($(this).attr("class")=="button_bg_y") {
                    $(".leftlist .listcard").removeClass("card_bg");
                }
            }
            if ($(this).parent().find($(".button_bg_y")).length>0) {
                $(this).toggleClass("button_bg");
                $(this).toggleClass("button_bg_y");
                return false;
            }
            else{
                $(".listcard button").addClass("button_bg");
                $(".listcard button").removeClass("button_bg_y");
                $(this).removeClass("button_bg");
                $(this).addClass("button_bg_y");
                $(".leftlist .listcard").removeClass("card_bg");
                $(this).parent().parent().addClass("card_bg");
                return false;
            }
        });

        // NOTE: 背景色

        // NOTE: 左
        $(".leftlist .listcard").click(function(){


            if ($(this).attr("class")=="listcard card_bg") {
                $(".leftlist .listcard").removeClass("card_bg");

            }else{
                $(".leftlist .listcard").removeClass("card_bg");
                $(this).addClass("card_bg");
            }
            $(".listcard button").removeClass("button_bg_y");
            $(".listcard button").addClass("button_bg");
        });

        // NOTE: 右
        $(".rightlist .listcard").click(function(){

            if ($(this).attr("class")=="listcard card_bg") {
                $(".rightlist .listcard").removeClass("card_bg");

            }else{
                $(".rightlist .listcard").removeClass("card_bg");
                $(this).addClass("card_bg");
            }
        });



        //报警列表 结束按钮
        $(".closecard").click(function(){
            $(this).parent().parent().hide();
            $(this).parent().parent().next().hide();
        });
}


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
      var z = document.getElementById("map_zoom_slider");
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
      var z = document.getElementById("map_zoom_slider");
      y.style.display = "none";
      x.style.display = "";
      z.style.right = "10px";
      break;
    default:
  }
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