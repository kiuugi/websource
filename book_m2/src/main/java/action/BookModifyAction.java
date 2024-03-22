package action;

import javax.servlet.http.HttpServletRequest;

import dto.BookDto;
import lombok.AllArgsConstructor;
import service.BookService;
import service.BookServiceImpl;

@AllArgsConstructor
public class BookModifyAction implements Action {
    private String path;

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String code = req.getParameter("code");
        String price = req.getParameter("price");
        BookDto modifyDto = new BookDto();
        modifyDto.setCode(Integer.parseInt(code));
        modifyDto.setPrice(Integer.parseInt(price));

        BookService service = new BookServiceImpl();
        boolean result = service.update(modifyDto);
        // req.setAttribute("code", code); 사용자 입력값이 정확하지 않을 수 있기때문에 setAttribute로 담지
        // 않는다.
        if (result) {
            // 수정 내용을 보여주기 read.do로 돌아가기
            path += "?code=" + modifyDto.getCode();
        } else {
            path = "/view/modify.jsp";
        }
        return new ActionForward(path, true);
    }
}
