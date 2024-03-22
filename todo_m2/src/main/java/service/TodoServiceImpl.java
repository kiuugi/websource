package service;

import java.util.List;

import dao.TodoDao;
import dto.TodoDto;

public class TodoServiceImpl implements TodoService {
    TodoDao dao = new TodoDao();

    @Override
    public List<TodoDto> getList() {
        return dao.getList();
    }

    @Override
    public TodoDto getRow(String no) {
        return dao.getRow(no);
    }

    @Override
    public Boolean Insert(TodoDto inserDto) {
        // 1이면 true, 아니면 false
        // boolean flag = dao.Insert(inserDto)==1?true:false;
        // return flag;
        return dao.Insert(inserDto) == 1;
    }

    @Override
    public Boolean update(TodoDto inserDto) {
        return dao.update(inserDto) == 1;
    }

    @Override
    public Boolean delete(String no) {
        return dao.delete(no) == 1;
    }

}
