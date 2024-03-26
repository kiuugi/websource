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
    // 전체 리스트 가져오기
    public List<BoardDto> getList() {
        List<BoardDto> list = new ArrayList<>();
        con = getConnection();
        String sql = "select bno, title, name, regdate, read_count from board";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardDto dto = new BoardDto();
                dto.setBno(rs.getInt(1)); // column이름 말고 숫자도 가능
                dto.setTitle(rs.getString(2));
                dto.setName(rs.getString(3));
                dto.setRegDate(rs.getDate(4));
                dto.setRead_count(rs.getInt(5));

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
    public List<BoardDto> searchList(String criteria, String keyword) {
        List<BoardDto> list = new ArrayList<>();
        String sql = "";
        con = getConnection();

        if (criteria.equals("title")) {
            sql = "select bno, title, name, regdate, read_count from board where title=?";
        } else if (criteria.equals("content")) {
            sql = "select bno, title, name, regdate, read_count from board where content=?";
        } else if (criteria.equals("name")) {
            sql = "select bno, title, name, regdate, read_count from board where name=?";
        }

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, keyword);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BoardDto dto = new BoardDto();
                dto.setBno(rs.getInt(1)); // column이름 말고 숫자도 가능
                dto.setTitle(rs.getString(2));
                dto.setName(rs.getString(3));
                dto.setRegDate(rs.getDate(4));
                dto.setRead_count(rs.getInt(5));

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
        String sql = "Select bno, name, title, content, attach from board where bno=?";

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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return dto;
    }

    // 글 삭제
    public int delete(String passward) {
        int result = 0;

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
