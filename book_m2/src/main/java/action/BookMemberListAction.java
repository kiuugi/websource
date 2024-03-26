package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.MemberDto;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookMemberListAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {

        BookService service = new BookServiceImpl();
        List<MemberDto> list = service.memberListAll();

        req.setAttribute("memberList", list);

        return new ActionForward(path, false);

    }

}
