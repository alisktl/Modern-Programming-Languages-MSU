import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author alisher
 * @project Task3 (Игра в 15)
 */
public class Board {
    private int n;
    private int[][] cells;
    private int blankPosition;

    public Board(int[][] cells) {
        n = cells.length;
        this.cells = new int[n][n];

        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.cells[i][j] = cells[i][j];

                if (cells[i][j] == 0) {
                    blankPosition = k;
                }

                k++;
            }
        }
    }

    // высисляет Манхэтоновское расстояние до цели
    public int manhattan() {
        int manhattan = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j] == 0) {
                    continue;
                }

                int rowDiff = Math.abs(row(cells[i][j]) - (i + 1));
                int colDiff = Math.abs(column(cells[i][j]) - (j + 1));

                manhattan = manhattan + rowDiff + colDiff;
            }
        }
        return manhattan;
    }

    // выводит номер строки целевой комбинации/Board'а
    private int row(int cell) {
        return (int) Math.ceil((double) cell / (double) n);
    }

    // выводит номер столбца целевой комбинации/Board'а
    private int column(int cell) {
        if (cell % n == 0) {
            return n;
        }

        return cell % n;
    }

    // выводит индекс строки
    private int rowIndex(int cell) {
        return (int) Math.floor(((double) cell) / ((double) n));
    }

    // выводит индекс столбца
    private int columnIndex(int cell) {
        return cell % n;
    }

    // проверяет на сходство с другим Board'ом
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Board that = (Board) other;
        for (int i = 0; i < n; i++) {
            if (!Arrays.equals(this.cells[i], that.cells[i])) {
                return false;
            }
        }

        return true;
    }

    // выводит всех соседей
    public ArrayList<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<>();
        int[][] neighbor;
        if (row(blankPosition + 1) != 1) {
            neighbor = copyMatrix(cells);
            swapAbove(neighbor, blankPosition);
            Board neighborBoard = new Board(neighbor);
            neighbors.add(neighborBoard);
        }
        if (row(blankPosition + 1) != n) {
            neighbor = copyMatrix(cells);
            swapBelow(neighbor, blankPosition);
            Board neighborBoard = new Board(neighbor);
            neighbors.add(neighborBoard);
        }
        if (column(blankPosition + 1) != 1) {
            neighbor = copyMatrix(cells);
            swapLeft(neighbor, blankPosition);
            Board neighborBoard = new Board(neighbor);
            neighbors.add(neighborBoard);
        }
        if (column(blankPosition + 1) != n) {
            neighbor = copyMatrix(cells);
            swapRight(neighbor, blankPosition);
            Board neighborBoard = new Board(neighbor);
            neighbors.add(neighborBoard);
        }

        return neighbors;
    }

    // своп функции
    private void swapAbove(int[][] cells_, int i) {
        int rowIndex = rowIndex(i);
        int columnIndex = columnIndex(i);

        int temp = cells_[rowIndex][columnIndex];
        cells_[rowIndex][columnIndex] = cells_[rowIndex - 1][columnIndex];
        cells_[rowIndex - 1][columnIndex] = temp;
    }

    private void swapBelow(int[][] cells, int i) {
        int rowIndex = rowIndex(i);
        int columnIndex = columnIndex(i);

        int temp = cells[rowIndex][columnIndex];
        cells[rowIndex][columnIndex] = cells[rowIndex + 1][columnIndex];
        cells[rowIndex + 1][columnIndex] = temp;
    }

    private void swapLeft(int[][] cells_, int i) {
        int rowIndex = rowIndex(i);
        int columnIndex = columnIndex(i);

        int temp = cells_[rowIndex][columnIndex];
        cells_[rowIndex][columnIndex] = cells_[rowIndex][columnIndex - 1];
        cells_[rowIndex][columnIndex - 1] = temp;
    }

    private void swapRight(int[][] cells, int i) {
        int rowIndex = rowIndex(i);
        int columnIndex = columnIndex(i);

        int temp = cells[rowIndex][columnIndex];
        cells[rowIndex][columnIndex] = cells[rowIndex][columnIndex + 1];
        cells[rowIndex][columnIndex + 1] = temp;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%d\t", cells[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // копирование матрицы
    private int[][] copyMatrix(int other[][]) {
        int[][] matrixCopy = new int[other.length][];

        for (int i = 0; i < other.length; i++) {
            matrixCopy[i] = new int[other[i].length];
            for (int j = 0; j < other.length; j++) {
                matrixCopy[i][j] = other[i][j];
            }
        }

        return matrixCopy;
    }
}
