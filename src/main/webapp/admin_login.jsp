<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>水政监察系统管理平台-登录</title>
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/icon.css"/>
    <link rel="stylesheet" type="text/css"  href="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/themes/color.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/index.css"/>

    <script src="<%=request.getContextPath()%>/resources/jquery-3.2.1.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>

    <script type="text/javascript">

        function login() {

            var loginName = $("#username").val();
            if (null == loginName || "" == $.trim(loginName)) {
                $.messager.alert('提示', '用户名不能为空!', 'warning');
                return;
            } else if (loginName.length < 6) {
                $.messager.alert('提示', '用户名太短!', 'warning');
                return;
            }

            var userPsw = $("#password").val();
            if (null == userPsw || "" == $.trim(userPsw)) {
                $.messager.alert('提示', '密码不能为空!', 'warning');
                return;
            } else if (userPsw.length < 6) {
                $.messager.alert('提示', '密码过于简单!', 'warning');
                return;
            }

            $.post("<%=request.getContextPath()%>/login/pcAdminUserLogin",
                {
                    'loginName': loginName,
                    'userPsw': userPsw
                }, function (data) {

                    if (data.code == "0") {//--登陆成功
                        window.location = "views/manager/manager_index.jsp";
                    } else {
                        $.messager.alert('错误', data.message, 'warning');
                    }
                });
        }
    </script>

    <script  type="text/javascript">
        $(function () {
            $('#username').keydown(function (e) {
                if (e.keyCode == 13) {
                    login();
                }
            });

            $('#password').keydown(function (e) {
                if (e.keyCode == 13) {
                    login();
                }
            });

        });

    </script>
</head>
<body id="login_bg">
<form id="loginform" name="loginform" method="post">
    <div class="box">
        <div class="login_logo1">水政监察系统管理平台<!-- <a href="test/goToTest">测试页1</a> --></div>
        <div class="login">
            <div class="login_pic">
                <img src="resources/images/login/login_img.gif" width="400"
                     height="224"/>
            </div>
            <div class="login_main">
                <img src="resources/images/login/rcenter.jpg"/>
                <a><input name="username" id="username" placeholder="用户名" type="text" class="input1"/></a>
                <a><input name="password" id="password" placeholder="密码" type="password" class="input2"/> </a>
                <font class="pass_word"><span><input name="" type="checkbox" value=""/>记住密码</span> <a href="#">忘记密码？</a></font>
                    <span style="display: block; float: left; width: 100%; margin-top: 15px;" onclick="login()">
					   <input name="登陆" type="button" value="" class="button1"/>
					</span>
                <input name="username" id="333" placeholder="用户名" type="text" disabled style="display:none"/>
            </div>
        </div>
        <div class="login_foot">版权所有©中科软科技股份有限公司
            Copyright@ 2004 All Rights Reserved
        </div>
    </div>
</form>
</body>
</html>