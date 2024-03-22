package service;

import java.util.List;

import dto.TodoDto;

// DAO의 CRUD 메소드를 호출하는곳

public interface TodoService {

    List<TodoDto> getList();

    TodoDto getRow(String no);

    Boolean Insert(TodoDto inserDto);

    Boolean update(TodoDto inserDto);

    Boolean delete(String no);

}
