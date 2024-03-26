package service;

import java.util.List;

import dao.BookDao;
import dto.BookDto;
import dto.ChangeDto;
import dto.MemberDto;

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

    @Override
    public MemberDto login(MemberDto loginDto) {
        return dao.isLogin(loginDto);
    }

    @Override
    public boolean passwordChange(ChangeDto changeDto) {
        return dao.passwordChange(changeDto) == 1;
    }

    @Override
    public boolean register(MemberDto registerDto) {
        return dao.register(registerDto) == 1;
    }

    @Override
    public boolean leave(MemberDto memberDto) {
        return dao.leave(memberDto) == 1;
    }

    @Override
    public List<MemberDto> memberListAll() {
        return dao.getMemberList();
    }

}
