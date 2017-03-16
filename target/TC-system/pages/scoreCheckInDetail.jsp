<%--
  Created by IntelliJ IDEA.
  User: wangjiawei
  Date: 2017/3/14
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="nju.wjw.vo.CourseVO" %>
<%@ page import="java.util.List" %>
<%@ page import="nju.wjw.entity.Course" %>
<%@ page import="nju.wjw.vo.StudentCourseVO" %>
<%@ page import="nju.wjw.vo.StudentScoreVO" %><%--
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
    <title>课程成绩登记</title>
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
        <h3>课程成绩等级</h3>
        <p class="description">
            如下是该课程下已选的同学的成绩列表，请选择成绩登记与修改
        </p>
        <div class="breadcrumb1">
            <ul>
                <li class="icon6"><a href="/">主页</a></li>
                <li class="icon6"><a href="/organization/organizationService">机构平台管理</a></li>
                <li class="icon6"><a href="/organization/courseConfirm">学生信息登记</a></li>
                <li class="current-page">成绩等级</li>
            </ul>
        </div>
    </div>
</div>
<!-- //banner -->

<% List<StudentScoreVO> scoreList = (List<StudentScoreVO>) request.getAttribute("scoreList");
%>
<div class="courses_box1">
    <div class="container">
        <div class="col-md-12">
            <div class="course_list">
                <div class="table-header clearfix">
                    <div class="id_col">学生id</div>
                    <div class="name_col">学生姓名</div>
                    <div class="price_col"  style="width: 20%;">学生成绩等级</div>
                    <div class="price_col" style="width: 40%;">成绩备注信息</div>
                </div>
                <ul class="table-list">
                    <form>
                    <%
                        if(scoreList != null)
                            for (int i=0;i<scoreList.size();i++) {
                                StudentScoreVO c = scoreList.get(i);
                    %>
                    <li class="clearfix">
                        <div class="id_col"><%=c.sid %></div>

                        <div class="name_col"><%=c.name %></div>

                        <div class="price_col"  style="width: 20%;"><input style="color:black;" type="text" value="<%=c.score %>" name="<%="id"+c.sid%>"></div>

                        <div class="price_col"  style="width: 40%;"><input style="color:black;" type="text" value="<%=c.back %>" name="<%="back"+c.sid%>"></div>

                    </li>
                    <% } %>
                    </form>
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