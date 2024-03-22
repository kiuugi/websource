package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import action.TodoCreateAction;
import action.TodoDeleteAction;
import action.TodoListAction;
import action.TodoReadAction;
import action.TodoUpdateAction;
import dao.TodoDao;
import dto.TodoDto;
import service.TodoService;
import service.TodoServiceImpl;

@WebServlet("*.do") // .do 로 들어오는 모든 요청을 받는 servlet
// insert.do 형식으로 들어올거임.
public class TodoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        // 경로에서 요청 찾기
        String requestUri = req.getRequestURI(); // 8080 이후의 값
        String contextPath = req.getContextPath(); // 프로젝트명
        String cmd = requestUri.substring(contextPath.length()); // 예시 /create.do

        System.out.println("requestUri " + requestUri); // 8080 이후의 값 : contextPath / list.do
        System.out.println("contextPath " + contextPath); // 지금 contextPath를 지웠음 원래는 /todo_m2
        System.out.println("cmd " + cmd); // requestUri.substring(contextPath.length()) 원래 들어올 contextPath 말고//
                                          // requestUri값 == /list.do
        // 주소값 가장 뒤에 붙는 *.do 를 찾기위해 한 일임

        Action action = null;

        if (cmd.equals("/list.do")) {

            action = new TodoListAction("/view/list.jsp");

        } else if (cmd.equals("/read.do")) {

            action = new TodoReadAction("/view/read.jsp");

        } else if (cmd.equals("/modify.do")) {

            // read랑 완전히 같은 일을 함, 보내주는 경로만 다름
            action = new TodoReadAction("/view/modify.jsp");

        } else if (cmd.equals("/update.do")) {

            action = new TodoUpdateAction("/list.do");

        } else if (cmd.equals("/delete.do")) {

            action = new TodoDeleteAction("/list.do");

        } else if (cmd.equals("/create.do")) {

            action = new TodoCreateAction("/list.do");

        }

        ActionForward af = null;

        try {
            af = action.execute(req);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (af.isRedirect()) {
            resp.sendRedirect(af.getPath());
        } else {
            RequestDispatcher rd = req.getRequestDispatcher(af.getPath());
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
