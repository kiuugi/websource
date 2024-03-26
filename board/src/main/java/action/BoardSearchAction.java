package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardSearchAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {

        BoardService service = new BoardServiceImpl();
        List<BoardDto> list = service.searchList(req.getParameter("criteria"), req.getParameter("keyword"));
        req.setAttribute("list", list);

        return new ActionForward(path, false);
    }

}
