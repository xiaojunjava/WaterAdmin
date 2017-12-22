<%@ page import="com.sinosoft.wateradmin.app.bean.FunctionalModule" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>功能模块</title>
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
</head>
<body>
    <%@include file="../../views/loadingDiv.jsp"%>

<!--功能模块主体-->
<div>
    <ul id="tt" class="easyui-tree"
        data-options="method:'get',animate:true,lines:true,checkbox:false,onlyLeafCheck:true,cascadeCheck:true,dnd:true">
        <li>
            <span>张家港市水政监察管理平台</span>
            <ul>
                <%
                    //--获取菜单，并进行菜单的初始化
                    List<FunctionalModule> systemList = (List<FunctionalModule>) request.getSession().getAttribute("functionModule");
                    //--遍历系统菜单，进行初始化
                    for (int i = 0; i < systemList.size(); i++) {
                        FunctionalModule systemMenu = systemList.get(i);
                %>

                <li data-options="state:'closed',id:<%=systemMenu.getFmId()%>,attributes:{fmId:'<%=systemMenu.getFmId()%>',fmName:'<%=systemMenu.getFmName()%>',fmCode:'<%=systemMenu.getFmCode()%>',parFmId:'<%=systemMenu.getParFmId()%>',fmAccessAddress:'<%=systemMenu.getFmAccessAddress()%>',remark:'<%=systemMenu.getRemark()%>',seqNo:'<%=systemMenu.getSeqNo()%>',fmDepth:'<%=systemMenu.getFmDepth()%>',fmType:'<%=systemMenu.getFmType()%>'}">
                    <!--系统菜单-->
                    <span><%=systemMenu.getFmName()%></span>
                    <ul>
                        <%
                            List<FunctionalModule> firstMenuList = systemMenu.getChildNodeList();
                            for (int j = 0; j < firstMenuList.size(); j++) {//--遍历一级菜单
                                FunctionalModule firstMenu = firstMenuList.get(j);
                        %>
                        <li data-options="state:'closed',id:<%=firstMenu.getFmId()%>,attributes:{fmId:'<%=firstMenu.getFmId()%>',fmName:'<%=firstMenu.getFmName()%>',fmCode:'<%=firstMenu.getFmCode()%>',parFmId:'<%=firstMenu.getParFmId()%>',fmAccessAddress:'<%=firstMenu.getFmAccessAddress()%>',remark:'<%=firstMenu.getRemark()%>',seqNo:'<%=firstMenu.getSeqNo()%>',fmDepth:'<%=firstMenu.getFmDepth()%>',fmType:'<%=firstMenu.getFmType()%>'}">
                            <span><%=firstMenu.getFmName()%></span>
                            <ul>
                                <%
                                    List<FunctionalModule> secondMenuList = firstMenu.getChildNodeList();
                                    for (int k = 0; k < secondMenuList.size(); k++) {
                                        FunctionalModule secMenu = secondMenuList.get(k);
                                %>
                                <li data-options="id:<%=secMenu.getFmId()%>,attributes:{fmId:'<%=secMenu.getFmId()%>',fmName:'<%=secMenu.getFmName()%>',fmCode:'<%=secMenu.getFmCode()%>',parFmId:'<%=secMenu.getParFmId()%>',fmAccessAddress:'<%=secMenu.getFmAccessAddress()%>',remark:'<%=secMenu.getRemark()%>',seqNo:'<%=secMenu.getSeqNo()%>',fmDepth:'<%=secMenu.getFmDepth()%>',fmType:'<%=secMenu.getFmType()%>'}"><%=secMenu.getFmName()%>
                                </li>
                                <%
                                    }
                                %>
                            </ul>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                </li>
                <%
                    }
                %>
            </ul>
        </li>
    </ul>
    <!--右键菜单div-->
    <div id="mm" class="easyui-menu" style="width: 120px;">
        <div onclick="add()" data-options="iconCls:'icon-add'">添加</div>
        <div onclick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
        <div onclick="removeit()" data-options="iconCls:'icon-remove'">删除</div>
        <div class="menu-sep"></div>
        <div onclick="expand()">展开</div>
        <div onclick="collapse()">折叠</div>
    </div>
</div>


<!--添加&编辑-->
<!-- 新增或编辑的弹出窗口 -->
<div id="treeModule" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:500px;height:450px;padding:5px;">
    <div style="width:80%;max-width:300px;padding:10px 60px;">
        <input type="hidden" id="tag"/>
        <input type="hidden" id="fmId"/><!--序号，主键，唯一-->
        <input type="hidden" id="parFmId"/><!--父结点编号-->
        <input type="hidden" id="fmDepth"/><!--层级深度-->

        <form id="treeModuleForm" method="post">
            <div style="margin-bottom:20px"><!--模块名称-->
                <input class="easyui-textbox" name="fmName" id="fmName" style="width:100%"
                       data-options="label:'模块名称:',required:true,validType:'maxLength[20]'">
            </div>

            <div style="margin-bottom:20px"><!--模块编号-->
                <input class="easyui-textbox" name="fmCode" id="fmCode" style="width:100%"
                       data-options="label:'模块编号:',required:false,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px"><!--访问地址-->
                <input class="easyui-textbox" name="fmAccessAddress" id="fmAccessAddress" style="width:100%"
                       data-options="label:'访问地址:',required:false,validType:'maxLength[100]'">
            </div>

            <div style="margin-bottom:20px"><!--顺序号-->
                <input class="easyui-numberbox" min="1" max="30" precision="0" name="seqNo" id="seqNo" style="width:100%"
                       data-options="label:'顺序号:',required:false,validType:'maxLength[20]'">
            </div>
            <div style="margin-bottom:20px">
                <select class="easyui-combobox" name="fmType" id="fmType" style="width:100%;" label="访问控制:"
                        data-options="required:true">
                    <option value="1">app</option>
                    <option value="2">指挥中心</option>
                    <option value="3">普通模块</option>
                    <option value="4">系统管理模块</option>
                </select>
            </div>
            <div style="margin-bottom:20px"><!--备注-->
                <input class="easyui-textbox" name="remark" id="remark" style="width:100%"
                       data-options="label:'备注:',required:false,validType:'maxLength[20]'">
            </div>
        </form>
    </div>
    <div style="text-align:center;">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitTreeModuleForm()" style="width:80px">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearTreeModuleForm()" style="width:80px">清空</a>
    </div>
</div>

<script type="text/javascript">
    $("#tt").tree({
        // 右键菜单
        onContextMenu: function (e, node) {
            //alert(node.target);

            e.preventDefault();
            // 选择一个结点
            $('#tt').tree('select', node.target);
            // 显示右键菜单
            $('#mm').menu('show', {
                left: e.pageX,
                top: e.pageY
            });
        },
        // 将多选改成单选
        onCheck: function (node, checked) {
            if (checked == true) {
                var nodes = $('#tt').tree('getChecked');

                for (var i = 0; i < nodes.length; i++) {
                    if (nodes[i].id != node.id) {
                        $("#tt").tree('uncheck', nodes[i].target);
                    }
                }
            }
        },
        // 双击可进行编辑
        onDblClick: function (node) {
            //alert(node.id);
            //alert(node.text);
            /*alert("fmId:"+node.attributes.fmId);
             alert("fmName:"+node.attributes.fmName);
             alert("fmCode:"+node.attributes.fmCode);
             alert("parFmId:"+node.attributes.parFmId);
             alert("fmAccessAddress:"+node.attributes.fmAccessAddress);
             alert("remark:"+node.attributes.remark);
             alert("seqNo:"+node.attributes.seqNo);
             alert("fmDepth:"+node.attributes.fmDepth);
             alert("fmType:"+node.attributes.fmType);
             */

            //$(this).tree('beginEdit', node.target);
        },
        // 编辑完成后保存
        onAfterEdit: function (node) {
            $.ajax({
                url: "Handler/Edit.ashx?cid=" + node.id + "&name=" + node.text,
                type: "GET",
                success: function (data) {
                    if (data == "True") {
                        tips("编辑完成！");
                    }
                },
                error: function () {
                    tips("错误！");
                }
            });
        }
    });

    // 选中的结点Id，格式：1,2,3
    function getChecked() {
        var nodes = $('#tt').tree('getChecked');
        var s = new Array();
        for (var i = 0; i < nodes.length; i++) {
            if (s != null) {
                s.push(nodes[i].id);
            }
        }
        return s.join(',');
    }

    // 删除结点
    function removeit() {
        var node = $('#tt').tree('getSelected');

        if (!$('#tt').tree('isLeaf', node.target)){
            $.messager.alert('提示消息', '对不起，您要删除的模块包含子模块，不能删除，请先删除子模块！');
            return false;
        }

        $.messager.confirm('Confirm', '您确定要删除该模块吗?', function (r) {
            if (r) {
                $.ajax({
                    type: "POST",
                    url: "<%=request.getContextPath()%>/module/delFunctionalModule",
                    data: {"fmId": node.id},
                    dataType: "json",
                    success: function (data) {
                        if (data.tag) {
                            $('#tt').tree('remove', node.target);
                            $.messager.alert('我的提示', '您的操作已成功!');
                        }
                    },
                    error: function (data) {
                        alert("2" + JSON.stringify(data));
                    }
                });
            }

        });
    }
    // 折叠
    function collapse() {
        var node = $('#tt').tree('getSelected');
        $('#tt').tree('collapse', node.target);
    }
    // 展开
    function expand() {
        var node = $('#tt').tree('getSelected');
        $('#tt').tree('expand', node.target);
    }
    // 弹出提示信息
    function tips(message) {
        $.messager.show({
            title: '提示消息',
            msg: message,
            timeout: 5000,
            showType: 'slide'
        });
    }

    //--点击新增按钮动作
    function add() {
        var t = $('#tt');
        var node = t.tree('getSelected');

        if (parseInt(node.attributes.fmDepth)+1>2){
           $.messager.alert('提示消息', '对不起，已达到结点层级上限，不能继续添加结点！');
           return false;
        }

        //--需初始化父结点，层级深度
        $("#parFmId").val(node.attributes.fmId);
        $("#fmDepth").val(parseInt(node.attributes.fmDepth)+1);

        /*alert("fmId:"+node.attributes.fmId);
         alert("fmName:"+node.attributes.fmName);
         alert("fmCode:"+node.attributes.fmCode);
         alert("parFmId:"+node.attributes.parFmId);
         alert("fmAccessAddress:"+node.attributes.fmAccessAddress);
         alert("remark:"+node.attributes.remark);
         alert("seqNo:"+node.attributes.seqNo);
         alert("fmDepth:"+node.attributes.fmDepth);
         alert("fmType:"+node.attributes.fmType);*/

        $('#treeModuleForm').form('clear');//清除表单数据
        $("#tag").val("add");//操作标识
        $("#treeModule").panel({title: "&nbsp;添加子模块"});//--标题
        $('#treeModule').window('open');//--打开新增页面
    }

    //--点击编辑按钮动作
    function edit() {
        var t = $('#tt');
        var node = t.tree('getSelected');

        /*alert("fmId:"+node.attributes.fmId);
         alert("fmName:"+node.attributes.fmName);
         alert("fmCode:"+node.attributes.fmCode);
         alert("parFmId:"+node.attributes.parFmId);
         alert("fmAccessAddress:"+node.attributes.fmAccessAddress);
         alert("remark:"+node.attributes.remark);
         alert("seqNo:"+node.attributes.seqNo);
         alert("fmDepth:"+node.attributes.fmDepth);
         alert("fmType:"+node.attributes.fmType);*/

        $('#treeModuleForm').form('clear');//清除表单数据
        $("#tag").val("edit");//操作标识

        //--给字段赋值
        $("#fmId").val(node.attributes.fmId);//--主键，唯一
        $("#parFmId").val(node.attributes.parFmId);
        $("#fmDepth").val(node.attributes.fmDepth);

        $("#fmName").textbox('setValue', node.attributes.fmName);
        $("#fmCode").textbox('setValue', node.attributes.fmCode == 'null' ? "" : node.attributes.fmCode);
        $("#fmAccessAddress").textbox('setValue', node.attributes.fmAccessAddress == 'null' ? "" : node.attributes.fmAccessAddress);
        $("#seqNo").textbox('setValue', node.attributes.seqNo == 'null' ? "" : node.attributes.seqNo );
        $("#fmType").combobox('setValue', node.attributes.fmType);
        $("#remark").textbox('setValue', node.attributes.remark == 'null' ? "" : node.attributes.remark);

        $("#treeModule").panel({title: "&nbsp;编辑子模块"});//--标题
        $('#treeModule').window('open');//--打开新增页面

    }

    //新增&编辑 提交保存
    function submitTreeModuleForm() {
        if ($('#treeModuleForm').form("validate")) {//通过校验

            var tag = $('#tag').val();
            var actionUrl = "";
            var form_data = $('#treeModuleForm').serializeObject();
            if (tag && tag == 'add') {
                actionUrl = "<%=request.getContextPath()%>/module/saveFunctionalModule?parFmId="+$("#parFmId").val()+"&fmDepth="+$("#fmDepth").val();
            } else if (tag && tag == 'edit') {
                actionUrl = "<%=request.getContextPath()%>/module/updateFunctionalModule?fmId="+$("#fmId").val()+"&parFmId="+$("#parFmId").val()+"&fmDepth="+$("#fmDepth").val();
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
                        $('#treeModule').window('close');

                        //--重新加载树或者树结点
                        var tree = $('#tt');
                        var node = tree.tree('getSelected');

                        if (tag && tag == 'add'){//--添加结点
                            tree.tree('append', {
                                parent: (node ? node.target : null),
                                data: [{
                                    id: data.functionalModule.fmId,
                                    text: data.functionalModule.fmName,
                                    attributes:{
                                        fmId:data.functionalModule.fmId,
                                        fmName:data.functionalModule.fmName,
                                        fmCode:data.functionalModule.fmCode,
                                        parFmId:data.functionalModule.parFmId,
                                        fmAccessAddress:data.functionalModule.fmAccessAddress,
                                        remark:data.functionalModule.remark,
                                        seqNo:data.functionalModule.seqNo,
                                        fmDepth:data.functionalModule.fmDepth,
                                        fmType:data.functionalModule.fmType
                                    }
                                }]
                            });
                        }else if (tag && tag == 'edit'){//--修改node的属性值
                            tree.tree("update", {
                                target: node.target,
                                id: data.functionalModule.fmId,
                                text: data.functionalModule.fmName,
                                attributes:{
                                    fmId:data.functionalModule.fmId,
                                    fmName:data.functionalModule.fmName,
                                    fmCode:data.functionalModule.fmCode,
                                    parFmId:data.functionalModule.parFmId,
                                    fmAccessAddress:data.functionalModule.fmAccessAddress,
                                    remark:data.functionalModule.remark,
                                    seqNo:data.functionalModule.seqNo,
                                    fmDepth:data.functionalModule.fmDepth,
                                    fmType:data.functionalModule.fmType
                                }
                            });

                        }
                    }
                },
                error: function (data) {
                    alert("2" + JSON.stringify(data));
                }
            });
        }
    }
    //--清空
    function clearTreeModuleForm() {
        $('#treeModuleForm').form('clear');
    }


</script>
</body>
</html>
