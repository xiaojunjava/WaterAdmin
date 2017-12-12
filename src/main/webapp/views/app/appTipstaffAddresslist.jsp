<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>执法人员通讯录</title>
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

        //--获取执法人员通讯录数据
        function loadTable(queryData) {
            //--显示的列模型
            var columns = [[
                {field: 'ck', checkbox: true},
                {field: 'tipstaffId', title: '序号', width: 100, align: 'center', sortable: 'true',},
                {field: 'tipstaffName', title: '执法人姓名', width: 100, align: 'center', sortable: 'true'},
                {field: 'tipstaffSex', title: '执法人性别', width: 100, align: 'center',formatter:function(value,row,index){
                    if (value == '0'){//0-女，1-男
                        return '女';
                    }else if (value == '1'){
                        return '男';
                    }
                }},
                {field: 'tipstaffOrg', title: '单位', width: 100, align: 'center'},
                {field: 'tipstaffDepartment', title: '部门', width: 100, align: 'center'},
                {field: 'tipstaffPosition', title: '职位', width: 100, align: 'center'},
                {field: 'tipstaffPersonalMailbox', title: '个人邮箱', width: 100, align: 'center'},
                {field: 'phoneNumber', title: '手机号码', width: 100, align: 'center'},
                {field: 'remark', title: '备注', width: 100, align: 'center'}
            ]];


            //--获取数据库中的数据
            var jsonData = {
                url: '<%=request.getContextPath()%>/tipstaff/getAtalTipstaffData',
                title: '执法人员通讯录列表',
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

            $('#list_atal').datagrid(jsonData);//加载数据
            $('#list_atal').datagrid('hideColumn','tipstaffId');

        }
        function add() {
            $('#atalModuleForm').form('clear');//清除表单数据
            $("#tag").val("add");//操作标识
            $("#atalModule").panel({title: "&nbsp;添加执法人员"});
            $('#atalModule').window('open');
        }
        function edit() {
            var rows = $('#list_atal').datagrid('getSelections');

            if (rows && rows.length == 1) {
                $("#tag").val("edit");//操作标识
                $('#atalModuleForm').form('clear');

                //--给字段赋值
                $("#tipstaffId").val(rows[0].tipstaffId);//--主键，唯一

                $("#tipstaffName").textbox('setValue',rows[0].tipstaffName);
                $("#tipstaffSex").combobox('setValue',rows[0].tipstaffSex);
                $("#tipstaffOrg").textbox('setValue',rows[0].tipstaffOrg);

                $("#tipstaffDepartment").textbox('setValue',rows[0].tipstaffDepartment);
                $("#tipstaffPosition").textbox('setValue',rows[0].tipstaffPosition);
                $("#tipstaffPersonalMailbox").textbox('setValue',rows[0].tipstaffPersonalMailbox);
                $("#phoneNumber").textbox('setValue',rows[0].phoneNumber);
                $("#remark").textbox('setValue',rows[0].remark);
            } else {
                $.messager.alert('提示', '请选择一条记录！', 'warning');
                return false;
            }
            //先选择一条记录
            $("#atalModule").panel({title: "&nbsp;编辑执法人员"});
            $('#atalModule').window('open');
        }
        function del() {
            var rows = $('#list_atal').datagrid('getSelections');
            if (rows && rows.length == 1) {
                $.messager.confirm('Confirm', '您确定要删除该条记录吗?', function (r) {
                    if (r) {
                        $.ajax({
                            type: "POST",
                            url: "<%=request.getContextPath()%>/tipstaff/delTipstaff",
                            data: {"tipstaffId": rows[0].tipstaffId},
                            dataType: "json",
                            success: function (data) {
                                if (data.tag) {
                                    $.messager.alert('我的提示', '您的操作已成功!');
                                    $('#atalModule').window('close');
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
<!-- 执法人员通讯录查询条件 -->
<div class="easyui-panel" title="查询" style="width:90%;padding:10px 10px 10px 10px;margin:0px 0px 1px 0px">
    <div style="margin-bottom:0px">
        <form id="atalSearchForm" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            姓名：<input class="easyui-textbox" name="tipstaffName" style="width:12%;" data-options="validType:'maxLength[20]'">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            性别：<select class="easyui-combobox" name="tipstaffSex" style="width:12%;" data-options="required:false">
                <option value="0">女</option>
                <option value="1">男</option>
            </select>

            <div style="display:inline; float:right">
                <a href="javascript:;" class="easyui-linkbutton" onclick="submitAtalSearchForm()" style="width:80px">查询</a>&nbsp;&nbsp;
                <a href="javascript:;" class="easyui-linkbutton" onclick="clearAtalSearchForm()" style="width:80px">清空</a>&nbsp;&nbsp;
            </div>
        </form>
    </div>
</div>

<!-- 执法人员通讯录列表 -->
<div id="list_atal"></div>

<!-- 新增或编辑的弹出窗口 -->
<div id="atalModule" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:500px;height:450px;padding:5px;">
    <div style="width:80%;max-width:300px;padding:10px 60px;">
        <input type="hidden" id="tag"/>
        <input type="hidden" id="tipstaffId" /><!--序号，主键，唯一-->

        <form id="atalModuleForm" method="post">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="tipstaffName" id="tipstaffName" style="width:100%"
                       data-options="label:'执法人姓名:',required:true,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <select class="easyui-combobox" name="tipstaffSex" id="tipstaffSex" style="width:100%;" label="性别:" data-options="required:false">
                    <option value="0">女</option>
                    <option value="1">男</option>
                </select>
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="tipstaffOrg" id="tipstaffOrg" style="width:100%"
                       data-options="label:'单位:',required:true,validType:'maxLength[20]'">
            </div>


            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="tipstaffDepartment" id="tipstaffDepartment" style="width:100%"
                       data-options="label:'部门:',required:true,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="tipstaffPosition" id="tipstaffPosition" style="width:100%"
                       data-options="label:'职位:',required:true,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="tipstaffPersonalMailbox" id="tipstaffPersonalMailbox" style="width:100%"
                       data-options="label:'个人邮箱:',required:false,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="phoneNumber" id="phoneNumber" style="width:100%"
                       data-options="label:'手机号码:',required:true,validType:'phoneNum'">
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
    function submitAtalSearchForm() {
        if ($('#atalSearchForm').form("validate")) {//通过校验
            var search_data = $('#atalSearchForm').serializeObject();
            loadTable(search_data);
        }
    }

    function clearAtalSearchForm(){
        $('#atalSearchForm').form('clear');
    }

    //新增&编辑
    function submitForm2() {
        if ($('#atalModuleForm').form("validate")) {//通过校验
            var tag = $('#tag').val();
            var actionUrl;
            var form_data = $('#atalModuleForm').serializeObject();
            if (tag && tag == 'add') {
                actionUrl = "<%=request.getContextPath()%>/tipstaff/saveTipstaff";
            } else if (tag && tag == 'edit') {
                actionUrl = "<%=request.getContextPath()%>/tipstaff/updateTipstaff?tipstaffId="+$("#tipstaffId").val();
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
                        $('#atalModule').window('close');
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
        $('#atalModuleForm').form('clear');
    }

</script>
</body>
</html>