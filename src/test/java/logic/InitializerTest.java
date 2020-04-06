package logic;

import entity.State;
import org.junit.Assert;
import org.junit.Test;

public class InitializerTest {

    @Test
    public void test_rotateMethod() {
        char[][] arrRedZero = {
                {'X', 'O', 'O', 'O'},
                {'X', 'X', 'X', 'X'}
        };

        State rotateState = Initializer.getRotateState(new State(arrRedZero));

        char[][] innerState = rotateState.getInnerState();

        Assert.assertSame('X',innerState[0][1]);
        Assert.assertSame('O',innerState[1][1]);
    }

    @Test
    public void test_mirrorYMethod() {
        char[][] arrRedZero = {
                {'X', 'O', 'O', 'O'},
                {'X', 'X', 'X', 'X'}
        };

        State mirrorYState = Initializer.getMirrorYState(new State(arrRedZero));

        char[][] innerState = mirrorYState.getInnerState();

        Assert.assertSame('X',innerState[0][3]);
        Assert.assertSame('O',innerState[0][0]);
    }

    @Test
    public void test_mirrorXMethod() {
        char[][] arrRedZero = {
                {'X', 'O', 'O', 'O'},
                {'X', 'X', 'X', 'X'}
        };

        State mirrorXState = Initializer.getMirrorXState(new State(arrRedZero));

        char[][] innerState = mirrorXState.getInnerState();

        Assert.assertSame('X',innerState[1][0]);
        Assert.assertSame('O',innerState[1][3]);
    }

    @Test
    public void test_mirrorXYMethod() {
        char[][] arrRedZero = {
                {'X', 'O', 'O', 'O'},
                {'X', 'X', 'X', 'X'}
        };

        State mirrorXYState = Initializer.getMirrorXYState(new State(arrRedZero));

        char[][] innerState = mirrorXYState.getInnerState();

        Assert.assertSame('X',innerState[1][3]);
        Assert.assertSame('O',innerState[1][0]);
    }
}
