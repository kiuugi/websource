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

@WebServlet("/list")
public class TodoListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // DB 연동
        TodoDao dao = new TodoDao(); // 위에 import 구문들여 TodoDao 객체생성 TodoDao에 있는 메소드 사용하기위함
        List<TodoDto> list = dao.getList();

        req.setAttribute("list", list);

        RequestDispatcher rd = req.getRequestDispatcher("/view/list.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
