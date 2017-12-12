<%@ page import="com.sinosoft.wateradmin.app.bean.FunctionalModule" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!--功能模块主体-->
<div id="popedomTreeDiv" class="easyui-window" data-options="modal:true,closed:true,iconCls:'icon-save'"
     style="width:500px;height:400px;padding:5px;">
    <ul id="tt" class="easyui-tree"
        data-options="method:'get',animate:true,lines:true,checkbox:true,onlyLeafCheck:false,cascadeCheck:true,dnd:true">
        <li>
            <span>张家港市水政监察管理平台</span>
            <ul>
                <%
                    //--获取菜单，并进行菜单的初始化
                    List<FunctionalModule> systemList = (List<FunctionalModule>) request.getSession().getAttribute("popedomTree");
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
        <div onclick="expand()">展开</div>
        <div onclick="collapse()">折叠</div>
    </div>

    <div style="display:inline; float:right">
        <a href="javascript:;" class="easyui-linkbutton" onclick="submitPopedomSet()" style="width:80px">设置</a>&nbsp;&nbsp;
        <a href="javascript:;" class="easyui-linkbutton" onclick="closeWindow()" style="width:80px">关闭</a>&nbsp;&nbsp;
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

    //--关闭本窗口
    function closeWindow() {
        $('#popedomTreeDiv').window('close');
    }

    //--提交权限设置
    function submitPopedomSet(){
        //--获取选中的节点信息

        var selectedModuleIds = getChecked();
        $.messager.confirm('提示', '您确定要设置该角色的权限吗?', function (r) {
            if (r) {
                $.ajax({
                    type: "POST",
                    url: "<%=request.getContextPath()%>/role/setRoleModule",
                    data: {
                        "moduleIds": selectedModuleIds,
                        "roleId":curSetRoleId
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data.tag){
                            $.messager.alert('提示', '您的授权操作已成功!');
                            closeWindow();
                        }
                    },
                    error: function (data) {
                        $.messager.alert('提示', '获取角色权限信息失败！');
                    }
                });
            }
        })
    }
</script>

