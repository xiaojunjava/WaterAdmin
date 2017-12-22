var myCotextPath="/"+window.location.pathname.split("/")[1];
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
var sa_columns = [[
    // {field:'ck',checkbox:true},
    {field:'smBeginTime',title:'轨迹开始时间',width:264,align:'center'},
    {field:'smEndTime',title:'轨迹结束时间',width:264,align:'center'},
    {field:'smTraJectory',title:'轨迹（点集合）',width:264,align:'center'},
    {field:'remark',title:'备注',width:264,align:'center'}
]];
function loadTable(){

    var IsCheckFlag=false;
    var rowIndexTo;//保存当前保存的是那条数据
    var jsonData = {
        url : myCotextPath+'/sm/getSMDatas',
        title:'&nbsp;&nbsp;车船实时位置监控记录',
        halign:'center',
        align:'center',
        method:'get',
        columns : sa_columns,
        striped:true,
        rownumbers : true,
        remoteSort : true,
        pagination : true,
        autoRowHeight:false,
        fitColumns: true,//允许表格自动缩放，以适应父容器
        // fit:true,
        pageSize : '10',
        width:'100%',
        height : '400',
        singleSelect : true,//为false时可以选择多行
        onSelect:function (rowIndex, rowData) {
            if(!IsCheckFlag){
                IsCheckFlag = true;
                rowIndexTo=rowIndex;
            }else if(rowIndexTo==rowIndex){
                IsCheckFlag = false;
                $(this).datagrid("unselectRow",rowIndex);
            }else{
                IsCheckFlag = false;
            }
        },
        onLoadSuccess : function(data) { },
        toolbar:[{ }]
    };
    $('#list_sa').datagrid(jsonData);//加载数据
}
function del() {
    var rows = $('#list_sa').datagrid('getSelections');
    if (rows&&rows.length == 1) {
        $.messager.confirm('Confirm','您确定要删除该条记录吗?',function(r){
            if (r){
                $.ajax({
                    type: "POST",
                    url: myCotextPath+"/sm/delSm",
                    data:{"saId":rows[0].saId},
                    dataType: "json",
                    success: function (data)
                    {
                        if(data.tag){
                            $.messager.alert('我的提示','您的操作已成功!');
                            loadTable();
                        }
                    },
                    error:function (data) {
                        alert("2"+JSON.stringify(data));
                    }
                });
            }
        });
    } else {
        $.messager.alert('提示', '请选择一条记录！', 'warning');
        return false;
    }
}

