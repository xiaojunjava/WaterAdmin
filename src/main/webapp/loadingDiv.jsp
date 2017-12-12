<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div id='loadingDiv' style="position: absolute; z-index: 1000; top: 0px; left: 0px;
width: 100%; height: 100%; background: white; text-align: center;">
    <h1 style="top: 48%; position: relative;">
        <font color="#15428B">努力加载中···</font>
    </h1>
</div>

<script type="text/JavaScript">
    function closeLoading() {
        $("#loadingDiv").fadeOut("normal", function () {
            $(this).remove();
        });
    }
    var no;
    $.parser.onComplete = function () {
        if (no) clearTimeout(no);
        no = setTimeout(closeLoading, 1000);
    }
</script>