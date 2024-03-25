package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookLogoutAction implements Action {

    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {

        HttpSession session = req.getSession();
        // session.removeAttribute("loginDto"); // 선택한 요소만 세션에서 삭제
        session.invalidate(); // 세션 전부 삭제

        return new ActionForward(path, true);
    }

}
