<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%
    // form (action="") => servlet => jsp
    // ScopeServlet(info2.jsp)에서 할 수 있는 일들을 forward.jsp에서 할 수 있게됨
    String id = request.getParameter("id");
    
%>
<h3>forward.jsp</h3>
<h3>id : <%=id%></h3>