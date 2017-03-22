<%@ page import="nju.wjw.vo.CourseDetailViewVO" %>
<%@ page import="nju.wjw.vo.CourseVO" %>
<%@ page import="nju.wjw.vo.OrganizationVO" %>
<%@ page import="nju.wjw.util.CourseStudentState" %>
<%@ page import="nju.wjw.util.StudentLevel" %>
<%--
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
    <title>课程详细信息</title>
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
        <h3>课程</h3>
        <p class="description">
            如下是该课程的详细信息
        </p>
        <div class="breadcrumb1">
            <ul>
                <li class="icon6"><a href="/">主页</a></li>
                <li class="icon6"><a href="/student/studentService">学生服务平台</a></li>
                <li class="icon6"><a href="/student/courseList">课程列表</a></li>
                <li class="current-page">课程详细信息</li>
            </ul>
        </div>
    </div>
</div>
<!-- //banner -->

<% CourseDetailViewVO courseDetailViewVO = (CourseDetailViewVO)request.getAttribute("courseDetailViewVO");
    CourseVO course = courseDetailViewVO.courseVO;
    OrganizationVO organizationVO = courseDetailViewVO.organizationVO;
    StudentLevel studentLevel= courseDetailViewVO.studentLevel;
    double pp = 0.0;
    switch (studentLevel){
        case STAR:pp=1;break;
        case MONTH:pp=0.9;break;
        case SUN: pp=0.8;break;
        case DIAMOND: pp =0.7;break;
    }
%>

<div class="courses_box1">
    <div class="container">
        <div class="col-md-3">
            <div class="courses_box1-left">
                <form>
                    <div class="select-block1">
                        <select>
                            <option value="">语言</option>
                        </select>
                    </div>
                    <!-- select-block -->
                    <div class="select-block1">
                        <select>
                            <option value="">时段</option>
                        </select>
                    </div>
                    <!-- select-block -->
                    <div class="select-block1">
                        <select>
                            <option value="">难度</option>
                        </select>
                    </div>
                    <!-- select-block -->
                    <div class="select-block1">
                        <select>
                            <option value="">地点</option>
                        </select>
                    </div>
                    <!-- select-block -->
                    <input type="submit" value="search" class="course-submit">
                </form>
            </div>

            <div class="social-widget">
                <h2>Connect with us</h2>
                <p><a href="#" class="fa fa-envelope-o"></a>Email: <%=organizationVO.email%></p>
                <br />
                <ul class="courses_social">
                    <li class="facebook-icon">
                        <div>
                            <a href="#" class="fa fa-renren"></a>
                        </div>
                    </li>
                    <li class="twitter-icon">
                        <div>
                            <a href="#" class="fa fa-skype"></a>
                        </div>
                    </li>
                    <li class="gplus-icon">
                        <div>
                            <a href="#" class="fa fa-weibo"></a>
                        </div>
                    </li>
                    <div class="clearfix"> </div>
                </ul>
            </div>



        </div>
        <div class="col-md-9 detail">
            <img src="/images/course.jpg" class="img-responsive" alt=""/>
            <h3><%=course.name %></h3>
            <p><%=course.description %></p>
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                <i class="fa fa-bookmark-o icon_3"></i>课程名师介绍
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                        <div class="panel-body">
                            <%=course.teacher%>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                <i class="fa fa-clock-o icon_3"></i> 课程上课时间
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo" aria-expanded="false" style="height: 0px;">
                        <div class="panel-body">
                            起始日: <%=course.startTime%><br />
                            终止日: <%=course.endTime%>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                <i class="fa fa-comments-o icon_3"></i> 课程价格
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree" aria-expanded="false" style="height: 0px;">
                        <div class="panel-body">
                            课程价格： <%=pp*Double.parseDouble(course.price) %> RMB  (会员折后价)
                        </div>
                    </div>
                </div>

            </div>
            <div class="author-box">
                <div class="author-box-left"><img src="/images/t13.png" class="img-responsive" alt=""/></div>
                <div class="author-box-right">
                    <h4>该课程由 <a href="#"><%=organizationVO.name%></a> 机构提供</h4>
                    <p><%=organizationVO.description==null?"该机构暂无介绍":organizationVO.description%></p>
                </div>
                <div class="clearfix"> </div>
            </div>

            <form class="comment-form" style="text-align: center" method="post" action="/student/addCourse">
                <input value="<%=course.id%>" type="hidden" name="cid"/>
                <% if(courseDetailViewVO.isPastDue){ %>
                <h4>该课程已经过了最后报名期限时间，不能再报名了，请关注本网站其他课程</h4>
                <% }else{
                    if(courseDetailViewVO.state.equals(CourseStudentState.PASSED)){ %>
                        <h4>您已选择了该课程，请依照我们的通知在规定时间完成课程并考试</h4>
                        <input name="submit" type="submit" id="submit" class="submit_1 btn btn-primary btn-block" value="申请退出课程">
                 <%   }else if(courseDetailViewVO.state.equals(CourseStudentState.WAITING)) { %>
                        <h4>您已提交预定申请，正在审核中，请等待审核</h4>
                 <%   }else if(courseDetailViewVO.state.equals(CourseStudentState.FAILURED)){ %>
                        <h4>您的课程预定审核没有通过，请检查账户余额或尝试联系机构了解详情</h4>
                        <input name="submit" type="submit" id="submit" class="submit_1 btn btn-primary btn-block" value="重新申请加入课程">
                 <%   }else if(courseDetailViewVO.state.equals(CourseStudentState.NOTJOINED)){ %>
                        <h4>您尚未加入该课程</h4>
                        <input name="submit" type="submit" id="submit" class="submit_1 btn btn-primary btn-block" value="申请加入课程">
                 <%   }

                }%>

            </form>

        <div class="clearfix"> </div>
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