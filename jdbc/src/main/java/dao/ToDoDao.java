package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TodoDto;

public class TodoDao {
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
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "C##test2";
        String password = "test";

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    // 3. sql 작업 = CRUD 메소드 구현
    // 전체조회 - Read
    public List<TodoDto> getList() {
        List<TodoDto> list = new ArrayList<>();
        con = getConnection();
        String sql = "Select no, title, created_at, completed From Todotbl order by no desc";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TodoDto dto = new TodoDto();
                dto.setNo(rs.getInt("no"));
                dto.setTitle(rs.getString("title"));
                dto.setCreatedAt(rs.getDate("created_at"));
                dto.setCompleted(rs.getBoolean("completed"));

                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs); // 밑에있는 close를 부른거임
        }
        return list;
    };

    // 하나 조회 -
    public TodoDto getRow(String no) {
        TodoDto dto = null;

        con = getConnection();

        String sql = "Select * From Todotbl where no=?";
        try {
            pstmt = con.prepareStatement(sql);
            // ? 해결
            pstmt.setInt(1, Integer.parseInt(no));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                dto = new TodoDto();
                dto.setNo(rs.getInt("no"));
                dto.setTitle(rs.getString("title"));
                dto.setCreatedAt(rs.getDate("created_at"));
                dto.setCompleted(rs.getBoolean("completed"));
                dto.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt, rs); // 밑에있는 close를 부른거임
        }
        return dto;
    };

    // 추가 - Create(Insert)
    public int Insert(TodoDto inserDto) {
        int result = 0;
        con = getConnection();
        String sql = "INSERT INTO TODOTBL(NO, title, description) VALUES (todo_seq.nextval, ?, ?)";

        try {
            pstmt = con.prepareStatement(sql);

            // ? 해결
            pstmt.setString(1, inserDto.getTitle());
            pstmt.setString(2, inserDto.getDescription());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, pstmt);
        }

        return result;
    }

    // 수정 - Update
    public int update(TodoDto inserDto) {
        int result = 0;
        con = getConnection();
        String sql = "UPDATE TODOTBL SET COMPLETED=? ,DESCRIPTION=? WHERE NO=?";

        try {
            pstmt = con.prepareStatement(sql);

            // ? 해결
            pstmt.setBoolean(1, inserDto.isCompleted());
            pstmt.setString(2, inserDto.getDescription());
            pstmt.setInt(3, inserDto.getNo());

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
