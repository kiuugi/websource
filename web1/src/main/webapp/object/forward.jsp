<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>


    <%
        // JSP 내장객체
        // 1) HttpServletRequest request
        // 2) HttpServletResponse response
        // 3) JspWriter out
        // 4) PageContext pageContext : jsp 페이지에 대한 정보를 저장하고 있는 객체
        //      1) forward()
        //      2) include("포함할 페이지 경로") : ex) 디자인 템플릿 구성 시 사용 같은 페이지에 중복되는 Navbar 같은거 

        // sendRedirect(경로); (주소)로 보내버림
        // http://localhost:8080/response/response.jsp 요청 / respnse. 응답
        // 다른 경로로 이동
        // response.sendRedirect("/basic/input.html");
        // forward("content.jsp") 에 있는 페이지가 화면에 보여짐 // forward()는 내 서버에 가지고 있는것만 보여줄 수 있음
        // 주소는 요청 주소인 상태 // include() 는 주소가 바뀜
        pageContext.forward("content.jsp"); // 못찾으면 결국 이름 알아보자
        
    %>
