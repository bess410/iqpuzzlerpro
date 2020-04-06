package logic;

import entity.Board;
import entity.ChainElement;
import entity.Figure;
import entity.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


        Set<ChainElement> answers = Logic.getAnswers(new HashSet<>(), board, figures);
        List<Board> finalResult = Logic.getFinalResult(new ArrayList<>(), answers, null);
        finalResult.add(board);

        Logic.printFinalResult(finalResult);
    }
}
