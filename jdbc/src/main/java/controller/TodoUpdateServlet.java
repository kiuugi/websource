package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TodoDao;
import dto.TodoDto;

@WebServlet("/update")
public class TodoUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 한글처리
        req.setCharacterEncoding("utf-8");
        // 번호, 완료헀는지, 상세내용 가져오기
        // value 가 없는 경우 checkbox, radio 의 경우에는 on 값을 가지고 오게 됨
        String no = req.getParameter("no");
        String completed = req.getParameter("completed");
        String description = req.getParameter("description");

        // DB작업

        // DB 연동
        TodoDao dao = new TodoDao(); // 위에 import 구문들여 TodoDao 객체생성 TodoDao에 있는 메소드 사용하기위함
        TodoDto dto = new TodoDto();
        dto.setCompleted(Boolean.parseBoolean(completed));
        dto.setDescription(description);
        dto.setNo(Integer.parseInt(no));

        int result = dao.update(dto);

        // servlet list 경로로 이동
        resp.sendRedirect("list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
