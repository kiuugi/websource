<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%-- jsp 주석
     
     자바 코드는 <%  %> 안에 작성(위치는 상관없음)
     자바 코드 화면 출력 <%= %>
 --%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

<%
    //  for(int i = 1; i < 11; i++) {
    //     out.print(i);
    //  } 

    // JSP 내장객체
    // 1) HttpServletRequest request
    // 2) HttpServletResponse response
    // 3) JspWriter out

    request.setCharacterEncoding("utf-8");

    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String[] dogs = request.getParameterValues("dog");
    // 응답할 페이지 설정은 위쪽에 jsp 설정에 있음

 %>
    <ul>
        <li>id : <%=id%></li>
        <li>name : <%=name%></li>
        <li>name : <% out.print(name); %></li>
        <%
        for (String dog : dogs) {
            out.print("<li>dog : " + dog + "</li>");
        }
        %>
    </ul>    
</body>
</html>
