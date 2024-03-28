package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BoardDto;
import dto.SearchDto;

public class BoardDao {
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // JDBC 단계
    // 1. 드라이버 로드
    static {
        try {

            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // 2. 커넥션 얻기
    public Connection getConnection() {
        Context initContext;
        try {
            initContext = new InitialContext();
            // java:/comp/env : connection이 등록된 이름 관리하는곳
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            // env등록된 곳으로 가서 jdbc/myoracle : 이 이름으로 된 connection 찾아오기
            DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
            con = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    // 3. DB CRUD

    // 전체 게시물 개수 가져오기
    public int getRows(String criteria, String keyword) {
        int total = 0;
        con = getConnection();
        String sql = "";
        try {
            if (criteria.isEmpty()) {
                sql += "SELECT  COUNT(bno) FROM BOARD";
                pstmt = con.prepareStatement(sql);
            } else {
                sql += "SELECT  COUNT(*) FROM BOARD WHERE " + criteria + " LIKE ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, "%" + keyword + "%");
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return total;
    }

    // 전체 리스트 가져오기
    public List<BoardDto> getList(SearchDto searchDto) {
        List<BoardDto> list = new ArrayList<>();

        con = getConnection();
        int start = searchDto.getPage() * searchDto.getAmount();
        int end = (searchDto.getPage() - 1) * searchDto.getAmount();

        String sql = "SELECT bno, TITLE, name, REGDATE , READ_COUNT , RE_LEV ";
        sql += "FROM (SELECT rownum AS rnum, A.* ";
        sql += "FROM (SELECT rownum, bno, TITLE, name, REGDATE , READ_COUNT , RE_LEV ";
        sql += "FROM BOARD  WHERE bno > 0 ";
        try {
            if (searchDto.getCriteria().isEmpty()) { // 검색 옵션이 선택 안되있을때

                sql += "ORDER BY RE_REF DESC, RE_SEQ ASC) A ";
                sql += "WHERE rownum <= ?) WHERE rnum > ?";
                pstmt = con.prepareStatement(sql);

                pstmt.setInt(1, start);
                pstmt.setInt(2, end);

            } else {
                sql += " and " + searchDto.getCriteria() + " like ?";
                sql += "ORDER BY RE_REF DESC, RE_SEQ ASC) A ";
                sql += "WHERE rownum <= ?) WHERE rnum > ?";
                pstmt = con.prepareStatement(sql);

                pstmt.setString(1, "%" + searchDto.getKeyword() + "%");
                pstmt.setInt(2, start);
                pstmt.setInt(3, end);
            }
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardDto dto = new BoardDto();
                dto.setBno(rs.getInt(1)); // column이름 말고 숫자도 가능
                dto.setTitle(rs.getString(2));
                dto.setName(rs.getString(3));
                dto.setRegDate(rs.getDate(4));
                dto.setRead_count(rs.getInt(5));
                dto.setRe_lev(rs.getInt(6));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return list;
    }

    // 새글작성
    public int create(BoardDto insertDto) {
        int result = 0;
        con = getConnection();
        String sql = "INSERT INTO board(bno, name, password, title, content, attach, re_ref, re_lev, re_seq)";
        sql += "VALUES(board_seq.nextval, ?, ?, ?, ?, ?, board_seq.currval, ?, ?)";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, insertDto.getName());
            pstmt.setString(2, insertDto.getPassword());
            pstmt.setString(3, insertDto.getTitle());
            pstmt.setString(4, insertDto.getContent());
            pstmt.setString(5, insertDto.getAttach());
            pstmt.setInt(6, 0);
            pstmt.setInt(7, 0);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;
    }

    // 게시글 검색
    public List<BoardDto> searchList(SearchDto searchDto) {
        List<BoardDto> list = new ArrayList<>();
        String sql = "";
        con = getConnection();

        sql = "SELECT bno, TITLE, name, REGDATE , READ_COUNT , RE_LEV ";
        sql += "FROM BOARD ";
        sql += "WHERE " + searchDto.getCriteria() + " like ? ";
        sql += "ORDER BY RE_REF DESC, RE_SEQ ASC";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%" + searchDto.getKeyword() + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BoardDto dto = new BoardDto();
                dto.setBno(rs.getInt(1)); // column이름 말고 숫자도 가능
                dto.setTitle(rs.getString(2));
                dto.setName(rs.getString(3));
                dto.setRegDate(rs.getDate(4));
                dto.setRead_count(rs.getInt(5));
                dto.setRe_lev(rs.getInt(6));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return list;
    }

    // 특정 글 조회
    public BoardDto getRow(int bno) {
        BoardDto dto = null;
        con = getConnection();
        String sql = "Select bno, name, title, content, attach, re_ref, re_seq, re_lev from board where bno=?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bno);
            rs = pstmt.executeQuery();

            if (rs.next()) { // 어차피 하나만 조회하니까 if로 씀
                dto = new BoardDto();
                dto.setBno(rs.getInt(1));
                dto.setName(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setContent(rs.getString(4));
                dto.setAttach(rs.getString(5));
                // reply에서 필요함
                dto.setRe_ref(rs.getInt("re_ref"));
                dto.setRe_seq(rs.getInt("re_seq"));
                dto.setRe_lev(rs.getInt("re_lev"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return dto;
    }

    // 글 삭제
    public int delete(BoardDto deleteDto) {
        int result = 0;
        con = getConnection();
        String sql = "delete from board where bno=? and password=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, deleteDto.getBno());
            pstmt.setString(2, deleteDto.getPassword());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;
    }

    // 글 수정
    public int update(BoardDto updateDto) {
        int result = 0;
        // bno와 password 일치 시 제목, 내용 수정
        con = getConnection();
        String sql = "update board set title=?, content=? where bno=? and password=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, updateDto.getTitle());
            pstmt.setString(2, updateDto.getContent());
            pstmt.setInt(3, updateDto.getBno());
            pstmt.setString(4, updateDto.getPassword());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;
    }

    // 댓글 작성
    public int reply(BoardDto replyDto) {
        int result = 0;
        int reRef = replyDto.getRe_ref();
        int reSeq = replyDto.getRe_seq();
        int reLev = replyDto.getRe_lev();
        con = getConnection();

        try {
            String sql = "UPDATE BOARD SET RE_SEQ = RE_SEQ +1 WHERE RE_REF = ? AND RE_SEQ > ?";
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, reRef);
            pstmt.setInt(2, reSeq);

            pstmt.executeUpdate();

            sql = "INSERT INTO board(bno, name, password, title, content, re_ref, re_lev, re_seq) values(board_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            // 새로운 sql 이니까 다시 pstmt에 담음
            pstmt.setString(1, replyDto.getName());
            pstmt.setString(2, replyDto.getPassword());
            pstmt.setString(3, replyDto.getTitle());
            pstmt.setString(4, replyDto.getContent());
            pstmt.setInt(5, reRef);
            pstmt.setInt(6, reLev + 1);
            pstmt.setInt(7, reSeq + 1);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;
    }

    // 조회수 업데이트
    public int updateCount(int bno) {
        int result = 0;
        con = getConnection();
        String sql = "UPDATE board SET read_count = READ_COUNT + 1 WHERE bno = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, bno);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;
    }

    // 4. 자원정리
    public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(Connection con, PreparedStatement pstmt) {
        try {
            if (pstmt != null)
                pstmt.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
