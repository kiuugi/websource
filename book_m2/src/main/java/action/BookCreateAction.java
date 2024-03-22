package action;

import javax.servlet.http.HttpServletRequest;

import dto.BookDto;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookCreateAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // 도서 추가 화면에서 넘어오는 데이터 가져오기
        String code = req.getParameter("code");
        String title = req.getParameter("title");
        String writer = req.getParameter("writer");
        String price = req.getParameter("price");
        String description = req.getParameter("description");
        BookDto dto = new BookDto(Integer.parseInt(code), title, writer, Integer.parseInt(price), description);
        // 서비스 호출 create
        BookService service = new BookServiceImpl();
        service.create(dto);

        return new ActionForward(path, true);
    }
}
