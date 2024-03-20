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

@WebServlet("/modify")
public class BookModifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // modify.jsp 넘긴 값 가져오기
        String code = req.getParameter("code");
        String price = req.getParameter("price");

        BookDao dao = new BookDao(); // 위에 import 구문들여 TodoDao 객체생성 TodoDao에 있는 메소드 사용하기위함
        BookDto updateDto = new BookDto();
        // Integer.parseInt(" ") => 공백이 들어가면 NumberFormatException(에러) 발생
        updateDto.setCode(Integer.parseInt(code));
        updateDto.setPrice(Integer.parseInt(price));

        int result = dao.update(updateDto);

        // 수정 성공시 list
        if (result > 0) {
            resp.sendRedirect("/list");
        } else {
            // 실패시 modify.jsp
            resp.sendRedirect("/view/modify.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
