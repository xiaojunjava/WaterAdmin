<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>附件上传测试</title>

<script
	src="../resources/jquery-3.2.1.js"
	type="text/javascript"></script>


<script type="text/javascript" src="../resources/ajaxfileupload.js"></script>

<script type="text/javascript">
	$(function() {
		$(".clickt ").click(function() {
			$("#photoFile").click();
		});
	})

    //--上传照片文件
    function uploadPhotoFile(){
    	
    	$.ajaxFileUpload({
			fileElementId : "photoFile",
			type : "post",
			dataType : 'text',			
			url : '<%=request.getContextPath()%>/app/uploadEvidence?ciId='+1+'&loginName=lvzhixue',
			success : function(data) {
				data = $.parseJSON($(data).text());
				if (data.code == "0") {
					alert('上传成功！');
				} else {
					alert(data.message);
				}
			},
			error : function(data) {
				alert(data);
			},
			complete : function(XMLHttpRequest, textStatus) {
			}
		});

    }
	
</script>
</head>
<body>
		<!--主要内容-->
			<div class="title">
				<p>
					<a href="javascript:;" class="button-a clickt">上传照片</a>
					<input type="file" id="photoFile" name="photoFile" style="display: none;" onchange="uploadPhotoFile()" />
				</p>
			</div>
</body>
</html>

