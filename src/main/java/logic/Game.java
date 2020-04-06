package logic;

import entity.Board;
import entity.ChainElement;
import entity.Figure;
import entity.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


        Set<ChainElement> answers = Logic.getAnswers(new HashSet<>(), board, figures);
        List<Board> finalResult = Logic.getFinalResult(new ArrayList<>(), answers, null);
        finalResult.add(board);

        Logic.printFinalResult(finalResult);
    }
}

