//用来替换jsp中用的 <%=request.getContextPath()%>
var myCotextPath="/"+window.location.pathname.split("/")[1];

//执法船
$('#zhifa').click(function () {
    if($(this).attr('class')=="hoverrf"){
       // saXY('1');//更新坐标
        showZboat();
    }else{
        remove('point',Zboat,null);
    }
});
//执法人
$('#ren').click(function () {
    if($(this).attr('class')=="hoverrf"){
      // peopleXY();//更新坐标
        showPeople();
    }else{
        remove('point',People,null);
    }
});
//执法车
$('#che').click(function () {
    if($(this).attr('class')=="hoverrf"){
       // saXY('4');//更新坐标
        showZcar();
    }else{
        remove('point',Zcar,null);
    }
});
//摄像头
$('#shexiang').click(function () {
    if($(this).attr('class')=="hoverrf"){
       showSX();
    }else{
        remove('point',Sx,null);
    }
});
//获取所有正在执法的 人的  实时坐标数据  PEOPLE_DATA
function  peopleXY() {
    $.ajax({
        type: "GET",
        url: myCotextPath+"/gis/getNowPeopleGis",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success: function (data)
        {
            alert("测试："+JSON.stringify(data));
        },
        error:function (data) {
            alert("2"+JSON.stringify(data));
        }
    });
}
//获取所有正在执法的 车船的  实时坐标数据
function  saXY(saType) {
    //1-水政执法船 ZBOAT_DATA、2-采沙船  CBOAT_DATA 、3-运沙船 YBOAT_DATA、4-执法车 ZCAR_DATA
    var param={"saType":saType};
    $.ajax({
        type: "GET",
        url: myCotextPath+"/gis/getNowSaGis",
        dataType: "json",
        data:param,
      //  contentType: "application/json; charset=utf-8",
        success: function (data)
        {
            alert("测试："+JSON.stringify(data));
        },
        error:function (data) {
            alert("2"+JSON.stringify(data));
        }
    });
}

function showOrHide(obj) {
    $(obj).parent().next().toggle();
}

