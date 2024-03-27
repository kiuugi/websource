<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/include/header.jsp"%>
<!-- Main content -->
<section class="content">
	<div class="box box-primary">
		<div class="box-header">
			<h3 class="box-title">Password Check</h3>
		</div>
		<div style="height:20px"></div>
		<form name="pwdCheck" method="post" action="/qDelete.do">
			<div class="box-body">
				<div class="form-group">
					<input type="password" name="password" class="form-control" size="10" maxlength='10'>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary">확인</button>
				</div>
			</div>
			<input type="hidden" name="bno" value='<%=request.getParameter("bno")%>'>
			<%-- 이전 view에서 bno를 담고, 거기에 속해있는 read.js 에서 보내준 bno --%>
		</form>
	</div>
</section>
<%@include file="/include/footer.jsp"%>
