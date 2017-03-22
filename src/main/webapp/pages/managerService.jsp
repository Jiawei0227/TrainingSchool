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
    <title>经理服务</title>
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
        <h3>经理服务平台</h3>
        <p class="description">
            欢迎来到ELITE COLLEGE精英学校。这里是经理服务平台，这欢迎使用ELITE教育。一路精彩，等你启航。
        </p>
        <div class="breadcrumb1">
            <ul>
                <li class="icon6"><a href="/">主页</a></li>
                <li class="icon6"><a href="/managerPlatform/Login">经理登录</a></li>
                <li class="current-page">经理服务</li>
            </ul>
        </div>
    </div>
</div>
<!-- //banner -->

<div class="services">
    <div class="container">
        <h1>经理服务列表</h1>
        <div class="service_box1">
            <div class="col-md-6">
                <div class="service_1">
                    <div class="service_1-left">
                        <span class="icon_5"><i class="fa fa-users"> </i></span>
                    </div>
                    <div class="service_1-right">
                        <h5><a href="/managerPlatform/CheckApply">审批机构开班/修改</a></h5>
                        <p>机构发起开班申请或者修改班级信息申请，需要向经理发送申请并进行审核 </p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="service_1">
                    <div class="service_1-left">
                        <span class="icon_5"><i class="fa fa-phone"> </i></span>
                    </div>
                    <div class="service_1-right">
                        <h5><a href="/managerPlatform/organizationList">机构管理</a></h5>
                        <p>将会员卡支付结算给各机构，以及机构、课程详细信息查看</p>
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
                        <h5><a href="/managerPlatform/studentList">学员信息查看</a></h5>
                        <p>查看学员信息列表,以及学员的选课\成绩等情况</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="service_1">
                    <div class="service_1-left">
                        <span class="icon_5"><i class="fa fa-globe"> </i></span>
                    </div>
                    <div class="service_1-right">
                        <h5><a href="/managerPlatform/statistic">TR统计信息查看</a></h5>
                        <p>查看精英教育统计情况，包括ELITE的资金流动情况等 </p>
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