//查询
function submitForm1(){
    if($('#ff1').form("validate")){//通过校验
        var search_data= $('#ff1').serializeObject();
        $('#list_sa').datagrid('load',search_data);//加载数据
        //loadTable(search_data);
    }
}
function  searchAddCar() {
    //添加搜索框
    $(".datagrid-toolbar table tbody tr").append("<td width='75%'>&nbsp;</td><td width='15%'></td>");
    $(".datagrid-toolbar table").attr("align", "center");
    $(".datagrid-toolbar table tbody tr td:first-child").attr("width", "10%");
    $(".datagrid-toolbar table tbody tr td:last-child").attr("align", "right");
    $(".datagrid-toolbar table tbody tr td:last-child").css("overflow","hidden");
//            $("#search").appendTo($(".datagrid-toolbar table tbody tr td:last-child"));
    var search = $('<div id="search" ><form id="ff1"><input name="remark"  class="easyui-searchbox" prompt="请输入备注信息" searcher="submitForm1"/></form></div>');
    search.appendTo($(".datagrid-toolbar table tbody tr td:last-child"));
    $.parser.parse(search);
}
function  searchAddPer() {
    //添加搜索框
    $(".datagrid-toolbar table tbody tr").append("<td width='75%'>&nbsp;</td><td width='15%'></td>");
    $(".datagrid-toolbar table").attr("align", "center");
    $(".datagrid-toolbar table tbody tr td:first-child").attr("width", "10%");
    $(".datagrid-toolbar table tbody tr td:last-child").attr("align", "right");
    $(".datagrid-toolbar table tbody tr td:last-child").css("overflow","hidden");
//            $("#search").appendTo($(".datagrid-toolbar table tbody tr td:last-child"));
    var search = $('<div id="search" ><form id="ff1"><input name="plResult"  class="easyui-searchbox" prompt="请输入巡查结果" searcher="submitForm1"/></form></div>');
    search.appendTo($(".datagrid-toolbar table tbody tr td:last-child"));
    $.parser.parse(search);
}
//列出所有车船
function listSa() {
    $.ajax({
        type: "GET",
        url: myCotextPath+"/sa/getAllSADatas",
        dataType: "json",
        success: function (data)
        {
            //alert(data.length);
            var ls_car='';
            var ls_ship='';
            for(var i=0;i<data.length;i++){
                // alert(data[i].saId);
                var type=data[i].saType;
                if(type=="4"){
                    ls_ship+='<div class="info_name">';
                    ls_ship+='<span onclick="findCar('+data[i].saId+')"> '+data[i].saName+'</span>';
                    ls_ship+=' <i></i>';
                    ls_ship+='</div>';
                }else{
                    ls_car+='<div class="info_name">';
                    ls_car+='<span onclick="findCar('+data[i].saId+')"> '+data[i].saName+'</span>';
                    ls_car+=' <i></i>';
                    ls_car+='</div>';
                }

            }
            $("#car_list").html(ls_car);
            $("#ship_list").html(ls_ship);
        },
        error:function (data) {
            alert("2"+JSON.stringify(data));
        }
    });
}
function findCar(saId) {
    var search_data={"saId":saId};
    $('#list_sa').datagrid({url:myCotextPath+"/sm/getSMDatas",columns:sa_columns,title:"&nbsp;&nbsp;车船实时位置监控记录",queryParams:search_data});
    searchAddCar();//添加搜索框
}
function findPer(userId) {
    var search_data={"userId":userId};
    $('#list_sa').datagrid({url:myCotextPath+"/app/getSelectPLDatas",columns:per_columns,title:"&nbsp;&nbsp;巡查台账记录",queryParams:search_data});
    searchAddPer();//添加搜索框
}
//列出所以台账人员
function listPL() {
    $.ajax({
        type: "GET",
        url: myCotextPath+"/login/getUsersData",
        dataType: "json",
        data:{page:"1",rows:"1000"},
        success: function (data)
        {
            var ls_per='';
            for(var i=0;i<data.rows.length;i++){
                    // alert(data[i].saId);
                ls_per+='<div class="info_name">';
                ls_per+='<span onclick="findPer('+data.rows[i].userId+')"> '+data.rows[i].userName+'</span>';
                ls_per+=' <i></i>';
                ls_per+='</div>';
            }
            $("#person_list").html(ls_per);
        },
        error:function (data) {
            alert("2"+JSON.stringify(data));
        }
    });
}
Date.prototype.formatMe = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
};
//加载巡查台帐（人的）数据表格
var per_columns = [[
    // {field:'ck',checkbox:true},
    {field:'userName',title:'巡查人员',width:264,align:'center'},
    {field:'plLedgerName',title:'台账名称',width:264,align:'center'},
    {field:'plBeginTime',title:'开始时间',width:264,align:'center',formatter : function(value, rowData, rowIndex) {
            var unixTimestamp = new Date(value);
            return unixTimestamp.formatMe("yyyy-MM-dd hh:mm:ss");
        }
    },
    {field:'plEndTime',title:'结束时间',width:264,align:'center',formatter : function(value, rowData, rowIndex){
        var unixTimestamp = new Date(value);
        return unixTimestamp.formatMe("yyyy-MM-dd hh:mm:ss");
    }},
    {field:'plResult',title:'巡查结果',width:264,align:'center'}
]];

//巡查台账数据列表
function loadTablePL(){

    var IsCheckFlag=false;
    var rowIndexTo;//保存当前保存的是那条数据
    var jsonData = {
        url : myCotextPath+'/app/getSelectPLDatas',
        title:'&nbsp;&nbsp;巡查台账记录',
        halign:'center',
        align:'center',
        method:'get',
        columns : per_columns,
        striped:true,
        rownumbers : true,
        remoteSort : true,
        pagination : true,
        autoRowHeight:false,
        fitColumns: true,//允许表格自动缩放，以适应父容器
        // fit:true,
        pageSize : '10',
        width:'100%',
        height : '400',
        singleSelect : true,//为false时可以选择多行
        onSelect:function (rowIndex, rowData) {
            if(!IsCheckFlag){
                IsCheckFlag = true;
                rowIndexTo=rowIndex;
            }else if(rowIndexTo==rowIndex){
                IsCheckFlag = false;
                $(this).datagrid("unselectRow",rowIndex);
            }else{
                IsCheckFlag = false;
            }
        },
        onClickRow:function(rowIndex,rowData){
            var people_routeJson= rowData["plTarjectory"];
            if(people_routeJson!=null&&people_routeJson.length>0){
                var obj = eval('(' + people_routeJson + ')');
                displayLine(obj);
            }else{
                alert("未获取到轨迹坐标");
            }
        },
        onLoadSuccess : function(data) { },
        toolbar:[{ }]
    };
    $('#list_sa').datagrid(jsonData);//加载数据
}

function displayLine(people_routeJson) {
    allMap.graphics.clear();
    showRoute(people_routeJson,myCotextPath+"/resources/cmd/image/personRoute.gif");
}