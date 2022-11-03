package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.BoardDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO
{
    private final DataSource ds = PooledDataSource.getDataSource();
    public List<BoardDTO> findAll()
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<BoardDTO> boardDTOs = new ArrayList<>();

        try{
            //Class.forName("com.mysql.jdbc.Driver"); //java 7이후 생략 가능
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //String url = "jdbc:mysql://localhost/mydb?characterEncoding=utf8&serverTimezone=UTC&useSSL=false";
            //String url = "jdbc:mysql://localhost/mydb";
            //conn = DriverManager.getConnection(url, "root", "1234");
            //conn.setAutoCommit(false);
            conn = ds.getConnection();
            String query = "SELECT * FROM BOARD";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                BoardDTO boardDTO = new BoardDTO();

                Long id = rs.getLong("board_id");
                String title = rs.getString("title");
                String writer = rs.getString("writer");
                String contents = rs.getString("contents");
                LocalDateTime regdate = rs.getTimestamp("regdate").toLocalDateTime();
                int hit = rs.getInt("hit");

                boardDTO.setId(id);
                boardDTO.setTitle(title);
                boardDTO.setWriter(writer);
                boardDTO.setContents(contents);
                boardDTO.setRegDate(regdate);
                boardDTO.setHit(hit);

                boardDTOs.add(boardDTO);
            }
        } catch(SQLException e){
            System.out.println("error : " + e);
        }  finally{
            try{
                if(conn != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    rs.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return boardDTOs;
    }
}
