package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.MemberDto;
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
        MemberDto memberDto = new MemberDto();
        memberDto.setUserid(userid);
        memberDto.setPassword(password);

        BookService service = new BookServiceImpl();
        boolean result = service.leave(memberDto);

        if (!result) {
            path = "/view/leave.jsp";
        } else {
            HttpSession session = req.getSession();
            session.invalidate();
        }

        return new ActionForward(path, true);
    }

}
