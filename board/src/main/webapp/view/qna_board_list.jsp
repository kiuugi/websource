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
			<div class="col-md-4 mb-3"><!--글쓰기 버튼-->
				<a href="#" class="btn btn-success">글쓰기</a>
			</div>
			<div class="col-md-3">
				<select name="amount" class="form-control">
							<option value="10" <c:out value='${pageDto.searchDto.amount == 10?"selected":""}'/> >10</option>
							<option value="20" <c:out value='${pageDto.searchDto.amount == 20?"selected":""}'/> >20</option>
							<option value="30" <c:out value='${pageDto.searchDto.amount == 30?"selected":""}'/> >30</option>
							<option value="40" <c:out value='${pageDto.searchDto.amount == 40?"selected":""}'/> >40</option>
						</select>
			</div>
			<div class="col-md-5">
			<!--검색 들어갈 부분-->
				<form action="<c:url value='/qList.do'/>" method="get" name="search" class="form-inline">
					<input type="hidden" name="page" value="${pageDto.searchDto.page}">
					<input type="hidden" name="amount" value="${pageDto.searchDto.amount}">
					<div class="form-group">
						<select name="criteria" class="form-control">
							<option value="n" <c:out value='${pageDto.searchDto.criteria == null?"selected":""}'/> >----------</option>
							<option value="title" <c:out value='${pageDto.searchDto.criteria == "title"?"selected":""}'/> >title</option>
							<option value="content" <c:out value='${pageDto.searchDto.criteria == "content"?"selected":""}'/> >content</option>
							<option value="name" <c:out value='${pageDto.searchDto.criteria == "name"?"selected":""}'/> >name</option>
						</select>
					</div>
					<div class="form-group">
						<input type="text" name="keyword" id="" class="form-control" value="${pageDto.searchDto.keyword}">
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
						<a href="${dto.bno}" class="move">${dto.title}</a>
					</td><!--제목-->
					<td class='text-center'>${dto.name}</td><!--작성자-->
					<td class='text-center'>${dto.regDate}</td><!--날짜-->
					<td class='text-center'><span class="badge badge-pill badge-primary">${dto.read_count}</span></td>
				</tr>
			</c:forEach>
		</table>
		<div class="container">
			<div class="row  justify-content-md-center">
				<nav aria-label="...">
					<ul class="pagination">

						<c:if test="${pageDto.prev}">
							<li class="page-item">
								<a class="page-link" href="${pageDto.startPage-1}">Previous</a>
							</li>
						</c:if>
						
						<c:forEach begin="${pageDto.startPage}" end="${pageDto.endPage}" var="idx">
							<li class="page-item <c:out value='${pageDto.searchDto.page == idx?"active":""}'/>" aria-current="page">
								<a class="page-link" href="${idx}">${idx}</a>
							</li>
						</c:forEach>

						<c:if test="${pageDto.next}">
							<li class="page-item">
								<a class="page-link" href="${pageDto.endPage+1}">Next</a>
							</li>
						</c:if>
					</ul>
				</nav>
			</div>
		</div>
		<div style="height:20px"></div>
	</div>	
</section>
<form action="<c:url value='/qList.do'/>" method="get" id="actionForm">
	<input type="hidden" name="page" value="${pageDto.searchDto.page}">
	<input type="hidden" name="amount" value="${pageDto.searchDto.amount}">
	<input type="hidden" name="criteria" value="${pageDto.searchDto.criteria}">
	<input type="hidden" name="keyword" value="${pageDto.searchDto.keyword}">
	<%-- <input type="hidden" name="bno" value="${dto.bno}"> --%>
</form>
<script src="<c:url value='/js/list.js'/>"></script>
<%@include file="/include/footer.jsp"%>
