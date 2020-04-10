package logic;

import entity.*;
import jdk.nashorn.internal.runtime.logging.Logger;

import java.util.*;
import java.util.stream.Collectors;

@Logger
public class Logic {

    public static boolean isPossiblePlaceFigureToPointOnBoard(Board board, Point point, Figure figure) {
        List<State> collect = getAllFigureStatesSuccessForPoint(board, point, figure);
        if (collect.isEmpty()) {
            return false;
        }
        return true;
    }

    public static List<State> getAllFigureStatesSuccessForPoint(Board board, Point point, Figure figure) {
        return figure.getStates().stream()
                .filter(state -> isPossiblePlaceStateToPointOnBoard(board, point, state))
                .collect(Collectors.toList());
    }

    public static boolean isPossiblePlaceStateToPointOnBoard(Board board, Point point, State state) {
        char[][] boardState = board.getState().getInnerState();
        char[][] figureState = state.getInnerState();

        int i = point.getI();
        int j = point.getJ();


        // Пробовать с этой ячейки вставить на доску
        Point firstFullPointState = getFirstFullPointState(figureState);

        // Пытаемся впихнуть нашу фигуру
        for (int k = 0; k < figureState.length; k++) {
            for (int l = 0 - firstFullPointState.getJ(); l < figureState[0].length - firstFullPointState.getJ(); l++) {
                if (i + k >= boardState.length
                        || j + l >= boardState[0].length
                        || j + l < 0
                        || (boardState[i + k][j + l] == 'X' && figureState[k][l + firstFullPointState.getJ()] == 'X')
                ) {
                    return false;
                }
            }
        }
        return true;
    }

    // Найти первую непустую ячейку у фигуры
    private static Point getFirstFullPointState(char[][] figureState) {
        Point point = null;
        for (int a = 0; a < figureState[0].length; a++) {
            if (figureState[0][a] == 'X') {
                return new Point(0, a);
            }
        }
        return point;
    }

    public static List<Point> getAllFreePoints(Board board) {
        List<Point> points = new ArrayList<>();
        char[][] innerState = board.getState().getInnerState();
        for (int i = 0; i < innerState.length; i++) {
            for (int j = 0; j < innerState[0].length; j++) {
                if (innerState[i][j] == 'O') {
                    points.add(new Point(i, j));
                }
            }
        }

        return points;
    }

    public static Point getFirstFreePoint(Board board) {
        char[][] innerState = board.getState().getInnerState();
        for (int i = 0; i < innerState.length; i++)
            for (int j = 0; j < innerState[0].length; j++) {
                if (innerState[i][j] == 'O') {
                    return new Point(i, j);
                }
            }
        return null;
    }

    public static Board getBoardWithState(Board board, Point point, State state) {
        char[][] boardState = board.getState().getInnerState();
        char[][] innerState = state.getInnerState();
        int i = point.getI();
        int j = point.getJ();

        Point firstFullPointState = getFirstFullPointState(state.getInnerState());
        for (int k = 0; k < innerState.length; k++) {
            for (int l = 0 - firstFullPointState.getJ(); l < innerState[0].length - firstFullPointState.getJ(); l++) {
                if (innerState[k][l + firstFullPointState.getJ()] == 'X') {
                    boardState[i + k][j + l] = 'X';
                }
            }
        }
        return new Board(new State(boardState));
    }

    // Из этой мапки нужно удалять все ненужное
    public static List<Element> getElements(Board board, List<Figure> figures) {
        List<Element> elements = new ArrayList<>();
        // Получаем все свободные точки
        List<Point> allFreePoints = getAllFreePoints(board);
        Point point;
        Figure figure;
        State state;
        // и для каждой точки получаем доступные states
        for (int i = 0; i < allFreePoints.size(); i++) {
            point = allFreePoints.get(i);
            for (int j = 0; j < figures.size(); j++) {
                figure = figures.get(j);
                List<State> allFigureStatesSuccessForPoint = getAllFigureStatesSuccessForPoint(board, point, figure);
                for (int k = 0; k < allFigureStatesSuccessForPoint.size(); k++) {
                    state = allFigureStatesSuccessForPoint.get(k);
                    elements.add(new Element(point, figure, state));
                }
            }
        }

        return elements;
    }

    public static Element finalResult;

    public static void getSolutions(Element parent, Board board, List<Element> elements) {

        // Берем первую пустую точку
        Point firstFreePoint = getFirstFreePoint(board);

//        log.println("Берем точку " + firstFreePoint);
        // Берем коллекцию элементов, которые можно разместить в этой точке
        List<Element> collect = elements.stream()
                .filter(element -> element.getPoint().equals(firstFreePoint))
                .collect(Collectors.toList());

        if (parent != null) {
//            log.println("Наш родитель " + parent);
            parent.setChildren(collect);
            collect.stream()
                    .filter(Element::isCanChangeParent)
                    .forEach(element -> element.setParent(parent));

        }


        Board newBoard;
        for (Element element : collect) {
            // Получаем новую доску
//            log.println("Берем элемент " + element);
            if (isPossiblePlaceStateToPointOnBoard(board, element.getPoint(), element.getState())) {
                newBoard = getBoardWithState(board, element.getPoint(), element.getState());
            } else {
//                log.println("Невозможно поместить стейт " + element.getState() + "на данную доску " + board + "\n");
                continue;
            }
//            log.println("Наша новая доска " + newBoard);
            if (isFull(newBoard.getState())) {
//                System.out.println("Неужели!");
                finalResult = element;
                Element previousElement = element;
                while (previousElement.getParent() != null) {
                    previousElement.setCanChangeParent(false);
                    previousElement = previousElement.getParent();
                }
                break;
            }

            // Фильтруем список наших элементов, чтобы не было нашей точки и нашей фигуры
            List<Element> newElements = elements.stream()
                    .filter(el -> !el.getPoint().equals(element.getPoint()))
                    .filter(el -> !el.getFigure().equals(element.getFigure()))
                    .collect(Collectors.toList());

            // Уходим в рекурсию
            getSolutions(element, newBoard, newElements);
        }
    }

    public static void printSolution(Board board, Element element) {
        while (element.getParent() != null) {
            Board boardWithState = getBoardWithState(board, element.getPoint(), element.getState());
            printDifferentStates(board.getState(), boardWithState.getState());
            element = element.getParent();
        }

    }

    public static boolean isFull(State state) {
        char[][] innerState = state.getInnerState();
        for (int i = 0; i < innerState.length; i++) {
            for (int j = 0; j < innerState[0].length; j++) {
                if (innerState[i][j] == 'O') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printDifferentStates(State origin, State next) {
        char[][] originState = origin.getInnerState();
        char[][] nextState = next.getInnerState();
        for (int i = 0; i < originState.length; i++) {
            for (int j = 0; j < originState[0].length; j++) {
                if (originState[i][j] == nextState[i][j]) {
                    System.out.print(originState[i][j] + " ");
                } else {
                    System.out.print("." + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}