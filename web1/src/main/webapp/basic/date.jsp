<%@ page import="java.time.LocalTime" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>현재 시간</h1>
    <%-- 1. 스크립트 2. 자바 --%>
    <%
        // html 쓸거 없으면 !(html) 안만들어도 됨

        Date date = new Date(); // java Import가 안들어옴
        out.print(date+"<br>");

        LocalTime currTime = LocalTime.now();
        out.print(currTime);
    %>
</body>
</html>