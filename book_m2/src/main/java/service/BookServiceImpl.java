package service;

import java.util.List;

import dao.BookDao;
import dto.BookDto;

public class BookServiceImpl implements BookService {

    BookDao dao = new BookDao();

    @Override
    public List<BookDto> listAll() {
        return dao.getList();
    }

    @Override
    public List<BookDto> searchListAll(String criteria, String keyword) {
        return dao.getSearchList(criteria, keyword);
    }

    @Override
    public BookDto read(int code) {
        return dao.getRow(code);
    }

    @Override
    public Boolean create(BookDto inserDto) {
        return dao.insert(inserDto) == 1; // 1 이랑 같은지 아닌지 => true, false
    }

    @Override
    public Boolean update(BookDto inserDto) {
        return dao.update(inserDto) == 1;
    }

    @Override
    public Boolean delete(int code) {
        return dao.delete(code) == 1;
    }

}
