<%@ page import="nju.wjw.vo.StudentVO" %><%--
  Created by IntelliJ IDEA.
  User: wangjiawei
  Date: 2017/2/13
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Elite College</a>
        </div>
        <!--/.navbar-header-->
        <div class="navbar-collapse collapse" id="bs-example-navbar-collapse-1" style="height: 1px;">
            <ul class="nav navbar-nav">

                <li class="dropdown">
                    <%
                        if(session.getAttribute("studentVO") == null) {
                    %>
                    <a href="/studentLogin"><i class="fa fa-user"></i><span>登录</span></a>
                    <%
                        }else {
                            StudentVO st = (StudentVO) session.getAttribute("studentVO");

                    %>
                    <a href="#"><i class="fa fa-user"></i><span><%=st.name%></span></a>
                </li>
                <li class="dropdown">
                    <a href="/student/studentLogout"><i class="fa fa-times"></i><span>登出</span></a>
                    <%}%>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-list"></i><span>课程</span></a>
                    <ul class="dropdown-menu">
                        <li><a href="courses.html">Courses Categories</a></li>
                        <li><a href="courses.html">Courses list</a></li>
                        <li><a href="courses.html">Courses detail</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-calendar"></i><span>事件</span></a>
                    <ul class="dropdown-menu">
                        <li><a href="events.html">Event1</a></li>
                        <li><a href="events.html">Event2</a></li>
                        <li><a href="events.html">Event3</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-search"></i><span>查询</span></a>
                    <ul class="dropdown-menu search-form">
                        <form>
                            <input type="text" class="search-text" name="s" placeholder="Search...">
                            <button type="submit" class="search-submit"><i class="fa fa-search"></i></button>
                        </form>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="clearfix"> </div>
    </div>
    <!--/.navbar-collapse-->
</nav>
