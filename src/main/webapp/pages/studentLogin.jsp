<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Login</title>
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
    <link href="/css/font-awesome.css" rel="stylesheet">
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

<!-- banner -->
<div class="courses_banner">
    <div class="container">
        <h3>学生登录平台</h3>
        <p class="description">
            欢迎来到ELITE COLLEGE精英学校。这里是学生登录平台，这里有最权威的教育精英课程。一路精彩，等你启航。
        </p>
        <div class="breadcrumb1">
            <ul>
                <li class="icon6"><a href="/">主页</a></li>
                <li class="current-page">学生平台</li>
            </ul>
        </div>
    </div>
</div>
<!-- //banner -->
<div class="courses_box1">
    <div class="container">
        <form class="login" method="post" action="/student-Login">
            <p class="lead">欢迎回来！</p>
            <div class="form-group">
                <input autocomplete="new-password" type="text" name="username" class="required form-control" placeholder="学员卡号">
            </div>
            <div class="form-group">
                <input autocomplete="new-password" type="password" class="password required form-control" placeholder="密码" name="password">
            </div>
            <div class="form-group" method="post" action="/student/student-Login">
                <input type="checkbox" name="remember" value="true"> 记住我
                <input type="submit" class="btn btn-primary btn-lg1 btn-block" name="submit" value="登录">
            </div>
            <p>还没有账号? <a href="/studentRegister" title="Sign Up">注册</a></p>
        </form>
    </div>
</div>


</body>
</html>