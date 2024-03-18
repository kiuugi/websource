<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%

    request.setCharacterEncoding("utf-8");

    // id, name, aga 가져오기
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String age = request.getParameter("age");

    // HttpServletRequest : 유효범위
    // request.getParameter() : 사용자의 입력값을 가지고 올 때 범위 제한됨
    // form action의 값으로 사용된 페이지 까지만 유효범위

    // info.jsp 가 알고있는 값(사용자 입력값, DB값)을 다른 페이지랑 공유
    // 1) get/post 방식으로 넘겨준다 : a태그/form 한번 더 쓰기
    // 2) scope 이용
    //      (1) page : 현재 page (x) 잘 안씀
    //      (2) request : HttpServletRequest 유효범위(form action의 값으로 사용된 페이지 까지만 유효범위)
    //      (3) session : HttpSession 유효범위(브라우저를 닫기 전까지)
    //      (4) application : 톰캣 서버 범위 (x) 잘 안씀
    //      request.setAttribute("key(String)", 값), request.getAttribute("key") - 스코프 메소드
    //      session.setAttribute("key(String)", 값), session.getAttribute("key") - 스코프 메소드
%>
<h3>id : <%=id%></h3>
<h3>name : <%=name%></h3>
<h3>age : <%=age%></h3>
<%
    // request scope 사용 //  getParameter()로 받은 정보fmf request로 담음
    request.setAttribute("id", id);
    request.setAttribute("name", name);
    request.setAttribute("age", age);


    // session scope 사용 // getParameter()로 받은 정보를 session으로 담음
    // session.setAttribute("id", id);
    // session.setAttribute("name", name);
    // session.setAttribute("age", age);

    // info.jsp 에 부여된 request 를 next.jsp 에 넘겨주는것
    // info.jsp 에서 할 수 있는 작업들을 next.jsp에서 할 수 있게 됨
    pageContext.forward("next.jsp");
    // forward()를 사용하여 주소를 바꾸지 않고 다른 페이지의 화면을 가져와서 request의 유효범위 밖으로 나가지 않고서 다른 주소의 화면을 출력함 
    // ==> request는 주소에 저장된 값을 다른 주소로 이동될때 버리는데 forward는 주소를 이동하는것이 아니라 다른 주소의 화면을 가져와 쓰는것이므로 저장된 값을 잃지 않고 계속 쓸 수 있음
%>

<%-- <form action="next.jsp" method="post">
      <div>
        <label for="id">id</label>
        <input type="text" name="id" id="id" value="<%=id%>" readonly />
      </div>
      <div>
        <label for="name">name</label>
        <input type="text" name="name" id="name" value="<%=name%>" readonly />
      </div>
      <div>
        <label for="age">age</label>
        <input type="text" name="age" id="age" value="<%=age%>" readonly />
      </div>
      <div>
        <button type="submit">전송</button>
      </div>
    </form> --%>

<%-- <h3>
    <a href="next.jsp?id=<%=id%>&name=<%=name%>&age=<%=age%>">다음 페이지</a>
</h3> --%>
<%-- <h3>
    <a href="next.jsp">다음 페이지</a>
</h3> --%>