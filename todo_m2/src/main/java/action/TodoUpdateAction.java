package action;

import javax.servlet.http.HttpServletRequest;

import dto.TodoDto;
import service.TodoService;
import service.TodoServiceImpl;

public class TodoUpdateAction implements Action {

    private String path;

    public TodoUpdateAction(String path) {
        this.path = path;
    }

    @Override
    public ActionForward execute(HttpServletRequest req) throws Exception {
        String title = req.getParameter("title");
        String no = req.getParameter("no");
        String completed = req.getParameter("completed");
        String description = req.getParameter("description");

        TodoDto dto = new TodoDto();
        dto.setCompleted(Boolean.parseBoolean(completed));
        dto.setDescription(description);
        dto.setNo(Integer.parseInt(no));
        dto.setTitle(title);

        TodoService service = new TodoServiceImpl();
        boolean result = service.update(dto);

        return new ActionForward(path, true);
    }

}
