<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="nju.wjw.vo.HistoryVO" %>
<%@ page import="java.util.List" %>
<% List<HistoryVO> historyVO = (List<HistoryVO>) request.getAttribute("historyVO");
%>
<div class="courses_box1">
    <div class="container">
        <div class="col-md-12">
            <div class="course_list">
                <div class="table-header clearfix">
                    <div class="id_col">编号</div>
                    <div class="name_col">执行时间</div>
                    <div class="startdate_col" style="width: 70%;">执行动作</div>

                </div>
                <ul class="table-list">
                    <%
                        if(historyVO != null)
                            for (int i=0;i<historyVO.size();i++) {
                                HistoryVO c = historyVO.get(i);
                    %>
                    <li class="clearfix">
                        <div class="id_col"><%=i %></div>

                        <div class="name_col"><%=c.createdAt %></div>

                        <div class="startdate_col" style="width: 70%;"><%=c.action %></div>


                    </li>
                    <% } %>

                </ul>
            </div>
        </div>
        <div class="clearfix"> </div>
    </div>
</div>