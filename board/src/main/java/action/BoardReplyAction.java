package action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardReplyAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        // 페이지나누기
        String page = req.getParameter("page");
        String amount = req.getParameter("amount");
        String criteria = req.getParameter("criteria");
        String keyword = URLEncoder.encode(req.getParameter("keyword"), "utf-8");

        //
        BoardDto replyDto = new BoardDto();
        replyDto.setName(req.getParameter("name"));
        replyDto.setTitle(req.getParameter("title"));
        replyDto.setContent(req.getParameter("content"));
        replyDto.setPassword(req.getParameter("password"));
        replyDto.setRe_lev(Integer.parseInt(req.getParameter("re_lev")));
        replyDto.setRe_ref(Integer.parseInt(req.getParameter("re_ref")));
        replyDto.setRe_seq(Integer.parseInt(req.getParameter("re_seq")));
        BoardService service = new BoardServiceImpl();

        if (!service.reply(replyDto)) {
            path = "/qReplyView.do?bno=" + req.getParameter("bno") + "&page=" + page + "&amount=" + amount
                    + "&criteria=" + criteria
                    + "&keyword="
                    + keyword;
        } else {
            path += "?page=" + page + "&amount=" + amount + "&criteria=" + criteria
                    + "&keyword="
                    + keyword;

        }

        return new ActionForward(path, true);
    }

}
