package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dto.MemberDto;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookLoginAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {

        // userid, password 가져오기
        MemberDto memberDto = new MemberDto();
        memberDto.setUserid(req.getParameter("userid"));
        memberDto.setPassword(req.getParameter("password"));

        // 서비스 호출
        BookService service = new BookServiceImpl();
        MemberDto loginDto = service.login(memberDto);
        // true 결과를 받았다면 받은 정보 sesstion 저장
        if (loginDto != null) {
            HttpSession session = req.getSession();
            session.setAttribute("loginDto", loginDto);
            // <c:if test="${empty loginDto}"> heder에 있는 것과 이름 맞춰줌
        } else {
            path = "/view/login.jsp";
        }

        // sendRedirect, book 목록
        return new ActionForward(path, true);
    }

}
