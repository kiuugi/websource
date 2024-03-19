<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ page import="dao.TodoDao"%>
<%@ page import="dto.TodoDto"%>
<%
    // 한글처리
    request.setCharacterEncoding("utf-8");
    // 번호, 완료헀는지, 상세내용 가져오기
    // value 가 없는 경우 checkbox, radio 의 경우에는 on 값을 가지고 오게 됨
    String no = request.getParameter("no");
    String completed = request.getParameter("completed");
    String description = request.getParameter("description");

    // DB작업
    
    // DB 연동
    TodoDao dao = new TodoDao(); // 위에 import 구문들여 TodoDao 객체생성 TodoDao에 있는 메소드 사용하기위함
    TodoDto dto = new TodoDto();
    dto.setCompleted(Boolean.parseBoolean(completed));
    dto.setDescription(description);
    dto.setNo(Integer.parseInt(no));

    int result = dao.update(dto);
   

    // 화면이동
    response.sendRedirect("list.jsp");
   
%>