<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>立案受理</title>
    <style>
        h3,h2{
            text-align: center;
        }
        .examApprovalCaseClass table{
            margin:0 auto;
            text-align: center;
        }
        .examApprovalCaseClass input,textarea{
            border:none;
            width:100%;
            display: inline-block;
            outline: none;
        }
        .examApprovalCaseClass textarea{
            line-height: 30px;
        }
        .examApprovalCaseClass tr{
            /*width:1000px;*/
        }
        .examApprovalCaseClass td{
            padding:10px;
        }

        .table-top{
            margin:10px;
            text-align: right;
        }
        .table-top input{
            width:80px;
            margin:0 3px;
            border:none;
            border-bottom:1px solid #333;
            text-align: center;
        }
    </style>
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
                {field: 'opt', title: '操作', width: 80, align: 'center',formatter:function(value,row,index){
                    var tmpOptStr = "<div style='display:inline; float:right'>"
                        /*+"<a href='javascript:;' class='easyui-linkbutton' onclick='auditAdopt("+row.ciId+","+row.prId+")' style='width:80px'>通过</a>&nbsp;&nbsp;"
                        +"<a href='javascript:;' class='easyui-linkbutton' onclick='auditRefuse("+row.ciId+","+row.prId+")' style='width:80px'>驳回</a>&nbsp;&nbsp;"*/
                        +"<a href='javascript:;' class='easyui-linkbutton' onclick='acceptance("+row.ciId+","+row.prId+")' style='width:80px'>受理</a>&nbsp;&nbsp;"
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

        //--点击受理，打开受理页面，在该页面通过或驳回
        function acceptance(){
            $('#examApprovalCaseModuleForm').form('clear');

            $("#examApprovalCaseModule").panel({title: "&nbsp;立案受理"});//--标题
            $('#examApprovalCaseModule').window('open').window('resize',{top: '10px'});//--打开新增页面
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

        //--分配取证人员
        function submitSelectUser(){
            var rows = $('#list_user').datagrid('getSelections');

            if (rows && rows.length == 1) {
                $("#ciZhiFaPerson").textbox('setValue',rows[0].userName);
                $('#selectUserModule').window('close');
            }else{
                $.messager.alert('提示', '请选择执法负责人！！', 'warning');
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

<!--立案审批表 Examination and approval of case-->
<div id="examApprovalCaseModule" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:900px;height:800px;padding:0px;">
    <div style="width:95%;max-width:700px;padding:10px 60px;">
        <input type="hidden" id="tag"/><!--标记新增or编辑-->
        <input type="hidden" id="prId_1"/><!--巡查上报记录编号-->

        <h3>（水行政执法机关名称）</h3>
        <h2>立 案 审 批 表</h2>
        <div class="table-top">
            <!--处罚机关简称-->
            <input name="ceaAbbreviation" id="ceaAbbreviation" type="text" value="苏" form="examApprovalCaseModuleForm" readonly>水立
            [<input name="ceaYear" id="ceaYear" type="text" value="2018" form="examApprovalCaseModuleForm" readonly>]<!--年份-->
            <input name="ceaSeqno" id="ceaSeqno" type="text" value="1" form="examApprovalCaseModuleForm" readonly>号<!--序号-->
        </div>

        <form class="examApprovalCaseClass" id="examApprovalCaseModuleForm" method="post">
            <table border="1" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="2" style="width: 80px;">案件来源</td>
                    <td colspan="6"><input  name="ceaSource" id="ceaSource" type="hidden" readonly>
                        <input  name="ceaSource_display" id="ceaSource_display" type="text" readonly></td>
                </tr>
                <tr>
                    <td colspan="2">案发地点</td>
                    <td colspan="2"><input name="ceaPosition" id="ceaPosition" type="text" readonly></td>
                    <td colspan="2">案发时间</td>
                    <td colspan="2"><input name="ceaTimeofcase" id="ceaTimeofcase" type="text" readonly></td>
                </tr>
                <tr>
                    <td rowspan="5" width="30">当事人情况</td>
                    <td rowspan="2" width="30">个人</td>
                    <td width="80">姓名</td>
                    <td width="80"><input name="ceaName" id="ceaName" type="text" readonly></td>
                    <td width="50">性别</td>
                    <td width="60">
                        <input name="ceaSex" id="ceaSex" type="text" readonly></td>
                    <td width="50">电话</td>
                    <td width="80"><input name="ceaTelephone" id="ceaTelephone" type="text" readonly></td>
                </tr>
                <tr>
                    <td>住所地</td>
                    <td colspan="2"><input  name="ceaAddress" id="ceaAddress" type="text" readonly></td>
                    <td>邮编</td>
                    <td colspan="2"><input name="ceaZipcode" id="ceaZipcode" type="text" readonly></td>
                </tr>
                <tr>
                    <td rowspan="3">单位</td>
                    <td>名称</td>
                    <td colspan="5"><input name="ceaCompanyName" id="ceaCompanyName" type="text" readonly></td>
                </tr>
                <tr>
                    <td>法定代表人（负责人）</td>
                    <td><input name="ceaCompanyCharge" id="ceaCompanyCharge" type="text" readonly></td>
                    <td>职务</td>
                    <td><input name="ceaCompanyJob" id="ceaCompanyJob" type="text" readonly></td>
                    <td>电话</td>
                    <td><input name="ceaCompanyTelephone" id="ceaCompanyTelephone" type="text" readonly></td>
                </tr>
                <tr>
                    <td>住所地</td>
                    <td colspan="2"><input name="ceaCompanyAddress" id="ceaCompanyAddress" type="text" readonly></td>
                    <td>邮编</td>
                    <td colspan="2"><input name="ceaCompanyZipcode" id="ceaCompanyZipcode" type="text" readonly></td>
                </tr>
                <tr>
                    <td colspan="2">案情简介<br/>及立案依<br/>据</td>
                    <td colspan="6">
                        <textarea  name="ceaCaseSummary" id="ceaCaseSummary" rows="4" readonly></textarea><br>
                        <div style="text-align: right;margin-right: 50px;">
                            经办人：<input name="ceaCaseAgent" id="ceaCaseAgent" type="text" style="width: 200px" readonly>
                            时间：<input name="ceaCaseDatetime" id="ceaCaseDatetime" type="text" style="width: 50px;text-align: right" readonly>
                        </div>

                    </td>
                </tr>
                <tr>
                    <td colspan="2">执法机构<br/>负责人<br/>审核意见</td>
                    <td colspan="6">
                        <textarea name="ceaCompanyAuditopinion" id="ceaCompanyAuditopinion"  rows="2" ></textarea><br>
                        <div style="text-align: right;margin-right: 50px;">
                            签名：<input name="ceaCompanyAutograph" id="ceaCompanyAutograph" type="text" style="width: 200px" >
                            时间：<input class="easyui-datetimebox" name="ceaCompanyDatetime" id="ceaCompanyDatetime" style="width: 150px"
                                      data-options="required:false,validType:'maxLength[100]'">
                        </div>

                    </td>
                </tr>
                <tr>
                    <td colspan="2">执法机关<br/>负责人<br/>审批意见</td>
                    <td colspan="6">
                        <textarea name="ceaOfficeAuditopinion" id="ceaOfficeAuditopinion" rows="1" readonly></textarea><br>
                        <p style="text-align: right;margin-right: 50px;">（执法机关印章）</p>
                        <div style="text-align: right;margin-right: 50px;">
                            签名：<input name="ceaOfficeAutograph" id="ceaOfficeAutograph" type="text" style="width: 200px" readonly>
                            <input type="text" style="width: 50px;text-align: right" readonly>年<!--时间：ceaOfficeDatetime-->
                            <input type="text" style="width: 30px;text-align: right" readonly>月
                            <input type="text" style="width: 30px;text-align: right" readonly>日
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">备注</td>
                    <td colspan="6"><textarea name="remark" id="remark_1" readonly></textarea></td>
                </tr>
            </table>

            <div style="margin-top:20px">
                <input class="easyui-textbox" name="ciZhiFaPerson" id="ciZhiFaPerson" style="width:200px;"
                       data-options="label:'执法负责人:',required:true,validType:'maxLength[100]'">
                <div style="display:inline;">
                    <a href="javascript:;" class="easyui-linkbutton" onclick="selectSingleUser()" style="width:35px">选择</a>&nbsp;&nbsp;
                </div>
            </div>
        </form>

    </div>
    <div style="text-align:center;">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="auditAdopt()" style="width:80px">通过</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="auditRefuse()" style="width:80px">驳回</a>
    </div>
    <br/>
</div>

<!--选择执法负责人-->
<%@include file="../../views/handingcase/select_user.jsp"%>


<script>
    //--查询
    function submitAcceptanceSearchForm() {
        if ($('#acceptanceSearchForm').form("validate")) {//通过校验
            var search_data = $('#acceptanceSearchForm').serializeObject();
            loadTable(search_data);
        }
    }
    //--清空查询条件
    function clearAcceptanceSearchForm(){
       $('#acceptanceSearchForm').form('clear');
    }


    //--选择单个执法负责人
    function selectSingleUser(){
        //--重新查询用户
        loadUserTable();
        //--打开用户选择页面，选择取证人员
        //alert('打开用户选择页面，选择取证人员');
        $("#selectUserModule").panel({title: "&nbsp;选择执法负责人"});
        $('#selectUserModule').window('open');
    }

</script>
</body>
</html>