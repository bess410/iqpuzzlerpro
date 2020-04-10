package logic;

import entity.Board;
import entity.Element;
import entity.Figure;
import entity.State;

import java.util.ArrayList;
import java.util.List;

public class Game5 {
    // 40 уровень
    public static void main(String[] args) {
        State originState = new State(new char[][]{
                {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
        });
        Board board = new Board(originState);

        // У нас есть поле и набор фигур, которые нужно разместить на этом поле
        List<Figure> figures = new ArrayList<>();

        figures.add(Figure.RED);
        figures.add(Figure.LIGHT_GREEN);
        figures.add(Figure.PURPLE);
        figures.add(Figure.DARK_GREEN);
        figures.add(Figure.LIGHT_BLUE);
        figures.add(Figure.PINK);
        figures.add(Figure.DARK_RED);
        figures.add(Figure.DARK_BLUE);
        figures.add(Figure.BLUE);
        figures.add(Figure.ORANGE);

        List<Element> elements = Logic.getElements(board, figures);
        Logic.getSolutions(null, board, elements);
        Logic.printSolution(board, Logic.finalResult);
    }
}
