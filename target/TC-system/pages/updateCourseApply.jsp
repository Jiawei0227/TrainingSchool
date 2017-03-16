<%@ page import="nju.wjw.vo.CourseVO" %><%--
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
    <title>修改班级申请</title>
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
                <li class="icon6"><a href="/organization/organizationService">机构服务</a></li>
                <li class="current-page">修改申请</li>
            </ul>
        </div>
    </div>
</div>
<% CourseVO courseVO = (CourseVO) request.getAttribute("courseVO"); %>
<!-- //banner -->
<div class="courses_box1">
    <div class="container">
        <form class="login" action="/organization/updateCourseApply" method="post">
            <p class="lead">申请修改班级</p>
            <input type="hidden" value="<%=courseVO.id%>" name="id">
            <div class="form-group">
                <input type="text" autocomplete="new-password" class="required form-control" placeholder="课程名 *" name="name" value="<%= courseVO.name %>">
            </div>
            <div class="form-group">
                <textarea aria-required="true" id="comment" class="form-control" placeholder="课程描述 *" name="description"><%= courseVO.description %></textarea>
            </div>
            <div class="form-group">
                <input type="text" autocomplete="new-password" class="required form-control" placeholder="" name="teacher" value="<%= courseVO.teacher %>">
            </div>

            <div class="input-append date form_datetime form-group">
                <input type="text" class="required form-control" value="<%= courseVO.startTime %>" name="startTime" readonly>
                <span class="add-on"><i class="icon-th"></i></span>
            </div>

            <div class="input-append date form_datetime form-group">
                <input type="text" class="required form-control" value="<%= courseVO.endTime %>" name="endTime" readonly>
                <span class="add-on"><i class="icon-th"></i></span>
            </div>

            <div class="form-group">
                <input type="text" autocomplete="new-password" class="required form-control" name="price" value="<%= courseVO.price %>">
            </div>

            <div class="form-group">
                <input type="submit" class="btn btn-primary btn-lg1 btn-block" name="submit" value="提交修改班级申请">
            </div>
        </form>
    </div>
</div>

</div>
<link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" media="all" />
<script src="/js/bootstrap-datetimepicker.js"></script>
<script>
    $(".form_datetime").datetimepicker({
        format: "yyyy-mm-dd",
        todayBtn: true
    });
    $.datetimepicker.setLocale('ch');

</script>
</body>
</html>