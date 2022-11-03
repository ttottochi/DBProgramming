package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.BoardDTO;

import java.util.List;
import java.util.Map;

public class MyBoardDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public MyBoardDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<BoardDTO> selectAll(){
        List<BoardDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();

        try{
            list = session.selectList("mapper.BoardMapper.selectAll");

        }finally{
            session.close();
        }
        return list;
    }
    public List<BoardDTO> findPostWithTitleLike(String title){
        List<BoardDTO> dtos = null;
        SqlSession session = sqlSessionFactory.openSession();
        try {
            dtos = session.selectList("mapper.BoardMapper.findPostWithTitleLike",title);
        } finally {
            session.close();
        }
        return dtos;
    }
    public List<BoardDTO> findPostWithTitleNameLike(Map<String, Object> params){
        List<BoardDTO> list = null;

        SqlSession session = sqlSessionFactory.openSession();
        try {
            list = session.selectList("mapper.BoardMapper.findPostWithTitleNameLike",params);
        } finally {
            session.close();
        }
        return list;
    }
    public List<BoardDTO> findPostWithTitleNameLike3(BoardDTO boardDTO){
        List<BoardDTO> list = null;

        SqlSession session = sqlSessionFactory.openSession(true);
        try {
            list = session.selectList("mapper.BoardMapper.selectWithTrim",boardDTO);
            session.commit();
        } finally {
            session.close();
            session.rollback();
        }
        return list;
    }



}
