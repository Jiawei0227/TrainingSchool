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
    <title>机构管理</title>
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
    <%--<link href='https://fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700' rel='stylesheet' type='text/css'>--%>
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
        <h3>机构管理平台</h3>
        <p class="description">
            欢迎来到ELITE COLLEGE精英学校。这里是机构服务平台，这欢迎使用ELITE教育。一路精彩，等你启航。
        </p>
        <div class="breadcrumb1">
            <ul>
                <li class="icon6"><a href="/">主页</a></li>
                <li class="icon6"><a href="/organization/Login">机构平台登录</a></li>
                <li class="current-page">机构服务</li>
            </ul>
        </div>
    </div>
</div>
<!-- //banner -->

<div class="services">
    <div class="container">
        <h1>机构服务列表</h1>
        <div class="service_box1">
            <div class="col-md-6">
                <div class="service_1">
                    <div class="service_1-left">
                        <span class="icon_5"><i class="fa fa-users"> </i></span>
                    </div>
                    <div class="service_1-right">
                        <h5><a href="/organization/newCourseApply">开班申请</a></h5>
                        <p>机构发起开办申请，需要向经理发送申请并进行审核 </p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="service_1">
                    <div class="service_1-left">
                        <span class="icon_5"><i class="fa fa-phone"> </i></span>
                    </div>
                    <div class="service_1-right">
                        <h5><a href="/organization/updateCourseList">班级信息修改</a></h5>
                        <p>修改班级上课信息，需要向经理发送申请并经过审核</p>
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
                        <h5><a href="/organization/courseConfirm">学员信息登记</a></h5>
                        <p>审批学员信息，成绩管理，学员管理</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="service_1">
                    <div class="service_1-left">
                        <span class="icon_5"><i class="fa fa-globe"> </i></span>
                    </div>
                    <div class="service_1-right">
                        <h5><a href="/organization/myHistory">机构统计信息查看</a></h5>
                        <p>查看本机构学生预定，退订，学员学习，财务等问题 </p>
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
