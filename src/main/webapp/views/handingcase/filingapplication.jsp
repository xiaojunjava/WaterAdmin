<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>立案申请</title>
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

        //--获取巡查上报数据，针对巡查上报记录，可以申请立案
        function loadTable(queryData) {
            //--显示的列模型
            var columns = [[
                {field: 'ck', checkbox: true},
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
                {field: 'remark', title: '备注', width: 150, align: 'center'},
                {field: 'opt', title: '操作', width: 50, align: 'center',formatter:function(value,row,index){
                    var tmpOptStr = "<div style='display:inline; float:right'>";
                    if (row.status=='0'){
                        tmpOptStr = tmpOptStr +"<a href='javascript:;' class='easyui-linkbutton' onclick='addFiling("+row.prId+"),event.cancelBubble= true'  style='width:80px'>立案</a>&nbsp;&nbsp;";
                    }
                    tmpOptStr = tmpOptStr +"</div>";
                    return tmpOptStr;
                }}
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
                    text: '新增',
                    iconCls: 'icon-save',
                    handler: add						//handler类似事件
                }, '-', /*{
                    text: '编辑',
                    iconCls: 'icon-add',
                    handler: edit
                }, '-', */{
                    text: '删除',
                    iconCls: 'icon-remove',
                    handler: del
                }]
            };

            $('#list_filing').datagrid(jsonData);//加载数据
            $('#list_filing').datagrid('hideColumn','prId');
            $('#list_filing').datagrid('hideColumn','plId');
            $('#list_filing').datagrid('hideColumn','longitude');
            $('#list_filing').datagrid('hideColumn','latitude');
        }

        //--点击新增按钮动作
        function add() {
            $('#newCaseModuleForm').form('clear');//清除表单数据
            $("#tag").val("add");//操作标识
            $("#newCaseModule").panel({title: "&nbsp;新增案件来源"});//--标题
            $('#newCaseModule').window('open');//--打开新增页面
        }
        /*
        //--点击编辑按钮动作
        function edit() {
            var rows = $('#list_filing').datagrid('getSelections');

            if (rows && rows.length == 1) {
                $("#tag").val("edit");//操作标识
                $('#filingModuleForm').form('clear');

                //--给字段赋值
                $("#filingId").val(rows[0].filingId);//--主键，唯一

                //--初始化数据
                $("#filingName").textbox('setValue',rows[0].filingName);
                $("#remark").textbox('setValue',rows[0].remark);


                //alert(rows[0].filingList[0].filingId);
            } else {
                $.messager.alert('提示', '请选择一条记录！', 'warning');
                return false;
            }

            //先选择一条记录，再打开窗口
            $("#filingModule").panel({title: "&nbsp;编辑角色"});
            $('#filingModule').window('open');
        }*/

        //--点击立案申请，打开申请页面
        function addFiling(prId){

            $('#examApprovalCaseModuleForm').form('clear');//清除表单数据

            //--初始化数据
            var rows = $('#list_filing').datagrid('getSelections');
            if (rows && rows.length == 1) {
                if (rows[0].prId!=prId){//--判断当前选中的行是不是要操作的行，不是的话--退出
                    return;
                }

                $("#prId_1").val(rows[0].prId);//--巡查上报记录编号
                $("#ceaAbbreviation").val('苏');//--处罚单位简称
                $("#ceaYear").val('2018');//--年份
                $("#ceaSeqno").val('2');//--序号

                //--案件来源
                $("#ceaSource").val(rows[0].source);
                if (rows[0].source == '0'){
                    $("#ceaSource_display").val('部门移送');
                }else if (rows[0].source == '1'){
                    $("#ceaSource_display").val('上级交办');
                }else if (rows[0].source == '2'){
                    $("#ceaSource_display").val('群众举报');
                }else if (rows[0].source == '3'){
                    $("#ceaSource_display").val('简易上报');
                }else if (rows[0].source == '4'){
                    $("#ceaSource_display").val('巡查上报');
                }

                //--案发地点
                $("#ceaPosition").val(rows[0].prPosition);
                //--案发时间
                $("#ceaTimeofcase").val(rows[0].prReportTime);

                //--案情简介及立案依据
                $("#ceaCaseSummary").val(rows[0].prSiteDescription);


            }else{
                $.messager.alert('提示', '请选择要立案的记录!');
                return;
            }

            /*
                $("#prSiteDescription").textbox('setValue',prSiteDescription);
            */

            $("#examApprovalCaseModule").panel({title: "&nbsp;立案审批表"});//--标题
            $('#examApprovalCaseModule').window('open').window('resize',{top: '10px'});;//--打开新增页面
        }

        //--点击删除按钮动作
        function del() {
            var rows = $('#list_filing').datagrid('getSelections');
            if (rows && rows.length == 1) {
                $.messager.confirm('警告', '您确定要删除该记录吗?', function (r) {
                    if (r) {
                        $.ajax({
                            type: "POST",
                            url: "<%=request.getContextPath()%>/app/delPatrolReport",
                            data: {"prId": rows[0].prId},
                            dataType: "json",
                            success: function (data) {
                                if (data.tag) {
                                    $.messager.alert('我的提示', '您的操作已成功!');
                                    $('#filingModule').window('close');
                                    loadTable();
                                }
                            },
                            error: function (data) {
                                $.messager.alert('提示', '对不起，操作失败！请您联系技术人员！');
                            }
                        });
                    }
                });
            } else {
                $.messager.alert('提示', '请选择一条记录！', 'warning');
                return false;
            }
        }
    </script>
