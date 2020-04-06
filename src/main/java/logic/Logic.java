package logic;

import entity.*;

import java.util.*;
import java.util.stream.Collectors;

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

    public static boolean isEmptySpaceEnough(Board board, Set<Figure> figures) {
        // Находим пустые места и проверяем что их можно заполнить оставшимися фигурами
        return true;
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

    public static Set<Point> getAllFreePoints(Board board) {
        Set<Point> points = new HashSet<>();
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

    private static int count = 1;

    public static Set<ChainElement> getAnswers(Set<ChainElement> elements, Board board, List<Figure> figures) {

        // Получаем все свободные точки
        Set<Point> allFreePoints = Logic.getAllFreePoints(board);

        // Берем фигуру
        for (Figure figure : figures) {
            // Берем точку
            for (Point point : allFreePoints) {
                // Берем все возможные states для этой доски, точки и фигуры
                List<State> states = Logic.getAllFigureStatesSuccessForPoint(board, point, figure);

                // Если точки еще есть, а вариантов уже нет, нужно удалить такую цепочку
                // Нам нужно создать цепочки

                for (State state : states) {
                    Board newBoard = Logic.getBoardWithState(board, point, state);
                    elements.add(new ChainElement(board, newBoard));

                    List<Figure> newFigures = new ArrayList<>(figures);
                    newFigures.remove(figure);

                    getAnswers(elements, newBoard, newFigures);
                }
            }
        }
        return elements;
    }

    public static List<Board> getFinalResult(List<Board> boards, Set<ChainElement> answers, Board parent) {
        Optional<ChainElement> board;
        if (parent == null) {
            // Находим полностью заполненный элемент
            board = answers.stream()
                    .filter(item -> isFull(item.getChild().getState()))
                    .findFirst();
        } else {
            board = answers.stream()
                    .filter(answer -> answer.getChild().equals(parent))
                    .findFirst();
        }


        // Получаем его родителя
        if (board.isPresent()) {
            ChainElement element = board.get();
            boards.add(element.getChild());
            getFinalResult(boards, answers, element.getParent());
        }

        return boards;
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

    public static void printFinalResult(List<Board> finalResult) {
        for (int i = 0; i < finalResult.size(); i++) {
            if (i < finalResult.size() - 1) {
                printDifferentStates(finalResult.get(i).getState(), finalResult.get(i + 1).getState());
            } else {
                printDifferentStates(finalResult.get(i).getState(), finalResult.get(i).getState());
            }
        }
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

    public static int getMinPart(State state) {
        char[][] innerState = state.getInnerState();
        List<List<Part>> list = new ArrayList<>();
        for (int i = 0; i < innerState.length; i++) {
            list.add(i, new ArrayList<>());
        }


        for (int i = 0; i < innerState.length; i++) {
            for (int j = 0; j < innerState[0].length; j++) {
                if (innerState[i][j] == 'O') {
                    int start = j;
                    while (j + 1 < innerState[0].length) {
                        j++;
                        if (innerState[i][j] == 'X') {
                            list.get(i).add(new Part(start, j - 1, j - start));
                            break;
                        }
                        if (j == innerState[0].length - 1) {
                            if (innerState[i][j] == 'O') {
                                list.get(i).add(new Part(start, j, j - start + 1));
                            }
                        }
                    }
                }
            }
        }

        int count = 1;
        for (int i = 0; i < list.size(); i++) {
            List<Part> parts = list.get(i);
            if (!parts.isEmpty()) {
                for (int j = 0; j < parts.size(); j++) {
                    parts.get(j).setId(count++);
                }
                break;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            List<Part> parts = list.get(i);

            if (i + 1 < list.size() && !parts.isEmpty()) {
                List<Part> partsNextRow = list.get(i + 1);

                if (partsNextRow.isEmpty()) {
                    for (int j = 0; j < parts.size(); j++) {
                        Part part = parts.get(j);
                        if (part.getId() > count) {
                            part.setId(count++);
                        }
                    }
                }

                if (parts.isEmpty()) {
                    continue;
                }

                for (int j = 0; j < partsNextRow.size(); j++) {
                    for (int k = 0; k < parts.size(); k++) {
                        Part partNext = partsNextRow.get(j);
                        Part part = parts.get(k);
                        // Если есть пересечения
                        if (isMatches(part, partNext)) {
                            int id = part.getId();
                            int id1 = partNext.getId();
                            int min = id < id1 ? id : id1;
                            part.setId(min);
                            partNext.setId(min);
                        } else {
                            if (partNext.getId() > count) {
                                partNext.setId(count++);
                            }
                        }
                    }
                }
            }
        }

        if (list.size() >= 2) {
            List<Part> last = list.get(list.size() - 1);
            List<Part> previous = list.get(list.size() - 2);
            if (previous.isEmpty()) {
                for (int i = 0; i < last.size(); i++) {
                    last.get(i).setId(count++);
                }
            }
        }

        Map<Integer, Integer> map = new HashMap<>();

        list.stream()
                .flatMap(Collection::stream)
                .forEach(item -> {
                    map.merge(item.getId(), item.getSize(), Integer::sum);
                });

        Optional<Integer> min = map.values().stream()
                .min(Integer::compareTo);

        return min.get();
    }

    private static boolean isMatches(Part part, Part partNext) {
        int start = part.getStart();
        int end = part.getEnd();
        int start1 = partNext.getStart();
        int end1 = partNext.getEnd();
        if ((start >= start1 && start <= end1)
                || (end >= start1 && end <= end1)) {
            return true;
        }
        return false;
    }

    public static boolean checkRight(Board board, List<Figure> figures) {
        int size = getAllFreePoints(board).size();
        Optional<Integer> reduce = figures.stream()
                .map(Figure::getSize)
                .reduce(Integer::sum);
        return reduce.filter(integer -> size == integer).isPresent();
    }

    boolean canFillEmptySpaces(Board board, List<Figure> figures) {

        return true;
    }
}
