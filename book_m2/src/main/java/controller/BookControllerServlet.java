package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import action.BookCreateAction;
import action.BookDeleteAction;
import action.BookLeaveAction;
import action.BookListAction;
import action.BookLoginAction;
import action.BookLogoutAction;
import action.BookModifyAction;
import action.BookPasswordAction;
import action.BookReadAction;
import action.BookRegisterAction;
import action.BookSearchAction;

@WebServlet("*.do")
public class BookControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 가져오는것에 대한 한글처리
        req.setCharacterEncoding("utf-8");

        // URI 분리
        String reqUri = req.getRequestURI();
        String contexPath = req.getContextPath(); // 프로젝트명
        String cmd = reqUri.substring(contexPath.length());
        // 요청이 들어오는 URI = 전체 URI - 프로젝트 URI

        // cmd를 가지고 액션 생성
        Action action = null;
        if (cmd.equals("/list.do")) {
            action = new BookListAction("/view/list.jsp");
            // 작업이 끝난 후 보여줄 페이지로 경로

        } else if (cmd.equals("/read.do")) {
            action = new BookReadAction("/view/read.jsp");

        } else if (cmd.equals("/create.do")) {
            action = new BookCreateAction("/list.do");

        } else if (cmd.equals("/delete.do")) {
            action = new BookDeleteAction("/list.do");

        } else if (cmd.equals("/modify.do")) {
            action = new BookModifyAction("/read.do"); // update

        } else if (cmd.equals("/search.do")) {
            action = new BookSearchAction("/view/list.jsp");

        } else if (cmd.equals("/login.do")) {
            action = new BookLoginAction("/list.do");

        } else if (cmd.equals("/logout.do")) {
            action = new BookLogoutAction("/");

        } else if (cmd.equals("/register.do")) {
            action = new BookRegisterAction("/view/login.jsp");

        } else if (cmd.equals("/change.do")) {
            action = new BookPasswordAction("/view/login.jsp");

        } else if (cmd.equals("/leave.do")) {
            action = new BookLeaveAction("/");
        }

        // 생성된 action에게 일 시키기(servlet(~Pro.jsp)이 해야했던 일)
        ActionForward af = null;
        try {
            af = action.execute(req); // 여기있는 req == 가장 위에 doGet(req, resp)
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 이동방식과 경로에 따라 움직이기
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
