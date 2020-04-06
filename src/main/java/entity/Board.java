package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Игровое поле
 */
@Data
@AllArgsConstructor
public class Board {
    public static int HEIGHT = 5;
    public static int WIDTH = 11;
    private State state;

    @Override
    public String toString() {
        return state.toString();
    }
}
