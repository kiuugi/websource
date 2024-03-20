<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="dao.TodoDao"%>
<%@ page import="dto.TodoDto"%>
<%
    // 한글처리
    request.setCharacterEncoding("utf-8");
 
    // no 가져오기 // modify.jsp 에서 a태그로 날라온 정보 
    String no = request.getParameter("no");
   

    // DB작업
    
    // DB 연동
    TodoDao dao = new TodoDao(); // 위에 import 구문들여 TodoDao 객체생성 TodoDao에 있는 메소드 사용하기위함

    int result = dao.delete(no);
   

    // 화면이동
    response.sendRedirect("list.jsp");
   
%>