package logic;

import entity.Board;
import entity.ChainElement;
import entity.Figure;
import entity.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


        System.out.println("Trying to get answers");
        Set<ChainElement> answers = Logic.getAnswers(new HashSet<>(), board, figures);
        System.out.println("Trying to get final result");
        List<Board> finalResult = Logic.getFinalResult(new ArrayList<>(), answers, null);
        finalResult.add(board);

        Logic.printFinalResult(finalResult);
    }
}
