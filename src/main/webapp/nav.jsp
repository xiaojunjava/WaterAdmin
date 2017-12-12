<%@ page import="com.sinosoft.wateradmin.app.bean.FunctionalModule" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
  Description:导航页
  User: lvzhixue
  Date: 2017/11/17
  Time: 9:38
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>水政监察综合系统平台导航</title>
    <script src="<%=request.getContextPath()%>/resources/js/login_and_nav/nav.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/osnav.css">
</head>
<body>

<div class="nav">

    <%
        List<FunctionalModule> moduleList = (List<FunctionalModule>) request.getSession().getAttribute("systemList");
        for (int i = 0; i < moduleList.size(); i++) {
            FunctionalModule module = moduleList.get(i);
    %>
            <div onclick="systemMenuClick('<%=request.getContextPath()%>/<%=module.getFmAccessAddress()%>?fmId=<%=module.getFmId()%>');">
                <img src="<%=request.getContextPath()%>/resources/images/login/osnav_<%=module.getFmCode()%>.png">
            </div>
    <%
        }
    %>
</div>

</body>
</html>

