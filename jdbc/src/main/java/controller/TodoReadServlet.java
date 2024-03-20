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

@WebServlet("/read")
public class TodoReadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 한글처리
        req.setCharacterEncoding("utf-8");
        // 제목 클릭 시 no 가져오기
        String no = req.getParameter("no");

        // DB작업

        // DB 연동
        TodoDao dao = new TodoDao(); // 위에 import 구문들여 TodoDao 객체생성 TodoDao에 있는 메소드 사용하기위함
        TodoDto todo = dao.getRow(no); // DB에서 온 값을 저장할 공간 TodoDto

        // todo 를 read.jsp에 보여주기
        req.setAttribute("todo", todo); // scope : request, session 중에 하나를 씀

        RequestDispatcher rd = req.getRequestDispatcher("/view/read.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
