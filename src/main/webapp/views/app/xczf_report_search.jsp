<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>信息查询</title>
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

        //--获取巡查上报数据，针对巡查上报记录，可以申请立案
        function loadTable(queryData) {
            //--显示的列模型
            var columns = [[
                /*{field: 'ck', checkbox: true},*/
                {field: 'prId', title: '上报记录序号', width: 200, align: 'center', sortable: 'true'},
                {field: 'plId', title: '巡查台账序号', width: 264, align: 'center', sortable: 'true'},
                {field: 'prSiteDescription', title: '现场描述', width: 200, align: 'center'},
                {field: 'prPosition', title: '发生位置', width: 200, align: 'center'},
                {field: 'prReportTime', title: '上报时间', width: 100, align: 'center'},
                {field: 'longitude', title: '经度坐标', width: 80, align: 'center'},
                {field: 'latitude', title: '纬度坐标', width: 80, align: 'center'},
                {field: 'status', title: '案件状态', width: 80, align: 'center',formatter:function(value,row,index){
                    if (value == '0'){
                        return '巡查上报';
                    }else if (value == '1'){
                        return ' <font color="blue">立案申请</font>';
                    }else if (value == '2'){
                        return '<font color="green">立案通过</font>';
                    }else if (value == '3'){
                        return '<font color="red">立案驳回</font>';
                    }else if (value == '4'){
                        return '<font color="red">案件审核驳回</font>';
                    }
                }},
                {field: 'source', title: '案件来源', width: 80, align: 'center',formatter:function(value,row,index){
                    if (value == '0'){
                        return '部门移送';
                    }else if (value == '1'){
                        return ' <font color="blue">上级交办</font>';
                    }else if (value == '2'){
                        return '<font color="green">群众举报</font>';
                    }else if (value == '3'){
                        return '<font color="red">简易上报</font>';
                    }else if (value == '4'){
                        return '<font color="red">巡查上报</font>';
                    }
                }},
                {field: 'remark', title: '备注', width: 150, align: 'center'}
            ]];

            //--获取数据库中的数据
            var jsonData = {
                url: '<%=request.getContextPath()%>/app/getPatrolReportDatasForWeb',
                title: '巡查上报记录',
                halign: 'center',
                align: 'center',
                method: 'get',
                columns: columns,
                rownumbers: true,
                nowrap: false,
                remoteSort: true,
                pagination: true,
                autoRowHeight: false,
                fitColumns: true,//允许表格自动缩放，以适应父容器
                striped:true,//--隔行显示不同背景色
                pageSize: '10',
                width: '98%',
                height: '400',
                singleSelect: false,//为true时只能选择单行
                queryParams: queryData,
                // onDblClickRow : otherMethod.onDblClickRow,
                onLoadSuccess: function (data) {
                    // alert(JSON.stringify(data));
                },
                singleSelect: true,//为false时可以选择多行
                collapsible: true,
                toolbar: [{ }]
            };

            $('#list_filing').datagrid(jsonData);//加载数据
            $('#list_filing').datagrid('hideColumn','prId');
            $('#list_filing').datagrid('hideColumn','plId');
            $('#list_filing').datagrid('hideColumn','longitude');
            $('#list_filing').datagrid('hideColumn','latitude');
        }
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