<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>巡查查询</title>
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/icon.css"/>
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/color.css"/>

    <script src="<%=request.getContextPath()%>/resources/jquery-3.2.1.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
    <script src="<%=request.getContextPath()%>/resources/common.js"></script>
    <script type="text/javascript">

        $(function () {
            //--加载表格数据
            loadTable();
        });
        var myCotextPath="/"+window.location.pathname.split("/")[1];
        //--获取巡查上报数据，针对巡查上报记录，可以申请立案
        function loadTable(queryData) {
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
            var IsCheckFlag=false;
            var rowIndexTo;//保存当前保存的是那条数据
            //--获取数据库中的数据
            var jsonData = {
                url : myCotextPath+'/app/getSelectPLDatas',
                title:'&nbsp;&nbsp;巡查台账记录',
                halign:'center',
                align:'center',
                method:'get',
                columns : per_columns,
                queryParams: queryData,
                striped:true,
                rownumbers : true,
                remoteSort : true,
                pagination : true,
                autoRowHeight:false,
                fitColumns: true,//允许表格自动缩放，以适应父容器
                // fit:true,
                pageSize : '10',
                width:'98%',
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

            $('#list_filing').datagrid(jsonData);//加载数据
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
    </script>
</head>
<body id="login_bg" align="center">

<%@include file="../../views/loadingDiv.jsp"%>

<!-- 立案申请查询条件 -->
<div class="easyui-panel" title="查询" style="width:98%;padding:10px 10px 10px 10px;margin:0px 0px 5px 0px">
    <div style="margin-bottom:0px">
        <form id="filingSearchForm" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            起止时间：<input class="easyui-datebox" name="startTime" style="width:12%;" data-options="validType:'maxLength[20]'">
            -
            <input class="easyui-datebox" name="endTime" style="width:12%;" data-options="validType:'maxLength[20]'">

            <div style="display:inline; float:right">
                <a href="javascript:;" class="easyui-linkbutton" onclick="submitFilingSearchForm()" style="width:80px">查询</a>&nbsp;&nbsp;
                <a href="javascript:;" class="easyui-linkbutton" onclick="clearFilingSearchForm()" style="width:80px">清空</a>&nbsp;&nbsp;
            </div>
        </form>
    </div>
</div>

<!-- 立案申请列表 -->
<div id="list_filing"></div>


<script>
    //立案申请查询事件
    function submitFilingSearchForm() {
        if ($('#filingSearchForm').form("validate")) {//通过校验
            var search_data = $('#filingSearchForm').serializeObject();
            loadTable(search_data);
        }
    }

    //--清空立案申请查询条件清空事件
    function clearFilingSearchForm(){
       $('#filingSearchForm').form('clear');
    }
</script>
</body>
</html>