//用来替换jsp中用的 <%=request.getContextPath()%>
var myCotextPath="/"+window.location.pathname.split("/")[1];

//点击报警列表
$(".leftlist").find(".listcard").click(function () {
    if($(this).attr('class')=="listcard"){
        alertlist();
    }else{
        closeInfo();
    }
});
//运砂船
$('#yunsha').click(function () {
    if($(this).attr('class')=="hoverrf"){
        showYboat();
    }else{
        remove('point',Yboat,null);
    }
});
//采砂船
$('#caisha').click(function () {
    if($(this).attr('class')=="hoverrf"){
        showCboat()
    }else{
        remove('point',Cboat,null);
    }
});
//执法船
$('#zhifa').click(function () {
    if($(this).attr('class')=="hoverrf"){
        showZboat();
    }else{
        remove('point',Zboat,null);
    }
});
//执法人
$('#ren').click(function () {
    if($(this).attr('class')=="hoverrf"){
        showPeople();
    }else{
        remove('point',People,null);
    }
});
//执法车
$('#che').click(function () {
    if($(this).attr('class')=="hoverrf"){
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


//列出所有车船
function listSa() {
    $.ajax({
        type: "GET",
        url: myCotextPath+"/sa/getAllSADatas",
        dataType: "json",
        success: function (data)
        {
            //alert(data.length);
            var ls_str='';
            for(var i=0;i<data.length;i++){
                // alert(data[i].saId);
                ls_str+='<div class="listcard">';
                ls_str+='<div class="cardtitle cardtitlebg">';
                var type=data[i].saType;
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
                ls_str+='<div class="carimg"><img src="'+img_url+'" alt="" /></div>';
                ls_str+='<div class="carname">'+data[i].saName+'</div>';
                ls_str+='<div class="message" title="发送短信" onclick="sendMsg('+data[i].saId+',\''+data[i].saName+'\')"></div>';
                ls_str+='<div class="more" title="查看详情" onclick="showOrHide(this);"></div>';
                ls_str+='</div>';
                ls_str+='<div class="more_open">';
                ls_str+='<div class="left_info">';
                ls_str+='<p>车船号码：'+data[i].saNum+'</p>';
                ls_str+='<p>颜色：'+data[i].saColor+'</p>';
                ls_str+='</div>';
                ls_str+='<div class="right_info">';
                ls_str+='<p>底盘号码：'+data[i].saChassisNum+'</p>';
                ls_str+='<p>发动机号：'+data[i].saMotorNum+'</p>';
                ls_str+=' </div> </div> </div>';
                ls_str+=' <div class="bottomline"></div>';

            }

            $("#sa_list").html(ls_str);
            $(".more_open").hide();
        },
        error:function (data) {
            alert("2"+JSON.stringify(data));
        }
    });
}
function sendMsg(saId,saName) {
    $('#f1').form('clear');
    $("#saId").val(saId);
    $("#mywin").panel({title:"&nbsp;&nbsp;信息发送------>"+saName });
    $('#mywin').window('open');
}
function clearF1(){
    $('#f1').form('clear');
}
//发送消息（保存）
function submitF1() {
    if($('#f1').form("validate")) {//通过校验
        var form_data = $('#f1').serializeObject();
        var actionUrl=myCotextPath+"/ii/addIi";
        $.ajax({
            type: "POST",
            url:actionUrl,
            data:form_data,
            dataType: "json",
            success: function (data)
            {
                if(data.tag){
                    $.messager.alert('操作提示',data.message);
                    $('#mywin').window('close');
                }
            },
            error:function (data) {  alert("2"+JSON.stringify(data));   }
        });
    }
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
                ls_str+='<div class="listcard">';
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
                ls_str+='<button onclick="btn1_guiji(this)" type="button" name="button" class="button_bg">轨迹回放</button>';
                ls_str+='<button onclick="btn2_showside(this)" type="button" name="button" class="button_bg">显示边界</button>';
                ls_str+='<button onclick="btn3_showline(this)" type="button" name="button" class="button_bg">显示路线</button>';
                ls_str+='<button onclick="btn4_close()" name="button" class="button_bg closecard">结束</button> </p>  </div>';
                ls_str+='<div class="bottomline"></div>';
            }

            $("#ship_alarm_list").html(ls_str);
        },
        error:function (data) {
            alert("2"+JSON.stringify(data));
        }
    });
}
//-----------轨迹回放
function btn1_guiji(obj){
    if($(obj).attr('class')=="button_bg"){
        showRoute(polylineJson,myCotextPath+"/resources/cmd/image/car.png");
    }else{
        remove('polyline',null,'dash');
        remove('point',myCotextPath+"/resources/cmd/image/car.png",null);
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
function btn4_close(){
    remove('polyline',null,'solid');
    remove('polyline',null,'dash');
    remove('point',myCotextPath+"/resources/cmd/image/car.png",null);
    remove('polygon',null,null);
}