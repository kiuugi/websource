package action;

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
            path = "/qReplyView.do?bno=" + req.getParameter("bno");
        }

        return new ActionForward(path, true);
    }

}
