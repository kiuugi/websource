<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/include/header.jsp" %>
<%
    // 1. list - 메뉴 todo List 클릭 => Servlet 이동 (db 작업, 작업결과를 scope에 담고 이동)=> list.jsp 에서 결과 보여주기
    // 2. read - 제목클릭 => Servlet 이동 (no 가져오기, db 작업, 작업결과를 scope 에 담고 이동) => read.jsp 결과 보여주기

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
  <%-- items 에 setAttribute한 변수명 --%>
  <c:forEach var="dto" items="${list}">
    <tr>
      <th scope="row">${dto.no}</th>
      <td><a href='<c:url value="/read.do?no=${dto.no}"/>' class="text-decoration-none text-reset">${dto.title}</a></td>
      <td>${dto.createdAt}</td>
      <td>
          <input type="checkbox" class="form-check-input" id="completed" name="completed" value="true" disabled <c:out value='${dto.completed ? "checked" : "" }' />>
      </td>
    </tr>
 </c:forEach>
  </tbody>
</table>
<%@ include file="/include/footer.jsp" %>
