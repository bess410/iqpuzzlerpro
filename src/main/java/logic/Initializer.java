package logic;

import entity.State;

import java.util.HashSet;
import java.util.Set;

public class Initializer {

    public static Set<State> getAllStates(char[][] source) {
        Set<State> states = new HashSet<>();
        // Добавляем исходное состояние
        State originState = new State(source);
        State rotateState = getRotateState(originState);
        states.addAll(getAllVariantsState(originState));
        states.addAll(getAllVariantsState(rotateState));
        return states;
    }

    private static Set<State> getAllVariantsState(State originState) {
        Set<State> states = new HashSet<>();
        states.add(originState);
        states.add(getMirrorXState(originState));
        states.add(getMirrorYState(originState));
        states.add(getMirrorXYState(originState));
        return states;
    }

    static State getRotateState(State source) {
        char[][] innerState = source.getInnerState();
        char[][] newInnerState = new char[innerState[0].length][innerState.length];
        for (int i = 0; i < newInnerState.length; i++) {
            for (int j = 0; j < newInnerState[0].length; j++) {
                newInnerState[i][newInnerState[0].length - 1 - j] = innerState[j][i];
            }
        }
        return new State(newInnerState);
    }

    static State getMirrorYState(State source) {
        char[][] innerState = source.getInnerState();
        char[][] newInnerState = new char[innerState.length][innerState[0].length];
        for (int i = 0; i < innerState.length; i++) {
            for (int j = 0; j < innerState[0].length; j++) {
                newInnerState[i][newInnerState[0].length - 1 - j] = innerState[i][j];
            }
        }
        return new State(newInnerState);
    }

    static State getMirrorXState(State source) {
        char[][] innerState = source.getInnerState();
        char[][] newInnerState = new char[innerState.length][innerState[0].length];
        for (int i = 0; i < innerState.length; i++) {
            for (int j = 0; j < innerState[0].length; j++) {
                newInnerState[newInnerState.length - 1 - i][j] = innerState[i][j];
            }
        }
        return new State(newInnerState);
    }

    static State getMirrorXYState(State source) {
        char[][] innerState = source.getInnerState();
        char[][] newInnerState = new char[innerState.length][innerState[0].length];
        for (int i = 0; i < innerState.length; i++) {
            for (int j = 0; j < innerState[0].length; j++) {
                newInnerState[newInnerState.length - 1 - i][newInnerState[0].length - 1 - j] = innerState[i][j];
            }
        }
        return new State(newInnerState);
    }
}
