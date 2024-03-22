package action;

import javax.servlet.http.HttpServletRequest;

import dto.TodoDto;
import service.TodoService;
import service.TodoServiceImpl;

public class TodoReadAction implements Action {
    private String path;

    public TodoReadAction(String path) {
        this.path = path; // 생성자
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String no = req.getParameter("no");

        TodoService service = new TodoServiceImpl();
        TodoDto todo = service.getRow(no); // DB에서 온 값을 저장할 공간 TodoDto

        req.setAttribute("todo", todo);
        return new ActionForward(path, false);
    }

}
