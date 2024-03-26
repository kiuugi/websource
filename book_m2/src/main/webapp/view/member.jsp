<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/include/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h3 class="border-bottom mb-3">고객관리</h3>
<table class="table table-bordered">
  <thead>
    <tr class="table-success">
      <th scope="col" class="text-center">userid</th>
      <th scope="col" class="text-center">name</th>
      <th scope="col" class="text-center">email</th>
    </tr>
  </thead>
  <tbody>
  <%-- Attribute 이름설정한걸로 부름. --%>
    <c:forEach var="dto" items="${memberList}">
        <tr>
        <th scope="row" class="text-center">${dto.userid}</th>
        <td class="text-center">${dto.name}</td>
        <td class="text-end">
            <fmt:formatNumber type="number" maxFractionDigits="3" value="${dto.email}"/>
        </td>
        </tr>
    </c:forEach>
  </tbody>
</table>
<%@ include file="/include/section.jsp" %>
<%@ include file="/include/footer.jsp" %>