package action;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardUpdateCountAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {

        BoardService service = new BoardServiceImpl();

        service.updateCount(Integer.parseInt(req.getParameter("bno")));

        path += "?bno=" + Integer.parseInt(req.getParameter("bno"));
        return new ActionForward(path, true);
    }

}
