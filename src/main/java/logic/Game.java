package logic;

import entity.*;

import java.util.*;

/**
 * Класс, в котором будет все происходить
 */
public class Game {
    public static void main(String[] args) {
        State originState = new State(new char[][]{
                {'X', 'X', 'O', 'O'},
                {'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O'},
                {'X', 'O', 'O', 'O'},
                {'X', 'X', 'O', 'O'}
        });
        Board board = new Board(originState);

        // У нас есть поле и набор фигур, которые нужно разместить на этом поле
        List<Figure> figures = new ArrayList<>();

        figures.add(Figure.RED);
        figures.add(Figure.LIGHT_GREEN);
        figures.add(Figure.PURPLE);

        List<Element> elements = Logic.getElements(board, figures);
        Logic.getSolutions(null, board, elements);
        System.out.println();
        System.out.println(Logic.finalResult);
    }
}

