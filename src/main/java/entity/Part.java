package entity;

import lombok.Data;

@Data
public class Part {
    private int start;
    private int end;
    private int size;
    private int id;

    public Part(int start, int end, int size) {
        this.start = start;
        this.end = end;
        this.size = size;
        this.id = Integer.MAX_VALUE;
    }
}
