// NOTE: 用户名响应onchange,获取value值拼接路径。本地图片以用户名.jpg命名。

$(document).ready(function () {
    var arrplaceholder = ['请输入登录账号', '请输入登录密码'];

    $("input").focus(function () {
        if (!!!$(this).prop("readonly")) {
            $(this).prop("placeholder", "")
        }
    });

    $("input").blur(function () {
        //alert($(this).index());
        $(this).prop("placeholder", arrplaceholder[$(this).index() - 1])
    });

});

//--根据登陆名更换头像，暂时去掉
function loadusericon() {
    //image目录内已有 username.jpg hujihua.jpg，测试输入用户名1：username 用户名2: hujihua
    var element = document.getElementById('usericon');
    var username = document.getElementById('username').value;
    element.src = "image/" + username + ".jpg";
}
//--用户登录
function login(url) {
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

    $.post(url,
        {
            'loginName': loginName,
            'userPsw': userPsw
        }, function (data) {

            if (data.code == "0") {//--登陆成功
                window.location = "nav.jsp";
            } else {
                $.messager.alert('错误', data.message, 'warning');
            }
        });


}
