package service;

import java.util.List;

import dto.BoardDto;

public interface BoardService {
    List<BoardDto> list();

    boolean insert(BoardDto insertDto);

    List<BoardDto> searchList(String criteria, String keyword);

    BoardDto read(int bno);

    boolean update(BoardDto updateDto);

    boolean delete(BoardDto deleteDto);

    boolean reply(BoardDto replyDto);
}
