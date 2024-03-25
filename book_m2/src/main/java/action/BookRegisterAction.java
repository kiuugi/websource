package action;

import javax.servlet.http.HttpServletRequest;

import dto.MemberDto;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookRegisterAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {

        String name = req.getParameter("name");
        String userid = req.getParameter("userid");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        MemberDto memberDto = new MemberDto(userid, password, name, email);

        BookService service = new BookServiceImpl();
        if (!service.register(memberDto)) {
            path = "/view/register.jsp";
        }

        return new ActionForward(path, true);
    }

}
