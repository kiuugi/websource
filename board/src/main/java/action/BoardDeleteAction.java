package action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import dto.BoardDto;
import lombok.AllArgsConstructor;
import service.BoardService;
import service.BoardServiceImpl;

@AllArgsConstructor
public class BoardDeleteAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {

        String page = req.getParameter("page");
        String amount = req.getParameter("amount");
        String criteria = req.getParameter("criteria");
        String keyword = URLEncoder.encode(req.getParameter("keyword"), "utf-8");

        BoardDto deleteDto = new BoardDto();
        deleteDto.setPassword(req.getParameter("password"));
        deleteDto.setBno(Integer.parseInt(req.getParameter("bno")));

        // delete 지금 문제가 원본글을 삭제하면 댓글도 삭제시키는건데 원본글인건만 확인하면 댓글이 있는지 없는지도 확인안하고 그냥 delete를
        // 해서 댓글 삭제가 실패 했는지 성공했는지 확인 못함
        BoardService service = new BoardServiceImpl();
        // bno 이용해서 행 조회
        BoardDto readDto = service.read(deleteDto.getBno());
        // re_ref 꺼내오기 bnp == re_ref : 원본글
        if (readDto.getBno() == readDto.getRe_ref()) {
            // 비밀번호 확인후 일치하면 deleteAll 호출
            if (service.pwdCheck(deleteDto)) {
                service.deleteAll(readDto.getRe_ref());
                path += "?page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword="
                        + keyword;
            } else {
                path = "/view/qna_board_pwdCheck.jsp?bno=" + deleteDto.getBno() + "&page=" + page + "&amount=" + amount
                        + "&criteria=" + criteria + "&keyword="
                        + keyword;
            }
        }
        // deleteDto.setRe_ref(readDto.getRe_ref());

        if (!service.delete(deleteDto)) {
            path = "/view/qna_board_pwdCheck.jsp?bno=" + deleteDto.getBno() + "&page=" + page + "&amount=" + amount
                    + "&criteria=" + criteria + "&keyword="
                    + keyword;
        } else {
            path += "?page=" + page + "&amount=" + amount + "&criteria=" + criteria + "&keyword="
                    + keyword;
        }

        return new ActionForward(path, true);
    }

}
