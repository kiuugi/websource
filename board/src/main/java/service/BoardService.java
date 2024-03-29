package service;

import java.util.List;

import dto.BoardDto;
import dto.SearchDto;

public interface BoardService {
    List<BoardDto> list(SearchDto searchDto);

    boolean insert(BoardDto insertDto);

    List<BoardDto> searchList(SearchDto searchDto);

    BoardDto read(int bno);

    boolean update(BoardDto updateDto);

    boolean delete(BoardDto deleteDto);

    boolean reply(BoardDto replyDto);

    boolean updateCount(int bno);

    int getTotalRows(String criteria, String keyword);

    boolean deleteAll(int reRef);

    boolean pwdCheck(BoardDto passDto);
}
