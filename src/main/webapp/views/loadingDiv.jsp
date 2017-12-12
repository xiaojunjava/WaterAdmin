<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--<div id='loadingDiv' style="position: absolute; z-index: 1000; top: 0px; left: 0px;width: 100%; height: 100%; background: white; text-align: center;">--%>
    <%--<h1 style="top: 48%; position: relative;">--%>
        <%--<font color="#15428B">努力加载中···</font>--%>
    <%--</h1>--%>
<%--</div>--%>

<script type="text/JavaScript">

    //获取浏览器页面可见高度和宽度
    var _PageHeight = document.documentElement.clientHeight,
        _PageWidth = document.documentElement.clientWidth;

    //计算loading框距离顶部和左部的距离（loading框的宽度为215px，高度为61px）
    var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 4 : 0,
        _LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2.5 : 0;

    //加载gif地址
    var Loadimagerul="<%=request.getContextPath()%>/resources/cmd/image/loading.gif";

    //在页面未加载完毕之前显示的loading Html自定义内容
    var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight
        + 'px;top:0;background:#f3f8ff;opacity:1;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor1: wait; left: '
        + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width:100px;; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff url('+Loadimagerul+') no-repeat scroll 5px 12px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">玩命加载中...</div></div>';

    //呈现loading效果
    document.write(_LoadingHtml);

    function closeLoading() {
        $("#loadingDiv").fadeOut("normal", function () {
            $(this).remove();
        });
    }
    var no;
    $.parser.onComplete = function () {
        if (no) clearTimeout(no);
        no = setTimeout(closeLoading, 50);
    }
</script>