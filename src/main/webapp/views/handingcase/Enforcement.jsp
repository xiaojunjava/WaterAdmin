<%--
  Description: 强制执行
  User: lvzhixue
  Date: 2017/12/28 14:46
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>强制执行</title>
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

        //--获取强制执行数据
        function loadTable(queryData) {
            //--显示的列模型
            var columns = [[
                {field: 'ck', checkbox: true},
                {field: 'ciId', title: '案件序号', width: 10, align: 'center', sortable: 'true'},
                {field: 'prId', title: '巡查上报序号', width: 10, align: 'center', sortable: 'true'},
                {field: 'ciName', title: '案件名称', width: 180, align: 'center'},
                {field: 'ciDatetime', title: '发生时间', width: 150, align: 'center'},
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
                {field: 'ciAcceptPerson', title: '受理人', width: 100, align: 'center'},
                {field: 'userId', title: '取证人编号', width: 100, align: 'center'},
                {field: 'userName', title: '取证人', width: 100, align: 'center'},
                {field: 'applyReason', title: '申请理由', width: 200, align: 'center'},
                {field: 'remark', title: '备注', width: 200, align: 'center'}

            ]];

            //--获取数据库中的数据
            var jsonData = {
                url: '<%=request.getContextPath()%>/caseinfo/getApprovalCaseList',
                title: '强制执行记录',
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
                toolbar: [{
                    text: '登记',
                    iconCls: 'icon-save',
                    handler: regedit						//handler类似事件
                }]

            };

            $('#list_approval').datagrid(jsonData);//加载数据
            $('#list_approval').datagrid('hideColumn','prId');
            $('#list_approval').datagrid('hideColumn','ciId');
            $('#list_approval').datagrid('hideColumn','ciType');
            $('#list_approval').datagrid('hideColumn','applyReason');
            $('#list_approval').datagrid('hideColumn','ciAcceptPerson');
            $('#list_approval').datagrid('hideColumn','userId');

        }

        function regedit(){
            alert('登记');
        }

        //--强制执行通过
        function approvalCaseAdopt(ciId, prId) {

            alert('强制执行通过');
            $.messager.confirm('确认', '您确定要通过该案件吗?', function (r) {
                if (r) {
                    $.ajax({
                        type: "POST",
                        url: "<%=request.getContextPath()%>/caseinfo/approvalCaseAdopt",
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

        //--强制执行驳回
        function approvalCaseRefuse(ciId, prId) {
            alert('强制执行驳回');
            $.messager.confirm('确认', '您确定要驳回该案件吗?', function (r) {
                if (r) {
                    $.ajax({
                        type: "POST",
                        url: "<%=request.getContextPath()%>/caseinfo/approvalCaseRefuse",
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

        //--打开取证记录
        function viewEvidence(ciId, prId) {
            this.ciId = ciId;
            this.prId = prId;

            //--重新查询用户
            loadRecOfEvidenceTable();
            //--打开用户选择页面，选择取证人员
            //alert('打开用户选择页面，选择取证人员');
            $("#selectRecOfEvidenceModule").panel({title: "&nbsp;取证记录"});
            $('#selectRecOfEvidenceModule').window('open');
        }

    </script>
</head>
<body id="login_bg" align="center">
<%@include file="../../views/loadingDiv.jsp"%>

<!-- 强制执行查询条件 -->
<div class="easyui-panel" title="查询" style="width:99%;padding:10px 10px 10px 10px;margin:0px 0px 1px 0px">
    <div style="margin-bottom:0px">
        <form id="approvalSearchForm" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            起止时间：<input class="easyui-datebox" name="startTime" style="width:12%;" data-options="validType:'maxLength[20]'">
            -
            <input class="easyui-datebox" name="endTime" style="width:12%;" data-options="validType:'maxLength[20]'">

            <div style="display:inline; float:right">
                <a href="javascript:;" class="easyui-linkbutton" onclick="submitApprovalSearchForm()" style="width:80px">查询</a>&nbsp;&nbsp;
                <a href="javascript:;" class="easyui-linkbutton" onclick="clearApprovalSearchForm()" style="width:80px">清空</a>&nbsp;&nbsp;
            </div>
        </form>
    </div>
</div>

<!-- 强制执行列表 -->
<div id="list_approval"></div>


<%@include file="../../views/handingcase/record_of_evidence.jsp"%>

<script>
    //查询
    function submitApprovalSearchForm() {
        if ($('#approvalSearchForm').form("validate")) {//通过校验
            var search_data = $('#approvalSearchForm').serializeObject();
            loadTable(search_data);
        }
    }

    function clearApprovalSearchForm(){
       $('#approvalSearchForm').form('clear');
    }
    
</script>
</body>
</html>