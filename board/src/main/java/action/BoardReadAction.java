package action;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardReadAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        BoardService service = new BoardServiceImpl();

        BoardDto row = service.read(Integer.parseInt(req.getParameter("bno")));
        req.setAttribute("row", row);
        return new ActionForward(path, false);
    }

}
