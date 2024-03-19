<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ page import="dao.TodoDao"%>
<%@ page import="dto.TodoDto"%>
<%
    // 한글처리
    request.setCharacterEncoding("utf-8");
    // 제목 클릭 시 no 가져오기
    String no = request.getParameter("no");

    // DB작업
    
    // DB 연동
    TodoDao dao = new TodoDao(); // 위에 import 구문들여 TodoDao 객체생성 TodoDao에 있는 메소드 사용하기위함
    TodoDto todo = dao.getRow(no); // DB에서 온 값을 저장할 공간 TodoDto

    // todo 를 read.jsp에 보여주기
    request.setAttribute("todo", todo); // scope : request, session 중에 하나를 씀


    // 화면이동(list) // 화면이동할때는 가져갈 정보가 있는지 판단 후 이동 // 지금은 가져갈 거 없다
    pageContext.forward("modify.jsp"); // request.set~() 이라면 forward()를 씀

    // no값이 필요하긴 하지만 다음 화면에서 한번만 필요하니까 session보다는 request를 쓰고 request를 썼으니 forward를 씀. 
%>