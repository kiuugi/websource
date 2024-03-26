package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import action.BoardListAction;
import action.BoardReadAction;
import action.BoardSearchAction;
import action.BoardWriteAction;

@WebServlet("*.do")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 50) // 파일 업로드 지원 // max값 지정 안하면 제한없음
public class BoardControllerServlet extends HttpServlet {
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
        if (cmd.equals("/qList.do")) {
            action = new BoardListAction("/view/qna_board_list.jsp");

        } else if (cmd.equals("/qWrite.do")) {
            action = new BoardWriteAction("/qList.do");

        } else if (cmd.equals("/search.do")) {
            action = new BoardSearchAction("/view/qna_board_list.jsp");

        } else if (cmd.equals("/qRead.do")) {
            action = new BoardReadAction("/view/qna_board_view.jsp");
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
