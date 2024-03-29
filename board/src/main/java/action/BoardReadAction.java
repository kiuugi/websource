package action;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import dto.SearchDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardReadAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {

        int page = Integer.parseInt(req.getParameter("page"));
        int amount = Integer.parseInt(req.getParameter("amount"));
        String criteria = req.getParameter("criteria");
        String keyword = req.getParameter("keyword");

        SearchDto searchDto = new SearchDto(criteria, keyword, page, amount);
        BoardService service = new BoardServiceImpl();

        BoardDto row = service.read(Integer.parseInt(req.getParameter("bno")));
        req.setAttribute("row", row);
        req.setAttribute("searchDto", searchDto);

        return new ActionForward(path, false);
    }

}
