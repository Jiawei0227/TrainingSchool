<%--
  Created by IntelliJ IDEA.
  User: wangjiawei
  Date: 2017/3/14
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
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
    <title>课程列表</title>
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
        <h3>学生课程列表</h3>
        <p class="description">
            如下是可由学员选择的课程列表
        </p>
        <div class="breadcrumb1">
            <ul>
                <li class="icon6"><a href="/">主页</a></li>
                <li class="icon6"><a href="/student/studentService">学员管理平台</a></li>
                <li class="current-page">课程列表</li>
            </ul>
        </div>
    </div>
</div>
<!-- //banner -->

<% List<CourseVO> courseList = (List<CourseVO>) request.getAttribute("courseList"); %>
<div class="courses_box1">
    <div class="container">
        <div class="col-md-12">
            <div class="course_list">
                <div class="table-header clearfix">
                    <div class="id_col">课程ID</div>
                    <div class="name_col">课程名称</div>
                    <div class="startdate_col">开始日期</div>
                    <div class="enddate_col">结束日期</div>
                    <div class="price_col">课程价格</div>
                    <div class="check_col">任课教师</div>
                </div>
                <ul class="table-list">
                    <%
                        if(courseList != null)
                            for (CourseVO c: courseList) { %>
                    <li class="clearfix">
                        <div class="id_col"><%=c.id %></div>

                        <div class="name_col"><a href="/student/courseDetail?id=<%=c.id%>"><%=c.name %></a></div>

                        <div class="startdate_col"><%=c.startTime %></div>

                        <div class="enddate_col"><%=c.endTime %></div>

                        <div class="price_col"><%=c.price %></div>

                        <div class="price_col"><%=c.teacher %></div>

                    </li>
                    <% } %>

                </ul>
            </div>
        </div>
        <div class="clearfix"> </div>
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