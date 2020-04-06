package entity;

import lombok.Data;

/**
 * Состояние детали (положение)
 */
@Data
public class State {
    private final char[][] innerState;

    public void setBlockX(int y0, int x0, int y1, int x1) {
        for (int i = y0; i <= y1; i++) {
//            System.out.println("i = " + i);
            for (int j = x0; j <= x1; j++) {
//                System.out.println("i = " + i + " j = " + j);
                innerState[i][j] = 'X';
            }
        }
    }

    public State(char[][] innerState) {
        this.innerState = innerState;
    }

    public void clearState() {
        for (int i = 0; i < innerState.length; i++) {
            for (int j = 0; j < innerState[0].length; j++) {
                innerState[i][j] = 'O';
            }
        }
    }

    public char[][] getInnerState() {
        char[][] arr = new char[innerState.length][innerState[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = innerState[i][j];
            }
        }
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < innerState.length; i++) {
            for (int j = 0; j < innerState[0].length; j++) {
                builder.append(innerState[i][j]).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
