import persistence.MyBatisConnectionFactory;
import persistence.dao.BoardDAO;
import persistence.dao.MyBoardDAO;
import persistence.dto.BoardDTO;
import service.BoardService;
import view.BoardView;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String args[]){
        /*BoardDAO boardDAO = new BoardDAO();
        BoardService boardService = new BoardService(boardDAO);
        BoardView boardView = new BoardView();
        List<BoardDTO> all = boardService.findAll();
        boardView.printAll(all);*/

        MyBoardDAO myBoardDAO = new MyBoardDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        String title = null;
        String writer = "lee";
        Map params = new HashMap<String, Object>();
        params.put("title",title);
        params.put("writer",writer);
        List<BoardDTO> posts = myBoardDAO.findPostWithTitleNameLike(params);
        System.out.println("posts.size() = " + posts.size());
        posts.stream().forEach(p -> System.out.println(p.toString()));
    }
}