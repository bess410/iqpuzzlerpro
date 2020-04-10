package logic;

import entity.*;

import java.util.*;

public class Game3 {
    public static void main(String[] args) {
        State originState = new State(new char[][]{
                {'O', 'O', 'O', 'O', 'X'},
                {'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O'}
        });
        Board board = new Board(originState);

        // У нас есть поле и набор фигур, которые нужно разместить на этом поле
        List<Figure> figures = new ArrayList<>();

        figures.add(Figure.DARK_RED);
        figures.add(Figure.ORANGE);
        figures.add(Figure.LIGHT_GREEN);

        List<Element> elements = Logic.getElements(board, figures);
        Logic.getSolutions(null, board, elements);
        System.out.println(Logic.finalResult);
    }
}
