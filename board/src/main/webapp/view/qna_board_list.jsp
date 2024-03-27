<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/include/header.jsp"%>
<!-- Main content -->
<section class="content">
	<div class="box box-primary">
		<div class="box-header">
			<h3 class="box-title">List Board</h3>
		</div>
		<div class="row justify-content-between">
			<div class="col-md-4 mb-3">
				<a href="<c:url value='/view/qna_board_write.jsp'/>" class="btn btn-success">글쓰기</a>
			</div><!--글쓰기 버튼-->
			<div class="col-md-5">
			<!--검색 들어갈 부분-->
				<form action="/search.do" method="post" name="search" class="form-inline">
					<div class="form-group">
						<select name="criteria">
							<option value="n">----------</option>
							<option value="title">title</option>
							<option value="content">content</option>
							<option value="name">name</option>
						</select>
					</div>
					<div class="form-group">
						<input type="text" name="keyword" id="" class="form-control">
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">검색</button>
					</div>
				</form>
			</div>
		</div>
		<br>
		<table class="table table-bordered">
			<tr>
				<th class='text-center' style='width:100px'>번호</th>
				<th class='text-center'>제목</th>
				<th class='text-center'>작성자</th>
				<th class='text-center'>날짜</th>
				<th class='text-center' style='width:100px'>조회수</th>
			</tr>
			<c:forEach var="dto" items="${list}">
			<tr><!-- 리스트 목록 보여주기 -->
				<td class='text-center'>${dto.bno}</td><!--번호-->

				<td>
					<c:if test="${dto.re_lev!=0}">
						<c:forEach begin="0" end="${dto.re_lev*1}">
						<%-- re_lev 만큼 반복 --%>
							&nbsp;
							<%-- &nbsp; 공백 한칸 --%>
						</c:forEach>
					</c:if>

					<a href="<c:url value='/qRead.do?bno=${dto.bno}'/>">${dto.title}</a>
				
				</td><!--제목-->
				<td class='text-center'>${dto.name}</td><!--작성자-->
				<td class='text-center'>${dto.regDate}</td><!--날짜-->
				<td class='text-center'><span class="badge badge-pill badge-primary">${dto.read_count}</span></td>
			</tr>
			</c:forEach>		
		</table>
		<div class="container">
			<div class="row  justify-content-md-center">
				<nav aria-label="Page navigation example">
				  <ul class="pagination"><!--하단의 페이지 나누기 부분-->

				  </ul>
				</nav>					
			</div>
		</div>
		<div style="height:20px"></div>
	</div>	
</section>
<%@include file="/include/footer.jsp"%>
