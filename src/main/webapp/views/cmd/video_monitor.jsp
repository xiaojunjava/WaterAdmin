<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="utf-8">
    <link href="<%=request.getContextPath()%>/resources/Video/css/video-js.css" rel="stylesheet">
    <style>
        body{background-color: #191919;}
        /*.m{ width: 740px; height: 400px; margin-left: auto; margin-right: auto; margin-top: 50px; }*/
        .video-js {
            width:100%;
            height:80%;
            margin: 0 auto;
        }
        .video-js .vjs-tech {
            position: relative;
        }
        .video-js .vjs-big-play-button {
            border-radius: 50%;
            width: 8em;
            height: 8em;
            line-height: 8em;
            font-size: 1em;
        }
        .video-js .vjs-big-play-button:before {
            font-size: 4.5em;
        }
    </style>
    <script type="text/javascript">
        $('.vjs-styles-defaults').remove();
        $('.vjs-styles-dimensions').remove();
    </script>
</head>
<body>
    <video id="my-video" class="video-js" controls preload="auto" data-setup="{}">
        <source src="<%=request.getContextPath()%>/resources/Video/testVideo.mp4" type="video/mp4">
        <p class="vjs-no-js">
            To view this video please enable JavaScript, and consider upgrading to a web browser that
            <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>
        </p>
    </video>
    <script src="<%=request.getContextPath()%>/resources/Video/js/video.min.js"></script>
    <script type="text/javascript">
        var myPlayer = videojs('my-video');
        videojs("my-video").ready(function(){
            var myPlayer = this;
            myPlayer.play();
        });
    </script>
</body>
</html>
