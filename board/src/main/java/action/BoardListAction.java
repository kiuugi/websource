package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import dto.PageDto;
import dto.SearchDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardListAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        int page = Integer.parseInt(req.getParameter("page"));
        int amount = Integer.parseInt(req.getParameter("amount"));
        String criteria = req.getParameter("criteria");
        String keyword = req.getParameter("keyword");

        BoardService service = new BoardServiceImpl();

        SearchDto searchDto = new SearchDto(criteria, keyword, page, amount);

        PageDto pageDto = new PageDto(searchDto, service.getTotalRows(criteria, keyword));

        List<BoardDto> list = service.list(searchDto);

        req.setAttribute("list", list);
        req.setAttribute("pageDto", pageDto);

        return new ActionForward(path, false);
    }

}