//列出所有车船（正在报警的）
function listShipAlarm() {
    $.ajax({
        type: "GET",
        url: myCotextPath+"/shipAlarm/getNowShipAlarmDatas",
        data:{"csaStatus":"1"},
        dataType: "json",
        success: function (data)
        {
            //alert(JSON.stringify(data));
            var ls_str='';
            for(var i=0;i<data.length;i++){
                // alert(data[i].saId);
                ls_str+='<div class="listcard" onclick="baojing_list(this);">';
                ls_str+='<p class="ptitle">';
                var type=data[i].shipArchives.saType;
                var img_url;
                switch(type){
                    case  '1':
                        img_url=myCotextPath+"/resources/cmd/image/shuizheng.png";
                        break;
                    case '2':
                        img_url=myCotextPath+"/resources/cmd/image/caisha.png";
                        break;
                    case '3':
                        img_url=myCotextPath+"/resources/cmd/image/yunsha.png";
                        break;
                    case '4':
                        img_url=myCotextPath+"/resources/cmd/image/gongan.png";
                        break;
                    default:
                        img_url=myCotextPath+"/resources/cmd/image/caisha.png";

                }
                ls_str+='<img src="'+img_url+'" alt="意外报警">';
                ls_str+='<span title="'+data[i].csaAlarmReason+'">'+data[i].shipArchives.saName+'</span> </p>';
                ls_str+='<p class="time">'+data[i].csaBegintime+'</p>';
                ls_str+=' <p class="button">';
                ls_str+='<button onclick="btn1_guiji(this),btn_change(this),event.cancelBubble = true" type="button" name="button" class="button_bg">轨迹回放</button>';
                ls_str+='<button onclick="btn2_showside(this),btn_change(this),event.cancelBubble = true" type="button" name="button" class="button_bg">显示边界</button>';
                ls_str+='<button onclick="btn3_showline(this),btn_change(this),event.cancelBubble = true" type="button" name="button" class="button_bg">显示路线</button>';
                ls_str+='<button onclick="btn4_close(this),event.cancelBubble = true" name="button" class="button_bg closecard">结束</button> </p>  </div>';
                ls_str+='<div class="bottomline"></div>';
            }

            $("#ship_alarm_list").html(ls_str);
        },
        error:function (data) {
            alert("2"+JSON.stringify(data));
        }
    });
}
//-----------报警列表
function baojing_list(obj){

    //点击报警列表
    if($(obj).attr('class')=="listcard"){
        alertlist(YW_DATA,myCotextPath+'/resources/cmd/image/zboat.png');
    }else{
        closeInfo(YW_DATA);
    }


    if ($(obj).attr("class")=="listcard card_bg") {
        $(".leftlist .listcard").removeClass("card_bg");

    }else{
        $(".leftlist .listcard").removeClass("card_bg");
        $(obj).addClass("card_bg");
    }
    // $(".listcard button").removeClass("button_bg_y");
    // $(".listcard button").addClass("button_bg");

}
//-----------轨迹回放
function btn_change(obj) {
    //如果当前卡片中包含被选中的按钮
    //当前(.listcard)-子代(有三个p，指定包含.buttoon的那个)-子代(四个button)-是否包含button_bg_y
    //alert($(this).children("p.button").children().hasClass("button_bg_y"));
    //检查该卡片中其他按钮有无被选中，有被选中的就继续选中。
    //alert($(this).parent().find($(".button_bg_y")).length)
    //=1的情况 只剩下一个的时候,//并且没有其他的情况
    if ($(obj).parent().find($(".button_bg_y")).length==1) {
        //并且点击的是本身的时候
        if ($(obj).attr("class")=="button_bg_y") {
            //移除背景
            $(".leftlist .listcard").removeClass("card_bg");
        }

    }
    if ($(obj).parent().find($(".button_bg_y")).length>0) {
        //设置按钮样式
        //toggleClass 单项移除不合理，切换bg才是正道
        $(obj).toggleClass("button_bg");
        $(obj).toggleClass("button_bg_y");
        //避免执行.listcard

        return false;
    }
    else{
        //说明是第一次选中，或切换了卡片

        $(".listcard button").addClass("button_bg");
        $(".listcard button").removeClass("button_bg_y");
        $(obj).removeClass("button_bg");
        $(obj).addClass("button_bg_y");
        $(".leftlist .listcard").removeClass("card_bg");
        //选中卡片 添加背景
        $(obj).parent().parent().addClass("card_bg");

        return false;
    }

    //$(this).toggleClass("button_bg_y");
    //因按钮在.listcard内，所以选中按钮同时也选中了卡片。会继续自顶层到底层逐级响应
    //继续执行.leftlist .listcard click事件，除非return false

}
function btn1_guiji(obj){
    if($(obj).attr('class')=="button_bg"){
        showRoute(polylineJson,myCotextPath+"/resources/cmd/image/boat.png");
    }else{
        remove('polyline',null,'dash');
        remove('point',myCotextPath+"/resources/cmd/image/boat.png",null);
    }
}
//-----------显示边界
function btn2_showside(obj){
    if($(obj).attr('class')=="button_bg"){
        showArea(ring);
    }else{
        remove('polygon',null,null);
    }
}
//显示路线
function btn3_showline(obj){
    if($(obj).attr('class')=="button_bg"){
        showProute(prouteJson);
    }else{
        remove('polyline',null,'solid');
    }
}
//关闭（结束）当前报警项
function btn4_close(obj){
    $(obj).parent().parent().hide();
    $(obj).parent().parent().next().hide();
    remove('polyline',null,'solid');
    remove('polyline',null,'dash');
    remove('point',myCotextPath+"/resources/cmd/image/boat.png",null);
    remove('polygon',null,null);
}
function openVideo() {
  //  window.location.href=myCotextPath+"/main/jump.monitor";
    window.open (myCotextPath+"/main/jump.monitor", "_blank", "height=260, width=400, toolbar= no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,top=100,left=300");
}