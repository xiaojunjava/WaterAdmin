var myCotextPath="/"+window.location.pathname.split("/")[1];
//菜单
$(document).ready(function() {
        showtime();
        listRP();
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
//列出规划的路线
function listRP() {
    $.ajax({
        type: "GET",
        url: myCotextPath+"/rp/getRPDatas",
        dataType: "json",
        data:{page:"1",rows:"1000"},
        success: function (data)
        {
            var ls_per='';
            for(var i=0;i<data.length;i++){
                // alert(data[i].saId);
                var seq="0"+(i+1);
                   if(i+1>9){//不要前面的0
                       seq=""+(i+1);
                   }
                ls_per+='<div class="xianlu_li">';
                ls_per+='<div class="xianlu_no">'+seq+'</div>';
                ls_per+='<div class="xianlu_name">'+data[i].rpLineName+'</div>';
                ls_per+='<div class="xianlu_del" onclick="hideMe(this)"><div></div></div></div>';
            }
            $("#listRP").html(ls_per);
        },
        error:function (data) {
            alert("2"+JSON.stringify(data));
        }
    });
}
function hideMe(obj) {
        $(obj).parent().hide();
}
