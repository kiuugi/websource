package action;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardModifyAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {

        BoardDto updateDto = new BoardDto();
        // java.lang.NumberFormatException: Cannot parse null string /
        // Integer.parseInt()가 int형태로 못바꿀때 나는 에러
        updateDto.setBno(Integer.parseInt(req.getParameter("bno")));
        updateDto.setContent(req.getParameter("content"));
        updateDto.setTitle(req.getParameter("title"));
        updateDto.setPassword(req.getParameter("password"));
        // 수정작업
        BoardService service = new BoardServiceImpl();

        if (!service.update(updateDto)) {
            path = "/qModify.do?bno=" + updateDto.getBno();
        } else {
            path += "?bno=" + updateDto.getBno();
        }

        return new ActionForward(path, true);
    }

}
