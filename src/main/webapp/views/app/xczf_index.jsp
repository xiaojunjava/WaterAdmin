<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.sinosoft.wateradmin.app.bean.FunctionalModule" %>
<%@ page import="com.sinosoft.wateradmin.app.bean.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>巡查执法系统</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/app/css/xuncha.css">

    <script src="<%=request.getContextPath()%>/resources/jquery-3.2.1.js"></script>
    <script src="<%=request.getContextPath()%>/resources/app/js/xuncha.js"></script>
</head>
<body>
<div class="header">
    <!-- 用户信息 -->
    <div class="userinfo">
        <div class="text">
            <div class="username">
                调度员：<%=((Users)request.getSession().getAttribute("user")).getUserName()%>
            </div>
            <div id="show">时钟</div>
        </div>
        <div class="userimg">
            <img src="<%=request.getContextPath()%>/resources/handingcase/image/hujihua.jpg" alt="" class="usericon"/>
        </div>
    </div>
    <!-- 退出 -->
    <div class="logout">
        <%--<a href="<%=request.getContextPath()%>/jump.exit" ><img src="<%=request.getContextPath()%>/resources/handingcase/image/logout.png" alt="" class="logoutimg"></a>--%>
            <a href="http://193376ov44.iok.la:8081/WaterAdmin/nav.html" ><img src="<%=request.getContextPath()%>/resources/handingcase/image/logout.png" alt="" class="logoutimg"></a>
    </div>

</div>
<div class="main">
    <div class="menu">
        <!--初始化菜单-->
        <%
            List<Map<FunctionalModule,List<FunctionalModule>>> menuList = (List) request.getSession().getAttribute("menuList");
            for (int i = 0; i < menuList.size(); i++) {
                Map<FunctionalModule,List<FunctionalModule>> map = menuList.get(i);

                for (FunctionalModule module : map.keySet()) {//--module为一级菜单，list为二级菜单
                    List<FunctionalModule> secMenuList = map.get(module);
        %>
        <div class="menu1">
            <a href="#" class="zlgl"><i></i><span><%=module.getFmName()%></span></a>
        </div>
                <div class="menu2">
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
        <iframe name="open_page" src="<%=request.getContextPath()%>/xczf/jump_xczf_report_search" frameborder="0" width="100%" height="100%">

        </iframe>
    </div>

</div>
</div>
</body>
</html>
