package service;

import java.util.List;

import dto.BookDto;
import dto.ChangeDto;
import dto.MemberDto;

public interface BookService {
    // DAO 호출
    // CRUD - 조회, 검색, 삽입, 삭제, 수정, ...
    List<BookDto> listAll();

    List<BookDto> searchListAll(String criteria, String keyword);

    BookDto read(int code);

    Boolean create(BookDto inserDto);

    Boolean update(BookDto inserDto);

    Boolean delete(int code);

    // member
    MemberDto login(MemberDto loginDto);

    boolean passwordChange(ChangeDto changeDto);

    boolean register(MemberDto registerDto);

    boolean leave(MemberDto memberDto);

    List<MemberDto> memberListAll();
}
