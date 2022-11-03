package view;

import persistence.dto.BoardDTO;

import java.util.List;

public class BoardView {
    public void printAll(List<BoardDTO> dtos)
    {
        for(BoardDTO dot:dtos)
        {
            System.out.println("dot.toString() = " + dot.toString());
        }
    }
}
