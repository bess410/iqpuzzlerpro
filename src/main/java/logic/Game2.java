package logic;

import entity.*;

import java.util.*;

public class Game2 {
    // Пусть будет 24
    public static void main(String[] args) {
        State originState = new State(new char[][]{
                {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'O'},
                {'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 'X', 'O', 'O'},
                {'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
        });
        Board board = new Board(originState);

        // У нас есть поле и набор фигур, которые нужно разместить на этом поле
        List<Figure> figures = new ArrayList<>();

        figures.add(Figure.RED);
        figures.add(Figure.LIGHT_GREEN);
        figures.add(Figure.PURPLE);
        figures.add(Figure.DARK_GREEN);
        figures.add(Figure.LIGHT_BLUE);
        figures.add(Figure.SKY);

        List<Element> elements = Logic.getElements(board, figures);
        Logic.getSolutions(null, board, elements);
        Logic.printSolution(board, Logic.finalResult);
    }
}
