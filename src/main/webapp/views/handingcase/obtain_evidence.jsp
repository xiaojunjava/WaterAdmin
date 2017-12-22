<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>调查取证</title>
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
        var ciId;
        var prId;

        $(function () {
            //--加载表格数据
            loadTable();
        });

        //--获取调查取证数据
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
                {field: 'remark', title: '备注', width: 200, align: 'center'},
                {field: 'opt', title: '操作', width: 180, align: 'center',formatter:function(value,row,index){
                    var tmpOptStr = "<div style='display:inline; float:right'>"
                        +"<a href='javascript:;' class='easyui-linkbutton' onclick='specifyUser("+row.ciId+","+row.prId+")' style='width:80px'>分配取证人</a>&nbsp;&nbsp;"
                        +"<a href='javascript:;' class='easyui-linkbutton' onclick='viewEvidence("+row.ciId+","+row.prId+")' style='width:80px'>取证记录</a>&nbsp;&nbsp;"
                        +"</div>";

                    return tmpOptStr;
                }}
            ]];

            //--获取数据库中的数据
            var jsonData = {
                url: '<%=request.getContextPath()%>/caseinfo/getEvidenceCaseList',
                title: '待取证案件',
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

            $('#list_evidence').datagrid(jsonData);//加载数据
            $('#list_evidence').datagrid('hideColumn','prId');
            $('#list_evidence').datagrid('hideColumn','ciId');
            $('#list_evidence').datagrid('hideColumn','ciType');
            $('#list_evidence').datagrid('hideColumn','applyReason');
            $('#list_evidence').datagrid('hideColumn','ciAcceptPerson');
            $('#list_evidence').datagrid('hideColumn','userId');

        }

        //--打开分配取证人页面
        function specifyUser(ciId, prId) {
            this.ciId = ciId;
            this.prId = prId;

            //--重新查询用户
            loadUserTable();
            //--打开用户选择页面，选择取证人员
            //alert('打开用户选择页面，选择取证人员');
            $("#selectUserModule").panel({title: "&nbsp;分配取证用户"});
            $('#selectUserModule').window('open');
        }

        //--查看记录
        function viewEvidence(ciId, prId) {
            alert('查勘取证记录');
            return;

        }

        //--分配取证人员
        function submitSelectUser(){
            var rows = $('#list_user').datagrid('getSelections');

            if (rows && rows.length == 1) {

                $.ajax({
                    type: "POST",
                    url: "<%=request.getContextPath()%>/caseinfo/specifyUser",
                    data: {
                        "ciId": this.ciId,
                        "prId": this.prId,
                        "userId": rows[0].userId,
                        "userName": rows[0].userName
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.tag) {
                            $.messager.alert('我的提示', '您的操作已成功!');
                            $('#selectUserModule').window('close');
                            loadTable();
                        }
                    },
                    error: function (data) {
                        $.messager.alert('错误', '您的操作失败，如果疑问，请联系技术支持人员！');
                    }
                });
            }else{
                $.messager.alert('提示', '请选择取证人！！', 'warning');
                return false;
            }
        }

        //--取消分配取证人员
        function cancelSelectUser(){
            $('#selectUserModule').window('close');
        }
    </script>
</head>
<body id="login_bg" align="center">
<%@include file="../../views/loadingDiv.jsp"%>

<!-- 立案申请查询条件 -->
<div class="easyui-panel" title="查询" style="width:99%;padding:10px 10px 10px 10px;margin:0px 0px 1px 0px">
    <div style="margin-bottom:0px">
        <form id="evidenceSearchForm" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            起止时间：<input class="easyui-datebox" name="startTime" style="width:12%;" data-options="validType:'maxLength[20]'">
            -
            <input class="easyui-datebox" name="endTime" style="width:12%;" data-options="validType:'maxLength[20]'">

            <div style="display:inline; float:right">
                <a href="javascript:;" class="easyui-linkbutton" onclick="submitEvidenceSearchForm()" style="width:80px">查询</a>&nbsp;&nbsp;
                <a href="javascript:;" class="easyui-linkbutton" onclick="clearEvidenceSearchForm()" style="width:80px">清空</a>&nbsp;&nbsp;
            </div>
        </form>
    </div>
</div>

<!-- 立案申请列表 -->
<div id="list_evidence"></div>


<%@include file="../../views/handingcase/select_user.jsp"%>

<script>
    //查询
    function submitEvidenceSearchForm() {
        if ($('#evidenceSearchForm').form("validate")) {//通过校验
            var search_data = $('#evidenceSearchForm').serializeObject();
            loadTable(search_data);
        }
    }

    function clearEvidenceSearchForm(){
       $('#evidenceSearchForm').form('clear');
    }
    
</script>
</body>
</html>