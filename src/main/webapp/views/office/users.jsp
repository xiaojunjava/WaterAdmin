<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>用户管理</title>
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

            //--初始化角色列表
            $.ajax({
                type: "POST",
                url: '<%=request.getContextPath()%>/role/getRoleList',
                dataType: "json",
                success: function(data) {
                    $('#roleId').combobox({
                        data: data.list,
                        valueField: 'roleId',
                        textField: 'roleName'
                    });

                    $('#roleIdSearch').combobox({
                        data: data.list,
                        valueField: 'roleId',
                        textField: 'roleName'
                    });

                }
            });
        });

        //--获取用户管理数据
        function loadTable(queryData) {
            //--显示的列模型
            var columns = [[
                {field: 'ck', checkbox: true},
                {field: 'userId', title: '用户序号', width: 264, align: 'center', sortable: 'true',},
                {field: 'orgId', title: '机构序号', width: 264, align: 'center', sortable: 'true'},
                {field: 'userName', title: '用户名称', width: 264, align: 'center'},
                {field: 'sex', title: '性别', width: 264, align: 'center',formatter:function(value,row,index){
                    if (value == '0'){//0-女，1-男
                        return '女';
                    }else if (value == '1'){
                        return '男';
                    }
                }},
                {field: 'userLoginname', title: '登录名称', width: 264, align: 'center'},
                {field: 'userPsw', title: '密码', width: 264, align: 'center'},
                {field: 'orgName', title: '机构名称', width: 264, align: 'center',formatter:function(value,row,index){
                    return row.organization.orgName;
                }},
                {field: 'roleId', title: '角色编号', width: 264, align: 'center',formatter:function(value,row,index){
                    var tmpRoleStr = "";
                    for (var i=0;i<row.roleList.length;i++){
                        if (i==row.roleList.length-1){
                            tmpRoleStr = tmpRoleStr + row.roleList[i].roleId;
                        }else{
                            tmpRoleStr = tmpRoleStr + row.roleList[i].roleId +"-";
                        }
                    }
                    return tmpRoleStr;
                }},
                {field: 'roleName', title: '角色', width: 264, align: 'center',formatter:function(value,row,index){
                    var tmpRoleStr = "";
                    for (var i=0;i<row.roleList.length;i++){
                        tmpRoleStr = tmpRoleStr + row.roleList[i].roleName +"<br>";
                    }
                    return tmpRoleStr;
                }},
                {field: 'phoneNumber', title: '电话号码', width: 264, align: 'center'},
                {field: 'birthday', title: '出生日期', width: 264, align: 'center'}
            ]];



            //--获取数据库中的数据
            var jsonData = {
                url: '<%=request.getContextPath()%>/login/getUsersData',
                title: '用户列表',
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

            $('#list_user').datagrid(jsonData);//加载数据
            $('#list_user').datagrid('hideColumn','userId');
            $('#list_user').datagrid('hideColumn','orgId');
            $('#list_user').datagrid('hideColumn','roleId');

        }

        //--点击新增按钮动作
        function add() {
            $('#userModuleForm').form('clear');//清除表单数据
            $("#tag").val("add");//操作标识
            $("#userModule").panel({title: "&nbsp;添加用户"});//--标题
            $('#userModule').window('open');//--打开新增页面
        }

        //--点击编辑按钮动作
        function edit() {
            var rows = $('#list_user').datagrid('getSelections');

            if (rows && rows.length == 1) {
                $("#tag").val("edit");//操作标识
                $('#userModuleForm').form('clear');

                //--给字段赋值
                $("#userId").val(rows[0].userId);//--主键，唯一

                //--初始化数据
                $("#userName").textbox('setValue',rows[0].userName);
                $("#userLoginname").textbox('setValue',rows[0].userLoginname);
                $("#userPsw").textbox('setValue',rows[0].userPsw);
                $("#sex").combobox('setValue',rows[0].sex);
                $("#phoneNumber").textbox('setValue',rows[0].phoneNumber);
                $("#birthday").textbox('setValue',rows[0].birthday);
                $("#roleId").combobox('setValue',rows[0].roleList[0].roleId);
                $("#orgId").combotree('setValue',rows[0].orgId);

                //alert(rows[0].roleList[0].roleId);
            } else {
                $.messager.alert('提示', '请选择一条记录！', 'warning');
                return false;
            }

            //先选择一条记录，再打开窗口
            $("#userModule").panel({title: "&nbsp;编辑用户"});
            $('#userModule').window('open');
        }

        //--点击删除按钮动作
        function del() {
            var rows = $('#list_user').datagrid('getSelections');
            if (rows && rows.length == 1) {
                $.messager.confirm('警告', '用户删除后，将不能进行任何操作，并且删除操作不可恢复，您确定要删除该条记录吗?', function (r) {
                    if (r) {
                        $.ajax({
                            type: "POST",
                            url: "<%=request.getContextPath()%>/login/delUser",
                            data: {"userId": rows[0].userId},
                            dataType: "json",
                            success: function (data) {
                                if (data.tag) {
                                    $.messager.alert('我的提示', '您的操作已成功!');
                                    $('#userModule').window('close');
                                    loadTable();
                                }
                            },
                            error: function (data) {
                                $.messager.alert('提示', '该用户正在使用中，不能被删除，如果疑问，请联系技术支持人员！');
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

<!-- 用户管理查询条件 -->
<div class="easyui-panel" title="查询" style="width:90%;padding:10px 10px 10px 10px;margin:0px 0px 1px 0px">
    <div style="margin-bottom:0px">
        <form id="userSearchForm" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            用户名称：<input class="easyui-textbox" name="userName" style="width:12%;" data-options="validType:'maxLength[20]'">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <!--角色：<input class="easyui-combobox" name="roleId" id="roleIdSearch" style="width:12%"/>-->

            <div style="display:inline; float:right">
                <a href="javascript:;" class="easyui-linkbutton" onclick="submitUserSearchForm()" style="width:80px">查询</a>&nbsp;&nbsp;
                <a href="javascript:;" class="easyui-linkbutton" onclick="clearUserSearchForm()" style="width:80px">清空</a>&nbsp;&nbsp;
            </div>
        </form>
    </div>
</div>

<!-- 用户管理列表 -->
<div id="list_user"></div>

<!-- 新增或编辑的弹出窗口 -->
<div id="userModule" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:500px;height:470px;padding:5px;">
    <div style="width:80%;max-width:300px;padding:10px 60px;">
        <input type="hidden" id="tag"/>

        <input type="hidden" id="userId" /><!--用户序号，主键，唯一-->

        <form id="userModuleForm" method="post">
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="userName" id="userName" style="width:100%"
                       data-options="label:'用户名称:',required:true,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="userLoginname" id="userLoginname" style="width:100%"
                       data-options="label:'登录名称:',required:true,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="userPsw" id="userPsw" style="width:100%"
                       data-options="label:'密码:',required:true,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <select class="easyui-combobox" name="sex" id="sex" style="width:100%;" label="性别:" data-options="required:false">
                    <option value="0">女</option>
                    <option value="1">男</option>
                </select>
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-textbox" name="phoneNumber" id="phoneNumber" style="width:100%"
                       data-options="label:'电话号码:',required:false,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <input class="easyui-datebox" name="birthday"  id="birthday" style="width:100%"
                       data-options="label:'出生日期:',required:false,validType:'maxLength[20]'">
            </div>

            <div style="margin-bottom:20px">
                <input class="easyui-combobox" name="roleId" id="roleId" style="width:100%"
                       data-options="label:'角色:',required:true" />
            </div>

            <div style="margin-bottom:20px">
                <select class="easyui-combotree" name="orgId" id="orgId" style="width:100%" url="<%=request.getContextPath()%>/org/getOrgTreeList"
                        data-options="label:'机构:',required:true">
                </select>
            </div>
        </form>
    </div>
    <div style="text-align:center;">
        <a href="javascript:;" class="easyui-linkbutton" onclick="submitUserModuleForm()" style="width:80px">提交</a>
        <a href="javascript:;" class="easyui-linkbutton" onclick="clearUserModuleForm()" style="width:80px">清空</a>
    </div>
</div>

<script>
    //查询
    function submitUserSearchForm() {
        if ($('#userSearchForm').form("validate")) {//通过校验
            var search_data = $('#userSearchForm').serializeObject();
            loadTable(search_data);
        }
    }

    function clearUserSearchForm(){
       $('#userSearchForm').form('clear');
    }


    //新增&编辑
    function submitUserModuleForm() {
        if ($('#userModuleForm').form("validate")) {//通过校验

            var tag = $('#tag').val();
            var actionUrl = "";

            var orgId = $("#orgId").combotree("getValue");//--机构编号
            var roleId = $("#roleId").val();//--角色编号
            //alert(orgId);
            //alert(roleId);

            var form_data = $('#userModuleForm').serializeObject();
            if (tag && tag == 'add') {
                actionUrl = "<%=request.getContextPath()%>/login/saveUser?orgId="+orgId+"&roleId="+roleId;
            } else if (tag && tag == 'edit') {
                actionUrl = "<%=request.getContextPath()%>/login/updateUser?userId="+$("#userId").val()+"&orgId="+orgId+"&roleId="+roleId;
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
                        $('#userModule').window('close');
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
    function clearUserModuleForm() {
        $('#userModuleForm').form('clear');
    }

</script>
</body>
</html>