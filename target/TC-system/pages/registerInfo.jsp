<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>RegisterInfo</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link href="/css/bootstrap-3.1.1.min.css" rel='stylesheet' type='text/css' />
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <!-- Custom Theme files -->
    <link href="/css/style.css" rel='stylesheet' type='text/css' />
    <link rel="stylesheet" href="css/jquery.countdown.css" />
    <link href='https://fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700' rel='stylesheet' type='text/css'>
    <!----font-Awesome----->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!----font-Awesome----->
    <script>
        $(document).ready(function(){
            $(".dropdown").hover(
                    function() {
                        $('.dropdown-menu', this).stop( true, true ).slideDown("fast");
                        $(this).toggleClass('open');
                    },
                    function() {
                        $('.dropdown-menu', this).stop( true, true ).slideUp("fast");
                        $(this).toggleClass('open');
                    }
            );
        });
    </script>
</head>
<body>

<jsp:include page="header.jsp" />


<div class="courses_banner">
    <div class="container">
        <h3>登录平台</h3>
        <p class="description">
            欢迎来到ELITE COLLEGE精英学校。这里是登录平台，这里有最权威的教育精英课程。一路精彩，等你启航。
        </p>
        <div class="breadcrumb1">
            <ul>
                <li class="icon6"><a href="/">主页</a></li>
                <li class="current-page">注册平台</li>
            </ul>
        </div>
    </div>
</div>

<div class="courses_box1">
    <div class="container">
        <div class="col-md-6 about_left">
            <h1><%=request.getAttribute("info") %></h1>
            <p>请牢记您的卡号！该卡号将作为您以后的登录凭证！继续完成激活就可以立即体验ELITE在线课程选择！</p>
            <ul class="about_links">
                <li><a href="#">1. 注册并完成身份信息验证</a></li>
                <li><a href="#">2. 快速激活学员卡</a></li>
                <li><a href="#">3. 选择课程进行学习</a></li>
            </ul>
            <a href="/student/studentLogin" class="radial_but">学院登录</a>
            <a href="/organization/Login" class="radial_but">机构登录</a>
        </div>
        <div class="col-md-6">
            <img src="images/event.jpg" class="img-responsive" alt=""/>
        </div>
        <div class="clearfix"> </div>
    </div>
</div>


</body>
</html>