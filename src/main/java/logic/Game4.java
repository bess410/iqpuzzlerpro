package logic;

import entity.*;

import java.util.*;

public class Game4 {

    // 19 уровень работает
    public static void main(String[] args) {
        State originState = new State(new char[][]{
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'X', 'O', 'X', 'O', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'X', 'X'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'X'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'X'}
        });
        Board board = new Board(originState);

        // У нас есть поле и набор фигур, которые нужно разместить на этом поле
        List<Figure> figures = new ArrayList<>();

        figures.add(Figure.RED);
        figures.add(Figure.ORANGE);
        figures.add(Figure.DARK_GREEN);
        figures.add(Figure.YELLOW);
        figures.add(Figure.LIGHT_BLUE);

        List<Element> elements = Logic.getElements(board, figures);
        Logic.getSolutions(null, board, elements);
        System.out.println(Logic.finalResult);
    }
}