</head>
<body id="login_bg" align="center">

<%@include file="../../views/loadingDiv.jsp"%>

<!-- 立案申请查询条件 -->
<div class="easyui-panel" title="查询" style="width:99%;padding:10px 10px 10px 10px;margin:0px 0px 1px 0px">
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
            <input name="ceaAbbreviation" id="ceaAbbreviation" type="text" value="苏" form="examApprovalCaseModuleForm">水立
            [<input name="ceaYear" id="ceaYear" type="text" value="2018" form="examApprovalCaseModuleForm">]<!--年份-->
            <input name="ceaSeqno" id="ceaSeqno" type="text" value="1" form="examApprovalCaseModuleForm">号<!--序号-->
        </div>

        <form class="examApprovalCaseClass" id="examApprovalCaseModuleForm" method="post">
            <table border="1" cellspacing="0" cellpadding="0">
                <tr>
                    <td colspan="2" style="width: 80px;">案件来源</td>
                    <td colspan="6"><input  name="ceaSource" id="ceaSource" type="hidden">
                        <input  name="ceaSource_display" id="ceaSource_display" type="text"></td>
                </tr>
                <tr>
                    <td colspan="2">案发地点</td>
                    <td colspan="2"><input name="ceaPosition" id="ceaPosition" type="text"></td>
                    <td colspan="2">案发时间</td>
                    <td colspan="2"><input name="ceaTimeofcase" id="ceaTimeofcase" type="text"></td>
                </tr>
                <tr>
                    <td rowspan="5" width="30">当事人情况</td>
                    <td rowspan="2" width="30">个人</td>
                    <td width="80">姓名</td>
                    <td width="80"><input name="ceaName" id="ceaName" type="text"></td>
                    <td width="50">性别</td>
                    <td width="60"><select class="easyui-combobox" name="ceaSex" id="ceaSex" style="width:100%;" data-options="required:false">
                        <option value="0">女</option>
                        <option value="1">男</option>
                    </select></td>
                    <td width="50">电话</td>
                    <td width="80"><input name="ceaTelephone" id="ceaTelephone" type="text"></td>
                </tr>
                <tr>
                    <td>住所地</td>
                    <td colspan="2"><input  name="ceaAddress" id="ceaAddress" type="text"></td>
                    <td>邮编</td>
                    <td colspan="2"><input name="ceaZipcode" id="ceaZipcode" type="text"></td>
                </tr>
                <tr>
                    <td rowspan="3">单位</td>
                    <td>名称</td>
                    <td colspan="5"><input name="ceaCompanyName" id="ceaCompanyName" type="text"></td>
                </tr>
                <tr>
                    <td>法定代表人（负责人）</td>
                    <td><input name="ceaCompanyCharge" id="ceaCompanyCharge" type="text"></td>
                    <td>职务</td>
                    <td><input name="ceaCompanyJob" id="ceaCompanyJob" type="text"></td>
                    <td>电话</td>
                    <td><input name="ceaCompanyTelephone" id="ceaCompanyTelephone" type="text"></td>
                </tr>
                <tr>
                    <td>住所地</td>
                    <td colspan="2"><input name="ceaCompanyAddress" id="ceaCompanyAddress" type="text"></td>
                    <td>邮编</td>
                    <td colspan="2"><input name="ceaCompanyZipcode" id="ceaCompanyZipcode" type="text"></td>
                </tr>
                <tr>
                    <td colspan="2">案情简介<br/>及立案依<br/>据</td>
                    <td colspan="6">
                        <textarea  name="ceaCaseSummary" id="ceaCaseSummary" rows="4"></textarea><br>
                        <div style="text-align: right;margin-right: 50px;">
                            经办人：<input name="ceaCaseAgent" id="ceaCaseAgent" type="text" style="width: 200px">
                            时间：<input class="easyui-datetimebox" name="ceaCaseDatetime" id="ceaCaseDatetime" style="width: 150px"
                                   data-options="required:false,validType:'maxLength[100]'">
                        </div>

                    </td>
                </tr>
                <tr>
                    <td colspan="2">执法机构<br/>负责人<br/>审核意见</td>
                    <td colspan="6">
                        <textarea name="ceaCompanyAuditopinion" id="ceaCompanyAuditopinion"  rows="2" readonly></textarea><br>
                        <div style="text-align: right;margin-right: 50px;">
                            签名：<input name="ceaCompanyAutograph" id="ceaCompanyAutograph" type="text" style="width: 200px" readonly>
                            <input type="text" style="width: 50px;text-align: right" readonly>年<!--时间：ceaCompanyDatetime-->
                            <input type="text" style="width: 30px;text-align: right" readonly>月
                            <input type="text" style="width: 30px;text-align: right" readonly>日
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
                    <td colspan="6"><textarea name="remark" id="remark_1" ></textarea></td>
                </tr>
            </table>
        </form>

    </div>
    <div style="text-align:center;">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitExamApprovalCase()" style="width:80px">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="cancelExamApprovalCase()" style="width:80px">取消</a>
    </div>
    <br/>
