package service;

import persistence.dao.BoardDAO;
import persistence.dto.BoardDTO;

import java.util.List;


public class BoardService {//dao를 이용함
    private final BoardDAO boardDAO;

    public BoardService(BoardDAO boardDAO)//의존성 주입입
    {
        this.boardDAO = boardDAO;
    }

    public List<BoardDTO> findAll()
    {
        List<BoardDTO> boardDTOs = boardDAO.findAll();
        return boardDTOs;
    }
}
