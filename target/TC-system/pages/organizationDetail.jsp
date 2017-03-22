<%@ page import="nju.wjw.util.CourseStudentState" %>
<%@ page import="nju.wjw.vo.CourseVO" %>
<%@ page import="nju.wjw.vo.OrganizationVO" %>
<%@ page import="java.util.List" %>
<%@ page import="nju.wjw.entity.Course" %>
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
    <title>机构详细信息</title>
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
        <h3>机构详细信息</h3>
        <p class="description">
            如下是该机构的详细信息
        </p>
        <div class="breadcrumb1">
            <ul>
                <li class="icon6"><a href="/">主页</a></li>
                <li class="icon6"><a href="/managerPlatform/managerService">经理服务平台</a></li>
                <li class="icon6"><a href="/managerPlatform/organizationList">机构列表</a></li>
                <li class="current-page">机构详细信息</li>
            </ul>
        </div>
    </div>
</div>
<!-- //banner -->

<% OrganizationVO organizationVO = (OrganizationVO)request.getAttribute("organizationVO");
    List<CourseVO> courseList = organizationVO.courseList;
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
            <img src="/images/event.jpg" class="img-responsive" alt=""/>
            <h3><%=organizationVO.name %></h3>
            <p><%=organizationVO.description==null?"该机构暂无简介":organizationVO.description %></p>

            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                <i class="fa fa-bookmark-o icon_3"></i>机构联系方式
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                        <div class="panel-body">
                            <%=organizationVO.email%>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading" role="tab">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button">
                            <i class="fa fa-rmb icon_3"></i> 课程金额结算
                        </a>
                    </h4>
                </div>
                <div class="panel-body">
                    待结算金额总计：<%= organizationVO.money %>
                    <% if(!organizationVO.money.equals("0.0")){  %>
                    <form method="post" action="/managerPlatform/checkInAccountPost">
                        <input value="<%= organizationVO.id %>" type="hidden" name="oid">
                        <input class="submit_1 btn btn-primary btn-block" type="submit" value="结算">
                    </form>
                    <% }else{ %>
                        <p>该机构暂没有需要结算金额</p>
                    <% } %>
                </div>
            </div>


            <div class="courses_box1">
                        <div class="course_list">
                            <div class="table-header clearfix">
                                <div class="id_col">课程ID</div>
                                <div class="name_col">课程名称</div>
                                <div class="startdate_col" style="width:15%;">开始日期</div>
                                <div class="enddate_col" style="width:15%;">结束日期</div>
                                <div class="price_col" style="width:20%;">课程审核状态</div>
                                <div class="check_col" style="width:15%;">学费</div>
                            </div>
                            <ul class="table-list">
                                <%
                                    if(courseList != null)
                                        for (CourseVO c: courseList) { %>
                                <li class="clearfix">
                                    <div class="id_col"><%=c.id %></div>

                                    <div class="name_col"><a href="/managerPlatform/courseDetail?id=<%=c.id%>"><%=c.name %></a></div>

                                    <div class="startdate_col" style="width:15%;"><%=c.startTime %></div>

                                    <div class="enddate_col" style="width:15%;"><%=c.endTime %></div>

                                    <%
                                        String state = "";
                                        if(c.isChecked==0)
                                            state = "等待审核中";
                                        else
                                            if(c.isPassed==1)
                                                state = "审核已通过";
                                            else
                                                state = "审核未通过";
                                    %>

                                    <div class="price_col" style="width:20%;"><%=state %></div>

                                    <div class="price_col" style="width:15%;"><%=c.price %></div>

                                </li>
                                <% } %>

                            </ul>
                        </div>

                    <div class="clearfix"> </div>
                </div>
            </div>

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