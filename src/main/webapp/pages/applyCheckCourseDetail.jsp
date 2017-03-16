<%@ page import="nju.wjw.vo.CourseVO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: wangjiawei
  Date: 2017/3/12
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>课程申请审核</title>
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
<!-- banner -->
<div class="courses_banner">
    <div class="container">
        <h3>经理审批</h3>
        <p class="description">
            如下是该课程的详细信息
        </p>
        <div class="breadcrumb1">
            <ul>
                <li class="icon6"><a href="/">主页</a></li>
                <li class="icon6"><a href="/managerPlatform/managerService">经理管理平台</a></li>
                <li class="icon6"><a href="/managerPlatform/CheckApply">审核课程</a></li>
                <li class="current-page">课程详细信息</li>
            </ul>
        </div>
    </div>
</div>
<!-- //banner -->

<% CourseVO course = (CourseVO)request.getAttribute("courseVO"); %>

<div class="admission">
    <div class="container">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
        <div class="terms" style="text-align:center">
            <h1>课程申请详细信息</h1>
            <br />
            <div class="terms_top">
                <h4>课程ID</h4>
                <p><%=course.id %></p>

                <h4>课程名称</h4>
                <p><strong><%=course.name %></strong></p>

                <h4>课程描述</h4>
                <p><%=course.description==null?"暂无":course.description %></p>

                <h4>课程教师</h4>
                <p><%=course.teacher%></p>

                <h4>课程费用</h4>
                <p><%=course.price%> 元</p>

                <h4>课程起始日期</h4>
                <p><%=course.startTime%></p>

                <h4>课程终止日期</h4>
                <p><%=course.endTime%></p>
            <form method="post" action="/managerPlatform/courseCheck/success">
                <input type="hidden" value="<%=course.id %>" name="id" />
                <input type="submit" class="shortcode_but medium" style="color:#ffffff; background-color:#d6724f; " value="通过审核"/>
            </form>
                <form method="post" action="/managerPlatform/courseCheck/failure">
                    <input type="hidden" value="<%=course.id %>" name="id" />
                    <input type="submit" class="shortcode_but medium" style="color:#ffffff; background-color:#d6724f; " value="不通过审核"/>
                </form>

            </div>
        </div>
        </div>
    </div>
</div>


<!-- FlexSlider -->
<link href="/css/flexslider.css" rel='stylesheet' type='text/css' />
<script defer src="/js/jquery.flexslider.js"></script>
<script type="text/javascript">
    $(function(){
        SyntaxHighlighter.all();
    });
    $(window).load(function(){
        $('.flexslider').flexslider({
            animation: "slide",
            start: function(slider){
                $('body').removeClass('loading');
            }
        });
    });


</script>
<!-- FlexSlider -->
</body>
</html>