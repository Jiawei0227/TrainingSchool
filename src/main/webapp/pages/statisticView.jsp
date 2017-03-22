<%@ page import="nju.wjw.vo.StatisticsVO" %><%--
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
        <h3>TR统计信息查看</h3>
        <p class="description">
            欢迎来到ELITE COLLEGE精英学校。这里是ELITE教育的统计信息以及资金汇总
        </p>
        <div class="breadcrumb1">
            <ul>
                <li class="icon6"><a href="/">主页</a></li>
                <li class="icon6"><a href="/managerPlatform/managerService">经理服务平台</a></li>
                <li class="current-page">TR统计信息查看</li>
            </ul>
        </div>
    </div>
</div>
<!-- //banner -->
<% StatisticsVO statisticsVO = (StatisticsVO) request.getAttribute("statisticsVO"); %>

<div class="courses_box1">
    <div class="container">
        <div class="col-md-6 about_left">
            <h1>Elite精英统计信息</h1>
            <ul class="about_links">
                <li>总机构数量：<%=statisticsVO.organ_Number%></li>
                <li>星级学员数量：<%=statisticsVO.star_stu_Number%></li>
                <li>月亮级学员数量：<%=statisticsVO.month_stu_Number%></li>
                <li>太阳级学员数量：<%=statisticsVO.sun_stu_Number%></li>
                <li>钻石学员数量：<%=statisticsVO.diamond_stu_Number%></li>
                <li>总课程数量：<%=statisticsVO.course_Number%></li>
                <li>总资产数量：<%=statisticsVO.account_Balance%>元</li>
            </ul>
        </div>
        <div class="col-md-6">
            <img src="/images/fc5.jpg" class="img-responsive" alt=""/>
        </div>
    </div>
</div>

<jsp:include page="history.jsp"/>



</body>
</html>
