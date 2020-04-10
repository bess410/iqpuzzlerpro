package entity;

import lombok.Data;

/**
 * Состояние детали (положение)
 */
@Data
public class State {
    private final char[][] innerState;

    public State(char[][] innerState) {
        this.innerState = innerState;
    }

    public char[][] getInnerState() {
        char[][] anotherInnerState = new char[this.innerState.length][this.innerState[0].length];
        for (int i = 0; i < this.innerState.length; i++) {
            for (int j = 0; j < this.innerState[0].length; j++) {
                anotherInnerState[i][j] = this.innerState[i][j];
            }
        }
        return anotherInnerState;
    }
}
