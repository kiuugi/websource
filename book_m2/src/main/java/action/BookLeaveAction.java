package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookLeaveAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {

        String userid = req.getParameter("userid");
        String password = req.getParameter("password");

        BookService service = new BookServiceImpl();
        boolean result = service.leave(userid);

        HttpSession session = req.getSession();
        session.invalidate();

        if (!result) {
            path = "/view/leave.jsp";
        }

        return new ActionForward(path, true);
    }

}