</div>

<!-- 手动添加案件弹出窗口（案件来源管理） -->
<div id="newCaseModule" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:500px;height:480px;padding:5px;">
    <div style="width:80%;max-width:300px;padding:10px 60px;">
        <input type="hidden" id="prId" /><!--上报记录序号，主键，唯一-->

        <form id="newCaseModuleForm" method="post">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="prSiteDescription" id="prSiteDescription" style="width:100%;height:110px;"
                       data-options="label:'现场描述:',required:true,validType:'maxLength[100]',multiline:true" >
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="prPosition" id="prPosition" style="width:100%;"
                       data-options="label:'发生位置:',required:true,validType:'maxLength[100]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-datetimebox" name="prReportTime" id="prReportTime" style="width:100%;"
                       data-options="label:'发生时间:',required:true,validType:'maxLength[100]'">
            </div>
            <div style="margin-bottom:20px">
                <select class="easyui-combobox" name="source" id="source" style="width:100%;" label="案件来源:" data-options="required:false">
                    <option value="0">部门移送</option>
                    <option value="1">上级交办</option>
                    <option value="2">群众举报</option>
                </select>
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="remark" id="remark" style="width:100%;height:90px;"
                       data-options="label:'备注:',required:false,validType:'maxLength[100]',multiline:true">
            </div>

        </form>
    </div>
    <div style="text-align:center;">
        <a href="javascript:;" class="easyui-linkbutton" onclick="submitAddCase()" style="width:80px">提交</a>
        <a href="javascript:;" class="easyui-linkbutton" onclick="cancelAddCase()" style="width:80px">取消</a>
    </div>
</div>

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


    //提交立案申请
    function submitExamApprovalCase() {
        if ($('#examApprovalCaseModuleForm').form("validate")) {//通过校验
            var actionUrl = "";
            var prId = $("#prId_1").val();//--巡检上报记录编号
            var form_data = $('#examApprovalCaseModuleForm').serializeObject();
            actionUrl = "<%=request.getContextPath()%>/caseinfo/submitExamApprovalCase?prId="+prId;
            $.ajax({
                type: "POST",
                url: actionUrl,
                data: JSON.stringify(form_data),
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    if (data.tag) {
                        $.messager.alert('我的提示', '您的操作已成功!');
                        $('#examApprovalCaseModule').window('close');
                        loadTable();
                    }
                },
                error: function (data) {
                    alert("2" + JSON.stringify(data));
                }
            });
        }
    }
    //--取消立案申请
    function cancelExamApprovalCase() {
        $('#examApprovalCaseModule').window('close');
    }

    //--新增案件来源--提交事件
    function submitAddCase() {

        if ($('#newCaseModuleForm').form("validate")) {//通过校验
            var tag = $('#tag').val();
            var actionUrl = "";
            var form_data = $('#newCaseModuleForm').serializeObject();
            if (tag && tag == 'add') {
                actionUrl = "<%=request.getContextPath()%>/app/saveCaseSource";
            } else if (tag && tag == 'edit') {
                //actionUrl = "<%=request.getContextPath()%>/uavDevice/updateUAV?prId=" + $("#prId").val();
            }

            $.ajax({
                type: "POST",
                url: actionUrl,
                data: JSON.stringify(form_data),
                dataType: "json",
                contentType: 'application/json',
                success: function (data) {
                    if (data.tag) {
                        $.messager.alert('我的提示', '您的操作已成功!');
                        $('#newCaseModule').window('close');
                        loadTable();
                    }
                },
                error: function (data) {
                    alert("2" + JSON.stringify(data));
                }
            });
        }
    }

    //--新增案件来源--取消事件
    function cancelAddCase(){
        $('#newCaseModule').window('close');//--关闭新增页面
    }

</script>
</body>
</html>