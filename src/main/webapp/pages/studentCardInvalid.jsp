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
    <title>StudentCardInvalid</title>
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
        <h3>学员卡资格</h3>
        <p class="description">
            会员可自行通知系统取消或恢复会员资格。
        </p>
        <div class="breadcrumb1">
            <ul>
                <li class="icon6"><a href="/">主页</a></li>
                <li class="icon6"><a href="/student/studentCardService">学员卡服务</a></li>
                <li class="current-page">学员卡资格取消</li>
            </ul>
        </div>
    </div>
</div>
<!-- //banner -->
<div class="courses_box1">
    <div class="container">
        <% if((int)request.getAttribute("MemberValidity") == 1){ %>
        <form class="login" action="/student/studentCard-Invalid" method="post">
            <div class="form-group">
                <h3 class="shortcode" style="font-size: 36px;font-weight: bold;">会员资格:<%=((int)request.getAttribute("MemberValidity") == 1)?"拥有资格":"暂无会员资格" %></h3>

                <h4 class="shortcode" style="font-size: 31px;font-weight: bold;">会员资格有效期:<%= request.getAttribute("Time") %></h4>
                <input type="submit" class="btn btn-primary btn-lg1 btn-block" name="submit" value="取消会员资格">

            </div>
        </form>
        <% }else{ %>
        <form class="login" action="/student/studentCard-Valid" method="post">
            <div class="form-group">
                <h3 class="shortcode" style="font-size: 36px;font-weight: bold;">会员资格:<%=((int)request.getAttribute("MemberValidity") == 1)?"拥有资格":"暂无会员资格" %></h3>

                <h4 class="shortcode" style="font-size: 31px;font-weight: bold;">会员资格有效期:<%= request.getAttribute("Time") %></h4>
                <input type="submit" class="btn btn-primary btn-lg1 btn-block" name="submit" value="恢复会员资格">

            </div>
        </form>
        <%}%>

    </div>
</div>s

</div>
</body>
</html>