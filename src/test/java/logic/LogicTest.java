package logic;

import entity.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogicTest {

    @Test
    public void test_isPossiblePlaceStateToBoardFailure() {
        State boardState = new State(new char[][]{
                {'O', 'O', 'O'},
                {'O', 'X', 'O'},
                {'O', 'X', 'O'},
                {'O', 'X', 'O'}
        });

        State state = new State(new char[][]{
                {'X', 'O', 'O', 'O'},
                {'X', 'X', 'X', 'X'}
        });

        Assert.assertFalse(Logic.isPossiblePlaceStateToPointOnBoard(new Board(boardState), new Point(0, 0), state));
    }

    @Test
    public void test_isPossiblePlaceStateToBoardSuccess() {
        State boardState = new State(new char[][]{
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'X', 'O'},
                {'O', 'X', 'O'}
        });

        State state = new State(new char[][]{
                {'X', 'X'},
                {'X', 'O'},
                {'X', 'O'},
                {'X', 'O'}
        });

        Assert.assertTrue(Logic.isPossiblePlaceStateToPointOnBoard(new Board(boardState), new Point(0, 0), state));
    }

    @Test
    public void test_isPossiblePlaceStateToBoardSuccessAgain() {
        State boardState = new State(new char[][]{
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'X', 'O', 'X'},
                {'O', 'O', 'O'}
        });

        State state = new State(new char[][]{
                {'X', 'X'},
                {'X', 'O'},
                {'X', 'O'},
                {'X', 'O'}
        });

        Assert.assertTrue(Logic.isPossiblePlaceStateToPointOnBoard(new Board(boardState), new Point(0, 1), state));
    }

    @Test
    public void test_isPossiblePlaceStateToBoardSuccessThree() {
        State boardState = new State(new char[][]{
                {'X', 'X', 'O'},
                {'X', 'O', 'O'},
                {'X', 'O', 'O'},
                {'O', 'O', 'O'}
        });

        State state = new State(new char[][]{
                {'O', 'O', 'X'},
                {'O', 'X', 'X'},
                {'X', 'X', 'O'}
        });

        Assert.assertTrue(Logic.isPossiblePlaceStateToPointOnBoard(new Board(boardState), new Point(1, 2), state));
    }

    @Test
    public void test_getBoardWithState() {
        State boardState = new State(new char[][]{
                {'X', 'X', 'O'},
                {'X', 'O', 'O'},
                {'X', 'O', 'X'},
                {'O', 'O', 'O'}
        });

        State state = new State(new char[][]{
                {'O', 'X'},
                {'X', 'X'},
                {'X', 'O'}
        });

        Board boardWithState = Logic.getBoardWithState(new Board(boardState), new Point(0, 2), state);
        System.out.println(boardWithState);
    }

    @Test
    public void test_isPossiblePlaceFigureToBoardSuccess() {
        State boardState = new State(new char[][]{
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'X', 'O', 'X'},
                {'O', 'O', 'O'}
        });

        Assert.assertTrue(Logic.isPossiblePlaceFigureToPointOnBoard(new Board(boardState), new Point(0, 0), Figure.RED));
    }

    @Test
    public void test_AllProcess() {
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

        Assert.assertEquals(4, finalResult.size());
    }

    @Test
    public void test_getEmptySpaces() {
        State originState = new State(new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'Х', 'Х', 'O', 'X'},
                {'O', 'X', 'O', 'O'},
                {'Х', 'Х', 'Х', 'Х'}
        });

        Assert.assertEquals(1, Logic.getMinPart(originState));
    }
}
