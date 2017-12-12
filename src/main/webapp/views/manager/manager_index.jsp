<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.sinosoft.wateradmin.app.bean.FunctionalModule" %>

<%@ page import="com.sinosoft.wateradmin.app.bean.Users" %><%--
  Description:后台管理系统
  User: lvzhixue
  Date: 2017/11/22 13:38
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>后台管理系统</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/manager/css/manager.css">

    <script src="<%=request.getContextPath()%>/resources/jquery-3.2.1.js"></script>
    <!--<script src="layui/layui.js" charset="utf-8"></script>-->
    <script src="<%=request.getContextPath()%>/resources/manager/js/manager.js"></script>

</head>
<body>
<div class="header">
    <!-- 一级导航
    <div class="nav">
        <a href="javascript:;" class="underline">首页</a>
        <div>|</div>
        <a href="javascript:;">站内通知</a>
        <div>|</div>
        <a href="javascript:;">学习进程</a>
        <div>|</div>
        <a href="javascript:;">学习资料</a>
    </div>
    -->
    <!-- 用户信息 -->
    <div class="userinfo">
        <div class="text">
            <div class="username">
                调度员：<%=((Users)request.getSession().getAttribute("user")).getUserName()%>
            </div>
            <div id="show">时钟</div>
        </div>
        <div class="userimg">
            <img src="<%=request.getContextPath()%>/resources/manager/image/hujihua.jpg" alt="" class="usericon"/>
        </div>
    </div>
    <!-- 退出 -->
    <div class="logout">
        <a href="javascript:;" onclick="window.location.href='<%=request.getContextPath()%>/admin_login.jsp'"><img src="<%=request.getContextPath()%>/resources/manager/image/logout.png" alt="" class="logoutimg"></a>
    </div>

</div>
<div class="main">
    <div class="menu" >
        <!--初始化菜单-->
        <%
            List<Map<FunctionalModule,List<FunctionalModule>>> menuList = (List) request.getSession().getAttribute("menuList");
            for (int i = 0; i < menuList.size(); i++) {
                Map<FunctionalModule,List<FunctionalModule>> map = menuList.get(i);
                String cls_name="zlgl";//切换图标
                switch (i+1){
                    case 1:
                        break;
                    case 2:
                    	cls_name="kcgl";
                    	break;
                    case 3:
                    	cls_name="ktgl";
                    	break;
                    case 4:
                    	cls_name="ksgl";
                }
                for (FunctionalModule module : map.keySet()) {//--module为一级菜单，list为二级菜单
                    List<FunctionalModule> secMenuList = map.get(module);
        %>
        <div class="menu1" >
            <a href="../manager/manager_home.jsp" target="open_page" class="<%=cls_name%>"><i></i><span><%=module.getFmName()%></span></a>
        </div>
        <div class="menu2" >
            <%
                for (int j = 0; j < secMenuList.size(); j++) {//--二级菜单
                    FunctionalModule secModule = secMenuList.get(j);
            %>
            <div class="menu3">
                <a href="<%=request.getContextPath()%>/<%=secModule.getFmAccessAddress()%>" target="open_page"><i></i><span><%=secModule.getFmName()%></span></a>
            </div>
            <%
                }
            %>
        </div>
        <%
                }
            }
        %>
    </div>

    <div class="right">
        <iframe name="open_page" src="../manager/manager_home.jsp" frameborder="0" width="100%" height="100%">

        </iframe>
    </div>

</div>
</div>
</body>
</html>
