package logic;

import entity.Board;
import entity.Element;
import entity.Figure;
import entity.State;

import java.util.ArrayList;
import java.util.List;

public class Game0 {
    public static void main(String[] args) {
        State originState = new State(new char[][]{
                {'X', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O'}
        });
        Board board = new Board(originState);

        // У нас есть поле и набор фигур, которые нужно разместить на этом поле
        List<Figure> figures = new ArrayList<>();

        figures.add(Figure.DARK_GREEN);
        figures.add(Figure.LIGHT_BLUE);

        List<Element> elements = Logic.getElements(board, figures);
        Logic.getSolutions(null, board, elements);
        System.out.println();
        System.out.println(Logic.finalResult);
    }
}
