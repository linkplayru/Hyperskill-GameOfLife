import java.util.Random;

public class Universe {

    private boolean[][] current;
    private boolean[][] next;
    private int generation;
    private int alive;
    private final int size;
    private final int[] pathY = {-1, -1, -1, 0, 1, 1, 1, 0};
    private final int[] pathX = {-1, 0, 1, 1, 1, 0, -1, -1};

    public Universe(int size) {
        current = new boolean[size][size];
        next = new boolean[size][size];
        this.size = size;
        genFirst();
    }

    public void genNext() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                next[y][x] = isAlive(y, x);
            }
        }
        current = next;
        next = new boolean[size][size];
        generation++;
        calcAlive();
    }

    private void calcAlive() {
        alive = 0;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (current[y][x]) {
                    alive++;
                }
            }
        }
    }

    public int getGeneration() {
        return generation;
    }

    public int getAlive() {
        return alive;
    }

    public boolean[][] getField() {
        return current;
    }

    public void genFirst() {
        Random random = new Random();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                current[y][x] = random.nextBoolean();
            }
        }
        calcAlive();
        generation = 1;
    }

    private boolean isAlive(int y, int x) {
        int aliveNeighbors = 0;
        boolean alive = current[y][x];
        for (int i = 0; i < 8 && aliveNeighbors <= 3; i++) {
            if (haveNeighbor(y, x, pathY[i], pathX[i])) {
                aliveNeighbors++;
            }
        }
        if (alive) {
            return aliveNeighbors == 2 || aliveNeighbors == 3;
        } else {
            return aliveNeighbors == 3;
        }
    }

    private boolean haveNeighbor(int y, int x, int dirY, int dirX) {
        int indexY = y + dirY;
        int indexX = x + dirX;
        if (indexY > size-1) {
            indexY = 0;
        }
        if (indexY < 0) {
            indexY = size-1;
        }
        if (indexX > size-1) {
            indexX = 0;
        }
        if (indexX < 0) {
            indexX = size-1;
        }
        return current[indexY][indexX];
    }

}
