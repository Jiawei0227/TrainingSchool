<%@ page import="nju.wjw.vo.StudentVO" %><%--
  Created by IntelliJ IDEA.
  User: wangjiawei
  Date: 2017/2/13
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>StudentActive</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Learn Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
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


<div class="courses_banner">
    <div class="container">
        <h3>学员注册</h3>
        <p class="description">
            1000元充值即可激活学员卡，享受海量课程！
        </p>
        <div class="breadcrumb1">
            <ul>
                <li class="icon6"><a href="/">主页</a></li>
                <li class="icon6"><a href="/student/studentCardService">学员卡服务</a></li>
                <li class="current-page">学员卡管理</li>
            </ul>
        </div>
    </div>
</div>
<!-- //banner -->
<div class="courses_box1">
    <div class="container">
        <form class="login" action="/student/student-active" method="post">
            <% StudentVO st = (StudentVO)session.getAttribute("studentVO"); %>
            <h3 class="shortcode" style="font-size: 36px;font-weight: bold;">账户余额:<%=st.balance %></h3>
            <p class="lead">输入缴费的银行账户密码与金额</p>
            <div class="form-group">
                <input type="text" autocomplete="new-password" class="required form-control" placeholder="账户 *" name="account" value="">
            </div>
            <div class="form-group">
                <input type="password" autocomplete="new-password" class="required form-control" placeholder="密码 *" name="password" value="">
            </div>
            <div class="form-group">
                <input type="text" autocomplete="new-password" class="required form-control" placeholder="金额 *" name="money" value="">
            </div>

            <div class="form-group">
                <input type="submit" class="btn btn-primary btn-lg1 btn-block" name="submit" value="缴费">
            </div>
        </form>
    </div>
</div>

</div>
</body>
</html>