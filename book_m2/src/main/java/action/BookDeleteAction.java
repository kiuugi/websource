package action;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookDeleteAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {

        String code = req.getParameter("code");

        BookService service = new BookServiceImpl();
        boolean result = service.delete(Integer.parseInt(code));

        if (!result) {
            path = "/view/delete.jsp";
        }

        return new ActionForward(path, true);
    }

}
