<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ page import="dao.TodoDao"%>
<%@ page import="dto.TodoDto"%>
<%
    // 한글처리
    request.setCharacterEncoding("utf-8");
    // 사용자가 입력한 todo 가져오기
    String title = request.getParameter("title");
    String description = request.getParameter("description");

    // DB작업
    
    // DB 연동
    TodoDao dao = new TodoDao(); // 위에 import 구문들여 TodoDao 객체생성 TodoDao에 있는 메소드 사용하기위함

    TodoDto inserDto = new TodoDto();

    inserDto.setTitle(title);
    inserDto.setDescription(description);

    int result = dao.Insert(inserDto);


    // 화면이동(list) // 화면이동할때는 가져갈 정보가 있는지 판단 후 이동 // 지금은 가져갈 거 없다
    response.sendRedirect("list.jsp");

%>