<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>水政单位通讯录</title>
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
        $.extend($.fn.validatebox.defaults.rules, {
            phoneNum: { //验证手机号
                validator: function(value, param){
                    return /^1[3-8]+\d{9}$/.test(value);
                },
                message: '请输入正确的手机号码。'
            },

            telNum:{ //既验证手机号，又验证座机号
                validator: function(value, param){
                    return /(^(0[0-9]{2,3})?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\d3)|(\d{3}\-))?(1[358]\d{9})$)/.test(value);
                },
                message: '请输入正确的电话号码。'
            }
        });

        $(function () {
            //--加载表格数据
            loadTable();
        });

        //--获取水政单位通讯录数据
        function loadTable(queryData) {
            //--显示的列模型
            var columns = [[
                {field: 'ck', checkbox: true},
                {field: 'unitId', title: '单位序号', width: 80, align: 'center', sortable: 'true'},
                {field: 'unitName', title: '单位名称', width: 160, align: 'center', sortable: 'true'},
                {field: 'unitAddress', title: '单位地址', width: 160, align: 'center'},
                {field: 'unitContactName', title: '联系人姓名', width: 80, align: 'center'},
                {field: 'unitContactTel', title: '联系人手机', width: 80, align: 'center'},
                {field: 'unitTel', title: '单位电话', width: 160, align: 'center'},
                {field: 'unitFax', title: '单位传真', width: 160, align: 'center'},
                {field: 'remark', title: '备注', width: 80, align: 'center'}
            ]];

            //--获取数据库中的数据
            var jsonData = {
                url: '<%=request.getContextPath()%>/wateradmin/getWateradminData',
                title: '水政单位通讯录列表',
                halign: 'center',
                align: 'center',
                method: 'get',
                columns: columns,
                rownumbers: true,
                remoteSort: true,
                pagination: true,
                autoRowHeight: false,
                fitColumns: true,//允许表格自动缩放，以适应父容器
                pageSize: '10',
                width: '90%',
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
                }, '-', {
                    text: '编辑',
                    iconCls: 'icon-add',
                    handler: edit
                }, '-', {
                    text: '删除',
                    iconCls: 'icon-remove',
                    handler: del
                }]

            };

            $('#list_awal').datagrid(jsonData);//加载数据
            $('#list_awal').datagrid('hideColumn','unitId');

        }
        function add() {
            $('#awalModuleForm').form('clear');//清除表单数据
            $("#tag").val("add");//操作标识
            $("#awalModule").panel({title: "&nbsp;添加水政单位"});
            $('#awalModule').window('open');
        }
        function edit() {
            var rows = $('#list_awal').datagrid('getSelections');

            if (rows && rows.length == 1) {
                $("#tag").val("edit");//操作标识
                $('#awalModuleForm').form('clear');

                //--给字段赋值
                $("#unitId").val(rows[0].unitId);//--主键，唯一

                $("#unitName").textbox('setValue',rows[0].unitName);
                $("#unitAddress").textbox('setValue',rows[0].unitAddress);
                $("#unitContactName").textbox('setValue',rows[0].unitContactName);

                $("#unitContactTel").textbox('setValue',rows[0].unitContactTel);
                $("#unitTel").textbox('setValue',rows[0].unitTel);
                $("#unitFax").textbox('setValue',rows[0].unitFax);
                $("#remark").textbox('setValue',rows[0].remark);
            } else {
                $.messager.alert('提示', '请选择一条记录！', 'warning');
                return false;
            }

            //先选择一条记录
            $("#awalModule").panel({title: "&nbsp;编辑水政单位"});
            $('#awalModule').window('open');
        }
        function del() {
            var rows = $('#list_awal').datagrid('getSelections');
            if (rows && rows.length == 1) {
                $.messager.confirm('Confirm', '您确定要删除该条记录吗?', function (r) {
                    if (r) {
                        $.ajax({
                            type: "POST",
                            url: "<%=request.getContextPath()%>/wateradmin/delWateradmin",
                            data: {"unitId": rows[0].unitId},
                            dataType: "json",
                            success: function (data) {
                                if (data.tag) {
                                    $.messager.alert('我的提示', '您的操作已成功!');
                                    $('#awalModule').window('close');
                                    loadTable();
                                }
                            },
                            error: function (data) {
                                alert("2" + JSON.stringify(data));
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
<!-- 水政单位通讯录查询条件 -->
<div class="easyui-panel" title="查询" style="width:90%;padding:10px 10px 10px 10px;margin:0px 0px 1px 0px">
    <div style="margin-bottom:0px">
        <form id="awalSearchForm" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            单位名称：<input class="easyui-textbox" name="unitName" style="width:12%;" data-options="validType:'maxLength[20]'">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            联系人：<input class="easyui-textbox" name="unitContactName" style="width:12%"
                                   data-options="validType:'maxLength[10]'">

            <div style="display:inline; float:right">
                <a href="javascript:;" class="easyui-linkbutton" onclick="submitAwalSearchForm()" style="width:80px">查询</a>&nbsp;&nbsp;
                <a href="javascript:;" class="easyui-linkbutton" onclick="clearAwalSearchForm()" style="width:80px">清空</a>&nbsp;&nbsp;
            </div>
        </form>
    </div>
</div>

<!-- 水政单位通讯录列表 -->
<div id="list_awal"></div>

<!-- 新增或编辑的弹出窗口 -->
<div id="awalModule" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:500px;height:450px;padding:5px;">
    <div style="width:80%;max-width:300px;padding:10px 60px;">
        <input type="hidden" id="tag"/>
        <input type="hidden" id="unitId" /><!--序号，主键，唯一-->

        <form id="awalModuleForm" method="post">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="unitName" id="unitName" style="width:100%"
                       data-options="label:'单位名称:',required:true,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="unitAddress" id="unitAddress" style="width:100%"
                       data-options="label:'单位地址:',required:true,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="unitContactName" id="unitContactName" style="width:100%"
                       data-options="label:'联系人姓名:',required:true,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="unitContactTel" id="unitContactTel" style="width:100%"
                       data-options="label:'联系人手机:',required:true,validType:'phoneNum'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="unitTel" id="unitTel" style="width:100%"
                       data-options="label:'单位电话:',required:true,validType:'telNum'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="unitFax" id="unitFax" style="width:100%"
                       data-options="label:'单位传真:',required:false,validType:'telNum'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="remark" id="remark" style="width:100%"
                       data-options="label:'备注:',required:false,validType:'maxLength[20]'">
            </div>
        </form>
    </div>
    <div style="text-align:center;">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm2()" style="width:80px">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()" style="width:80px">清空</a>
    </div>
</div>

<script>
    //查询
    function submitAwalSearchForm() {
        if ($('#awalSearchForm').form("validate")) {//通过校验
            var search_data = $('#awalSearchForm').serializeObject();
            loadTable(search_data);
        }
    }

    function clearAwalSearchForm(){
        $('#awalSearchForm').form('clear');
    }

    //新增&编辑
    function submitForm2() {
        if ($('#awalModuleForm').form("validate")) {//通过校验
            var tag = $('#tag').val();
            var actionUrl;
            var form_data = $('#awalModuleForm').serializeObject();
            if (tag && tag == 'add') {
                actionUrl = "<%=request.getContextPath()%>/wateradmin/saveWateradmin";
            } else if (tag && tag == 'edit') {
                actionUrl = "<%=request.getContextPath()%>/wateradmin/updateWateradmin?unitId="+$("#unitId").val();
            }
            $.ajax({
                type: "POST",
                url: actionUrl,
                data: JSON.stringify(form_data),
                dataType: "json",
                contentType : 'application/json',
                success: function (data) {
                    if (data.tag) {
                        $.messager.alert('我的提示', '您的操作已成功!');
                        $('#awalModule').window('close');
                        loadTable();
                    }
                },
                error: function (data) {
                    alert("2" + JSON.stringify(data));
                }
            });
        }
    }
    function clearForm() {
        $('#awalModuleForm').form('clear');
    }

</script>
</body>
</html>