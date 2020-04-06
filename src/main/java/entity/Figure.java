package entity;

import logic.Initializer;
import java.util.Set;

/**
 * Элемент паззла
 */
public enum Figure {
    RED(Initializer.getAllStates(new char[][]{
            {'X', 'O', 'O', 'O'},
            {'X', 'X', 'X', 'X'}
    }), 5),
    YELLOW(Initializer.getAllStates(new char[][]{
            {'O', 'X', 'O', 'O'},
            {'X', 'X', 'X', 'X'}
    }), 5),
    ORANGE(Initializer.getAllStates(new char[][]{
            {'O', 'O', 'X'},
            {'X', 'X', 'X'},
            {'O', 'X', 'O'}
    }), 5),
    PINK(Initializer.getAllStates(new char[][]{
            {'O', 'O', 'X', 'X'},
            {'X', 'X', 'X', 'O'}
    }), 5),
    LIGHT_BLUE(Initializer.getAllStates(new char[][]{
            {'O', 'X'},
            {'X', 'X'}
    }), 3),
    BLUE(Initializer.getAllStates(new char[][]{
            {'X', 'X', 'X'},
            {'O', 'O', 'X'},
            {'O', 'O', 'X'}
    }), 5),
    DARK_BLUE(Initializer.getAllStates(new char[][]{
            {'O', 'O', 'X'},
            {'X', 'X', 'X'}
    }), 4),
    PURPLE(Initializer.getAllStates(new char[][]{
            {'X', 'X', 'O'},
            {'O', 'X', 'X'},
            {'O', 'O', 'X'}
    }), 5),
    DARK_GREEN(Initializer.getAllStates(new char[][]{
            {'O', 'X', 'O'},
            {'X', 'X', 'X'}
    }), 4),
    LIGHT_GREEN(Initializer.getAllStates(new char[][]{
            {'X', 'O', 'X'},
            {'X', 'X', 'X'}
    }), 5),
    DARK_RED(Initializer.getAllStates(new char[][]{
            {'O', 'X', 'X'},
            {'X', 'X', 'O'}
    }), 4),
    SKY(Initializer.getAllStates(new char[][]{
            {'O', 'X', 'X'},
            {'X', 'X', 'X'}
    }), 5);

    Figure(Set<State> states, int size) {
        this.states = states;
        this.size = size;
    }

    /**
     * Состояния, которые может принимать деталь
     */
    private Set<State> states;

    private int size;

    public Set<State> getStates() {
        return states;
    }

    public int getSize() {
        return size;
    }
}
