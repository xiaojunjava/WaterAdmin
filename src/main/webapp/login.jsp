<%--
  Description:登陆页面
  User: lvzhixue
  Date: 2017/11/17
  Time: 9:38
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">

    <title>水政监察综合系统平台登录</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/color.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/login.css">

    <script src="<%=request.getContextPath()%>/resources/jquery-3.2.1.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
    <script src="<%=request.getContextPath()%>/resources/js/login_and_nav/login.js"></script>
    <script  type="text/javascript">
        $(function () {
            $('#username').keydown(function (e) {
                if (e.keyCode == 13) {
                    login('<%=request.getContextPath()%>/login/pcUserLogin');
                }
            });

            $('#password').keydown(function (e) {
                if (e.keyCode == 13) {
                    login('<%=request.getContextPath()%>/login/pcUserLogin');
                }
            });
        });

    </script>
</head>
<body>
<div class="logo">
    <img src="<%=request.getContextPath()%>/resources/images/login/login_logo.png">
</div>
<div class="main">
    <div class="login">
        <img id="usericon" src="<%=request.getContextPath()%>/resources/images/login/usericon.png" onerror="javascript:this.src='<%=request.getContextPath()%>/resources/images/login/usericon.png';" alt="用户头像">
        <input type="text" name="username" value="" class="username" id="username" placeholder="请输入登录账号">
        <input type="password" name="password" value="" class="password" id="password" placeholder="请输入登录密码">
        <button type="button" name="button" onclick="login('<%=request.getContextPath()%>/login/pcUserLogin');">登录</button>
        <input name="username" id="333"  disabled style="display:none"/>
    </div>
</div>

<div class="copyright">
    <p>copyright 2000-2017 中科软股份有限公司 版权所有</p>
    <p>联系电话：010-4512711</p>
    <p>技术支持：北京中科软科技股份有限公司</p>
</div>
</body>
</html>
