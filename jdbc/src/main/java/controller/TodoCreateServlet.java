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

@WebServlet("/create")
public class TodoCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 한글처리
        req.setCharacterEncoding("utf-8");
        // 사용자가 입력한 todo 가져오기
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        // DB작업

        // DB 연동
        TodoDao dao = new TodoDao(); // 위에 import 구문들여 TodoDao 객체생성 TodoDao에 있는 메소드 사용하기위함
        TodoDto inserDto = new TodoDto();

        inserDto.setTitle(title);
        inserDto.setDescription(description);

        int result = dao.Insert(inserDto);

        // 화면이동(list) // 화면이동할때는 가져갈 정보가 있는지 판단 후 이동 // 지금은 가져갈 거 없다
        resp.sendRedirect("list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
