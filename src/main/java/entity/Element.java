package entity;

import lombok.Data;

import java.util.List;

@Data
public class Element {
    private Point point;
    private Figure figure;
    private State state;

    private Element parent = null;
    private List<Element> children = null;
    private boolean canChangeParent = true;

    public Element(Point point, Figure figure, State state) {
        this.point = point;
        this.figure = figure;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Element{" +
                "point=" + point +
                ", figure=" + figure +
                ", state=" + state +
                ", parent=" + parent +
                '}';
    }
}
