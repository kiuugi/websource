package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDao;
import dto.BookDto;

@WebServlet("/create")
public class BookCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // create.jsp 에서 넘긴 값 가져오기
        // 한글처리
        req.setCharacterEncoding("utf-8");

        String code = req.getParameter("code");
        String title = req.getParameter("title");
        String writer = req.getParameter("writer");
        String price = req.getParameter("price");
        String description = req.getParameter("description");

        BookDao dao = new BookDao(); // 위에 import 구문들여 TodoDao 객체생성 TodoDao에 있는 메소드 사용하기위함

        BookDto insertDto = new BookDto(Integer.parseInt(code), title, writer, Integer.parseInt(price), description);
        // insertDto.setCode(Integer.parseInt(code));
        // insertDto.setTitle(title);
        // insertDto.setWriter(writer);
        // insertDto.setPrice(Integer.parseInt(price));
        // insertDto.setDescription(description);

        int result = dao.insert(insertDto);

        if (result > 0) {
            resp.sendRedirect("/list");
        } else {
            resp.sendRedirect("/view/create.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
