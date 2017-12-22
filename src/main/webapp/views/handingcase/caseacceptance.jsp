<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>案件受理</title>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/icon.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/color.css"/>

    <script src="<%=request.getContextPath()%>/resources/jquery-3.2.1.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
    <script src="<%=request.getContextPath()%>/resources/common.js"></script>
    <script type="text/javascript">


        $(function () {
            //--加载表格数据
            loadTable();
        });

        //--获取立案受理数据
        function loadTable(queryData) {
            //--显示的列模型
            var columns = [[
                {field: 'ck', checkbox: true},
                {field: 'ciId', title: '案件序号', width: 10, align: 'center', sortable: 'true'},
                {field: 'prId', title: '巡查上报序号', width: 10, align: 'center', sortable: 'true'},
                {field: 'ciName', title: '案件名称', width: 200, align: 'center'},
                {field: 'ciDatetime', title: '发生时间', width: 180, align: 'center'},
                {field: 'ciType', title: '案件类型', width: 100, align: 'center'},
                {field: 'ciStatus', title: '案件状态', width: 80, align: 'center', formatter: function(value,row,index){
                    if (value == '1'){
                        return '立案申请';
                    }else if (value == '2'){
                        return '案件处理中';
                    }else if (value == '3'){
                        return '案件结案';
                    }else if (value == '4'){
                        return '无效案件';
                    }
                }},
                {field: 'ciContent', title: '案件内容', width: 200, align: 'center'},
                {field: 'ciPlace', title: '案件地点', width: 80, align: 'center'},
                {field: 'ciAcceptPerson', title: '受理人', width: 200, align: 'center'},
                {field: 'applyReason', title: '申请理由', width: 200, align: 'center'},
                {field: 'remark', title: '备注', width: 200, align: 'center'},
                {field: 'opt', title: '操作', width: 120, align: 'center',formatter:function(value,row,index){
                    var tmpOptStr = "<div style='display:inline; float:right'>"
                        +"<a href='javascript:;' class='easyui-linkbutton' onclick='auditAdopt("+row.ciId+","+row.prId+")' style='width:80px'>通过</a>&nbsp;&nbsp;"
                        +"<a href='javascript:;' class='easyui-linkbutton' onclick='auditRefuse("+row.ciId+","+row.prId+")' style='width:80px'>驳回</a>&nbsp;&nbsp;"
                        +"</div>";

                    return tmpOptStr;
                }}
            ]];

            //--获取数据库中的数据
            var jsonData = {
                url: '<%=request.getContextPath()%>/caseinfo/getUnDisposeCaseList',
                title: '待处理案件',
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
                width: '99%',
                height: '400',
                singleSelect: false,//为true时只能选择单行
                queryParams: queryData,
                // onDblClickRow : otherMethod.onDblClickRow,
                onLoadSuccess: function (data) {
                    // alert(JSON.stringify(data));
                },
                singleSelect: true,//为false时可以选择多行
                collapsible: true,
                toolbar: []

            };

            $('#list_acceptance').datagrid(jsonData);//加载数据
            $('#list_acceptance').datagrid('hideColumn','prId');
            $('#list_acceptance').datagrid('hideColumn','ciId');
            $('#list_acceptance').datagrid('hideColumn','ciType');
        }

        //--立案受理通过
        function auditAdopt(ciId,prId){
            $.messager.confirm('确认', '您确定要受理通过吗?', function (r) {
                if (r) {
                    $.ajax({
                        type: "POST",
                        url: "<%=request.getContextPath()%>/caseinfo/auditAdopt",
                        data: {
                            "ciId": ciId,
                            "prId": prId
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data.tag) {
                                $.messager.alert('我的提示', '您的通过操作已成功!');
                                $('#acceptanceModule').window('close');
                                loadTable();
                            }
                        },
                        error: function (data) {
                            $.messager.alert('错误', '您的通过操作失败，如果疑问，请联系技术支持人员！');
                        }
                    });
                }
            });
        }

        //--立案受理驳回
        function auditRefuse(ciId,prId){
            $.messager.confirm('确认', '您确定要驳回该立案申请吗?驳回后，如需立案，请再次申请！', function (r) {
                if (r) {
                    $.ajax({
                        type: "POST",
                        url: "<%=request.getContextPath()%>/caseinfo/auditRefuse",
                        data: {
                            "ciId": ciId,
                            "prId": prId
                        },
                        dataType: "json",
                        success: function (data) {
                            if (data.tag) {
                                $.messager.alert('我的提示', '您的驳回操作已成功!');
                                $('#acceptanceModule').window('close');
                                loadTable();
                            }
                        },
                        error: function (data) {
                            $.messager.alert('提示', '您的驳回操作失败，如果疑问，请联系技术支持人员！');
                        }
                    });
                }
            });
        }
    </script>
</head>
<body id="login_bg" align="center">
<%@include file="../../views/loadingDiv.jsp"%>

<!-- 立案申请查询条件 -->
<div class="easyui-panel" title="查询" style="width:99%;padding:10px 10px 10px 10px;margin:0px 0px 1px 0px">
    <div style="margin-bottom:0px">
        <form id="acceptanceSearchForm" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            起止时间：<input class="easyui-datebox" name="startTime" style="width:12%;" data-options="validType:'maxLength[20]'">
            -
            <input class="easyui-datebox" name="endTime" style="width:12%;" data-options="validType:'maxLength[20]'">

            <div style="display:inline; float:right">
                <a href="javascript:;" class="easyui-linkbutton" onclick="submitAcceptanceSearchForm()" style="width:80px">查询</a>&nbsp;&nbsp;
                <a href="javascript:;" class="easyui-linkbutton" onclick="clearAcceptanceSearchForm()" style="width:80px">清空</a>&nbsp;&nbsp;
            </div>
        </form>
    </div>
</div>

<!-- 立案申请列表 -->
<div id="list_acceptance"></div>

<script>
    //查询
    function submitAcceptanceSearchForm() {
        if ($('#acceptanceSearchForm').form("validate")) {//通过校验
            var search_data = $('#acceptanceSearchForm').serializeObject();
            loadTable(search_data);
        }
    }

    function clearAcceptanceSearchForm(){
       $('#acceptanceSearchForm').form('clear');
    }
    
</script>
</body>
</html>