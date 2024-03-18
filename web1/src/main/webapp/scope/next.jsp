<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%
    // info.jsp a 태그가 보낸 id, name, aga 가져오기
    String id = (String)request.getParameter("id");
    String name = (String)request.getParameter("name");
    String age = (String)request.getParameter("age");

    // String id = (String)session.getAttribute("id");
    // String name = (String)session.getAttribute("name");
    // String age = (String)session.getAttribute("age");



%>
<h3>next.jsp</h3>
<%-- null 이 뜸 --%>
<h3>id : <%=id%></h3>
<h3>name : <%=name%></h3>
<h3>age : <%=age%></h3>
<h3>
    <a href="next2.jsp">다음 페이지</a>
</h3>