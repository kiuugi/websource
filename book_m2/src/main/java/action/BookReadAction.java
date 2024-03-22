package action;

import javax.servlet.http.HttpServletRequest;

import dto.BookDto;
import service.BookService;
import service.BookServiceImpl;

public class BookReadAction implements Action {

    private String path;

    public BookReadAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String code = req.getParameter("code");

        BookService service = new BookServiceImpl();
        BookDto list = service.read(Integer.parseInt(code));

        req.setAttribute("dto", list);

        return new ActionForward(path, false);
    }

}
