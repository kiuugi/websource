package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dto.BookDto;
import dto.ChangeDto;
import dto.MemberDto;

public class BookDao {
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
        // 주석처리한것들이 context.xml 에 Resource 태그에 들어있음
        // String url = "jdbc:oracle:thin:@localhost:1521:xe";
        // String user = "C##test2";
        // String password = "test";

        // try {
        // con = DriverManager.getConnection(url, user, password);
        // } catch (SQLException e) {
        // e.printStackTrace();
        // }
        return con;
    }

    // 3. CRUD / sql구문
    // 전체조회
    public List<BookDto> getList() {
        List<BookDto> list = new ArrayList<>();
        con = getConnection();
        String sql = "Select code, title, writer, price From booktbl order by code desc";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookDto dto = new BookDto();
                dto.setCode(rs.getInt("code"));
                dto.setTitle(rs.getString("title"));
                dto.setWriter(rs.getString("writer"));
                dto.setPrice(rs.getInt("price"));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs); // 밑에있는 close를 부른거임
        }
        return list;
    }

    // 검색
    public List<BookDto> getSearchList(String criteria, String keyword) {
        List<BookDto> list = new ArrayList<>();
        con = getConnection();

        String sql = "";

        if (criteria.equals("code")) {
            sql = "select * from booktbl where code=?";
        } else {
            sql = "select * from booktbl where writer=?";
        }

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, (keyword));

            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookDto dto = new BookDto();
                dto.setCode(rs.getInt("code"));
                dto.setTitle(rs.getString("title"));
                dto.setWriter(rs.getString("writer"));
                dto.setPrice(rs.getInt("price"));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs); // 밑에있는 close를 부른거임
        }
        return list;
    }

    // 단일 조회
    public BookDto getRow(int code) {
        BookDto dto = null;
        con = getConnection();

        String sql = "Select * From booktbl where code=?";
        try {
            pstmt = con.prepareStatement(sql);
            // ? 해결
            pstmt.setInt(1, code);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                dto = new BookDto();
                dto.setCode(rs.getInt("code"));
                dto.setTitle(rs.getString("title"));
                dto.setWriter(rs.getString("writer"));
                dto.setPrice(rs.getInt("price"));
                dto.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs); // 밑에있는 close를 부른거임
        }
        return dto;
    }

    // 수정
    public int update(BookDto updateDto) {
        int result = 0;

        con = getConnection();

        String sql = "update booktbl set price=? where code=?";

        try {
            pstmt = con.prepareStatement(sql);

            // ? 해결

            pstmt.setInt(1, updateDto.getPrice());
            pstmt.setInt(2, updateDto.getCode());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // 추가
    public int insert(BookDto insertDto) {
        int result = 0;

        con = getConnection();
        String sql = "INSERT INTO booktbl(code, title, writer, price, description) VALUES (?, ?, ?, ?, ?)";

        try {
            pstmt = con.prepareStatement(sql);

            // ? 해결
            pstmt.setInt(1, insertDto.getCode());
            pstmt.setString(2, insertDto.getTitle());
            pstmt.setString(3, insertDto.getWriter());
            pstmt.setInt(4, insertDto.getPrice());
            pstmt.setString(5, insertDto.getDescription());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // 삭제
    public int delete(int code) { // primary key 값을 가져왔음
        int result = 0;

        con = getConnection();
        String sql = "DELETE FROM BOOKTBL WHERE code=?";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, code);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;
    }

    // member 작업
    public MemberDto isLogin(MemberDto loginDto) {
        // 아이디와 비밀번호를 받아서 서버조회
        MemberDto dto = null;
        con = getConnection();
        String sql = "Select userid, name from membertbl where userid=? and password=?";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, loginDto.getUserid());
            pstmt.setString(2, loginDto.getPassword());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                dto = new MemberDto();
                dto.setUserid(rs.getString("userid"));
                dto.setName(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs);
        }
        return dto;
    }

    public int passwordChange(ChangeDto changeDto) {
        int result = 0;

        // 비밀번호 변경
        con = getConnection();
        String sql = "UPDATE membertbl SET password=? WHERE userid=?";

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, changeDto.getNewPassword());
            pstmt.setString(2, changeDto.getUserid());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;

    }

    public int register(MemberDto insertDto) {
        // 회원가입
        int result = 0;
        MemberDto dto = null;
        con = getConnection();
        String sql = "INSERT INTO membertbl(userid, password, name, email) VALUES (?, ?, ?, ?)";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, insertDto.getUserid());
            pstmt.setString(2, insertDto.getPassword());
            pstmt.setString(3, insertDto.getName());
            pstmt.setString(4, insertDto.getEmail());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;
    }

    public int leave(MemberDto leaveDto) {
        int result = 0;
        con = getConnection();
        String sql = "delete from membertbl where userid=? and password=?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, leaveDto.getUserid());
            pstmt.setString(2, leaveDto.getPassword());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }
        return result;

    }

    public List<MemberDto> getMemberList() {
        List<MemberDto> list = new ArrayList<>();
        con = getConnection();
        String sql = "Select userid, password, name, email From membertbl";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MemberDto dto = new MemberDto();
                dto.setUserid(rs.getString("userid"));
                dto.setPassword(rs.getString("password"));
                dto.setName(rs.getString("name"));
                dto.setEmail(rs.getString("email"));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs); // 밑에있는 close를 부른거임
        }
        return list;
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
