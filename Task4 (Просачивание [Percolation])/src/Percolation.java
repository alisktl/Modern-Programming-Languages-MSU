/**
 * @author alisher
 * @project Task4 (Просачивание [Percolation])
 */
public class Percolation {

    private int n;
    private int top = 0;
    private int bottom;
    private boolean[][] cellOpened;
    private WeightedQuickUnionFindPathCompression quf;

    public Percolation(int n) {
        this.n = n;
        bottom = n * n + 1;
        quf = new WeightedQuickUnionFindPathCompression(n * n + 2);
        cellOpened = new boolean[n][n];
    }

    // открываем ячейку
    public void open(int row, int col) {
        cellOpened[row - 1][col - 1] = true;
        if (row == 1) {
            quf.union(getIndexQUF(row, col), top);
        }
        if (row == n) {
            quf.union(getIndexQUF(row, col), bottom);
        }

        if (col > 1 && isOpen(row, col - 1)) {
            quf.union(getIndexQUF(row, col), getIndexQUF(row, col - 1));
        }
        if (col < n && isOpen(row, col + 1)) {
            quf.union(getIndexQUF(row, col), getIndexQUF(row, col + 1));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            quf.union(getIndexQUF(row, col), getIndexQUF(row - 1, col));
        }
        if (row < n && isOpen(row + 1, col)) {
            quf.union(getIndexQUF(row, col), getIndexQUF(row + 1, col));
        }
    }

    // проверяем, открыта ли ячейка
    public boolean isOpen(int row, int col) {
        return cellOpened[row - 1][col - 1];
    }

    // проверяем на просачиваемость
    public boolean percolates() {
        return quf.find(top, bottom);
    }

    // выводит индекс в WeightedQuickUnionFindPathCompression
    private int getIndexQUF(int row, int col) {
        return n * (row - 1) + col;
    }
}
