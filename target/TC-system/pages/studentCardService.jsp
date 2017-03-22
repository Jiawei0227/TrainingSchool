<%--
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
    <title>Register</title>
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
    <link rel="stylesheet" href="/css/jquery.countdown.css" />

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
        <h3>学员卡管理平台</h3>
        <p class="description">
            欢迎来到ELITE COLLEGE精英学校。这里是学员卡激活与暂停管理，这欢迎使用ELITE教育。一路精彩，等你启航。
        </p>
        <div class="breadcrumb1">
            <ul>
                <li class="icon6"><a href="/">主页</a></li>
                <li class="icon6"><a href="/student/studentLogin">学生平台登录</a></li>
                <li class="current-page">学员卡管理</li>
            </ul>
        </div>
    </div>
</div>
<!-- //banner -->

<div class="services">
    <div class="container">
        <h1>学员卡激活与管理</h1>
        <div class="service_box1">
            <div class="col-md-6">
                <div class="service_1">
                    <div class="service_1-left">
                        <span class="icon_5"><i class="fa fa-users"> </i></span>
                    </div>
                    <div class="service_1-right">
                        <h5><a href="/student/studentCardService/studentActive">学员卡激活</a></h5>
                        <p>向学员卡中充值1000元以上即可激活，有效期一年. </p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="service_1">
                    <div class="service_1-left">
                        <span class="icon_5"><i class="fa fa-phone"> </i></span>
                    </div>
                    <div class="service_1-right">
                        <h5><a href="/student/studentCardValid">会员卡恢复/资格取消</a></h5>
                        <p>有效期一年，到期后卡上费用不足将暂停其记录；一旦支付，则恢复，会员记录可用；暂停1年后未支付，会员记录停止.</p>
                    </div>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="service_box1">
            <div class="col-md-6">
                <div class="service_1">
                    <div class="service_1-left">
                        <span class="icon_5"><i class="fa fa-star"> </i></span>
                    </div>
                    <div class="service_1-right">
                        <h5><a href="/student/updatePersonalInfo">会员卡信息修改</a></h5>
                        <p>会员可以修改会员卡以及自己的个人信息. </p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="service_1">
                    <div class="service_1-left">
                        <span class="icon_5"><i class="fa fa-globe"> </i></span>
                    </div>
                    <div class="service_1-right">
                        <h5><a href="/student/points">会员卡级别查询</a></h5>
                        <p>根据消费金额将享受不同等级的会员卡级别. </p>
                    </div>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>


</div>
</body>
</html>
