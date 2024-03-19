<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ page import="dao.TodoDao"%>
<%@ page import="dto.TodoDto"%>
<%@ page import="java.util.List"%>
<%@ include file="../include/header.jsp" %>
<%
    // DB 연동
    TodoDao dao = new TodoDao(); // 위에 import 구문들여 TodoDao 객체생성 TodoDao에 있는 메소드 사용하기위함
    List<TodoDto> list = dao.getList();
%>
<h1 class="mt-5">Todo List</h1>
    <table class="table">
  <thead>
    <tr>
      <th scope="col">번호</th>
      <th scope="col">제목</th>
      <th scope="col">작성일</th>
      <th scope="col">완료여부</th>
    </tr>
  </thead>
  <tbody>
  <% for(TodoDto dto:list){ %>
    <tr>
      <th scope="row"><%=dto.getNo()%></th>
      <td><a href="/jdbc/view/readPro.jsp?no=<%=dto.getNo()%>"><%=dto.getTitle()%></a></td>
      <td><%=dto.getCreatedAt()%></td>
      <td>
          <%
            out.print("<input type='checkbox' class='form-check-input' id='completed' name='completed' value='true' ");
            if(dto.isCompleted()){
              out.print("checked>");
            }else{
              out.print(">");
            }
          %>
      </td>
    </tr>
    <% } %>
  </tbody>
</table>
<%@ include file="../include/footer.jsp" %>
