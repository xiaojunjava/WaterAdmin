<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>立案申请</title>
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
                {field: 'status', title: '状态', width: 80, align: 'center',formatter:function(value,row,index){
                    if (value == '0'){//0-女，1-男
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
                {field: 'remark', title: '备注', width: 200, align: 'center'},

                {field: 'opt', title: '操作', width: 120, align: 'center',formatter:function(value,row,index){
                    var tmpOptStr = "<div style='display:inline; float:right'>";

                    if (row.status=='0'){
                        tmpOptStr = tmpOptStr +"<a href='javascript:;' class='easyui-linkbutton' onclick='addFiling("+row.prId+",\""+row.prSiteDescription+"\")' style='width:80px'>立案</a>&nbsp;&nbsp;";
                    }
                    tmpOptStr = tmpOptStr +"<a href='javascript:;' class='easyui-linkbutton' onclick='viewDetail("+row.prId+")' style='width:80px'>详情</a>&nbsp;&nbsp;";
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
                toolbar: [/*{
                    text: '新增',
                    iconCls: 'icon-save',
                    handler: add						//handler类似事件
                }, '-', {
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
        }

        /*//--点击新增按钮动作
        function add() {
            $('#filingModuleForm').form('clear');//清除表单数据
            $("#tag").val("add");//操作标识
            $("#filingModule").panel({title: "&nbsp;添加角色"});//--标题
            $('#filingModule').window('open');//--打开新增页面
        }

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
        function addFiling(prId,prSiteDescription){
            $('#filingModuleForm').form('clear');//清除表单数据
            $("#prId").val(prId);//--主键，唯一
            $("#prSiteDescription").textbox('setValue',prSiteDescription);

            $("#filingModule").panel({title: "&nbsp;立案申请"});//--标题
            $('#filingModule').window('open');//--打开新增页面
        }

        //--点击删除按钮动作
        function del() {
            var rows = $('#list_filing').datagrid('getSelections');
            if (rows && rows.length == 1) {
                $.messager.confirm('警告', '您确定要删除该角色吗?', function (r) {
                    if (r) {
                        $.ajax({
                            type: "POST",
                            url: "<%=request.getContextPath()%>/filing/delFiling",
                            data: {"filingId": rows[0].filingId},
                            dataType: "json",
                            success: function (data) {
                                if (data.tag) {
                                    $.messager.alert('我的提示', '您的操作已成功!');
                                    $('#filingModule').window('close');
                                    loadTable();
                                }
                            },
                            error: function (data) {
                                $.messager.alert('提示', '该角色正在使用中，不能被删除，如果疑问，请联系技术支持人员！');
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

<!-- 立案申请的弹出窗口 -->
<div id="filingModule" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:500px;height:450px;padding:5px;">
    <div style="width:80%;max-width:300px;padding:10px 60px;">
        <input type="hidden" id="prId" /><!--角色序号，主键，唯一-->

        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="prSiteDescription" id="prSiteDescription" style="width:100%;height:130px;"
                   data-options="label:'现场描述:',required:false,validType:'maxLength[100]',multiline:true" readonly >
        </div>

        <form id="filingModuleForm" method="post">

            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="applyReason" id="applyReason" style="width:100%;height:130px;"
                       data-options="label:'申请理由:',required:true,validType:'maxLength[100]',multiline:true">
            </div>

        </form>
    </div>
    <div style="text-align:center;">
        <a href="javascript:;" class="easyui-linkbutton" onclick="submitFilingModuleForm()" style="width:80px">提交</a>
        <a href="javascript:;" class="easyui-linkbutton" onclick="clearFilingModuleForm()" style="width:80px">清空</a>
    </div>
</div>

<script>
    //查询
    function submitFilingSearchForm() {
        if ($('#filingSearchForm').form("validate")) {//通过校验
            var search_data = $('#filingSearchForm').serializeObject();
            loadTable(search_data);
        }
    }

    function clearFilingSearchForm(){
       $('#filingSearchForm').form('clear');
    }


    //提交立案申请
    function submitFilingModuleForm() {
        if ($('#filingModuleForm').form("validate")) {//通过校验

            var actionUrl = "";
            var prId = $("#prId").val();//--巡检上报记录编号
            //alert(filingId);
            var form_data = $('#filingModuleForm').serializeObject();
            actionUrl = "<%=request.getContextPath()%>/caseinfo/applyCase?prId="+$("#prId").val()+"&applyReason="+$("#applyReason").textbox('getValue');
            $.ajax({
                type: "POST",
                url: actionUrl,
                data: {

                },
                dataType: "json",
                contentType : 'application/json',
                success: function (data) {
                    if (data.tag) {
                        $.messager.alert('我的提示', '您的操作已成功!');
                        $('#filingModule').window('close');
                        loadTable();
                    }
                },
                error: function (data) {
                    alert("2" + JSON.stringify(data));
                }
            });
        }
    }
    //--清空
    function clearFilingModuleForm() {
        $('#filingModuleForm').form('clear');
    }


</script>
</body>
</html>