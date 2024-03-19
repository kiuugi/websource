<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ include file="../include/header.jsp" %>
<%@ page import="dto.TodoDto"%>

<%
  // TodoDto todo = (TodoDto)request.getAttribute("todo"); // object 형태로 넘어오니까 TodoDto로 타입변환
  // EL로 쓰면 이걸 안써도 됨.
%>
<h1 class="mt-5">Todo Modify</h1>
<form action="updatePro.jsp" method="post">
  <div class="mb-3">
    <label label for="title" class="form-label">title</label>
    <input type="text" class="form-control" id="title" placeholder="title" name="title" value="${todo.title}">
  </div>
  <div class="mb-3">
    <label label for="createdAt" class="form-label">작성일createdAt</label>
    <input type="text" class="form-control" id="createdAt" placeholder="createdAt" name="createdAt" value="${todo.createdAt}" >
  </div>
  <div class="mb-3">
    <label label for="completed" class="form-check-label">completed</label>
    <input type="checkbox" class="form-check-input" id="completed" name="completed" value="true" <c:out value='${todo.completed ? "checked" : "" }' />>
  </div>
  <div class="mb-3">
    <label for="description" class="form-label">description</label>
    <textarea class="form-control" id="description" rows="3" name="description" >${todo.description}</textarea>
  </div>
  <div>
      <button class="btn btn-primary" type="submit">확인</button>
      <a class="btn btn-success" href='<c:url value="/view/list.jsp"/>'>목록</a>
  </div>
  <input type="hidden" name="no" value="${todo.no}">
</form>
<%@ include file="../include/footer.jsp" %>