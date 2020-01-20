import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

/**
 * @author alisher
 * @project Task3 (Игра в 15)
 */
public class Solver {
    private static int n;
    private static int a;
    private static int b;
    private PriorityQueue<State> pq;
    private Board initial;
    private Board goal;

    public Solver(int n) {
        this.n = n;

        pq = new PriorityQueue<>();

        int[][] cells = new int[n][n];
        int k = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = k;
                k++;
            }
        }

        cells[n - 1][n - 1] = 0;
        initial = new Board(cells);
        goal = new Board(cells);
    }

    public static Solver init(int argN, int argA, int argB) {
        n = argN;
        a = argA;
        b = argB;

        Solver solver = new Solver(n);

        // перемешивает с глубиной 2*n^2 шага
        solver.shuffle((int) (2 * Math.pow(n, 2)));

        return solver;
    }

    // начинает решать задачу
    public static void start(Solver solver) {
        if (solver == null) {
            return;
        }

        solver.solve();
    }

    // показывает результат
    public void showResult() {
        if (!isSolvable())
            System.out.println("Нет решения");
        else {
            System.out.println("Количество проделанных шагов: " + moves());

            Stack<Board> solution = solution();

            while (!solution.empty()) {
                System.out.println(solution.pop());
            }
        }
    }

    // алгоритм поиска A*
    private void solve() {
        State minState;
        pq.add(new State(initial, 0, null));

        while (!pq.peek().board.equals(goal)) {
            minState = pq.poll();

            // пробегает по всем соседям
            for (Board neighbor : minState.board.neighbors()) {
                if (minState.moves == 0) {
                    pq.add(new State(neighbor, minState.moves + 1, minState));
                } else if (!neighbor.equals(minState.previousState.board)) {
                    pq.add(new State(neighbor, minState.moves + 1, minState));
                }
            }
        }
    }

    // перемешивает с глубинной shuffleDepth
    private void shuffle(int shuffleDepth) {
        Stack<State> shuffleStack = new Stack<>();
        State currentState;
        Random random = new Random();

        shuffleStack.push(new State(initial, 0, null));
        ArrayList<Board> neighbors;
        Board nextNeighbor;

        int randomNeighborIndex;

        while (shuffleDepth > 0) {
            currentState = shuffleStack.pop();
            neighbors = currentState.board.neighbors();

            randomNeighborIndex = random.nextInt(neighbors.size());
            nextNeighbor = neighbors.get(randomNeighborIndex);

            if (currentState.previousState != null) {
                while (nextNeighbor.equals(currentState.previousState.board)) {
                    randomNeighborIndex = random.nextInt(neighbors.size());
                    nextNeighbor = neighbors.get(randomNeighborIndex);
                }
            }

            shuffleStack.push(new State(neighbors.get(randomNeighborIndex), 0, currentState));

            --shuffleDepth;
        }

        initial = shuffleStack.pop().board;
    }

    // проверяет на разрешимость задачи
    public boolean isSolvable() {
        if (pq.peek().board.equals(goal)) {
            return true;
        }

        return false;
    }

    // выводит количество шагов
    public int moves() {
        if (!isSolvable()) {
            return -1;
        }

        return pq.peek().moves;
    }

    // выводит стэк с последовательностью шагов
    public Stack<Board> solution() {
        if (!isSolvable()) {
            return null;
        }

        Stack<Board> stackSolution = new Stack<>();
        State current = pq.peek();

        while (current.previousState != null) {
            stackSolution.push(current.board);
            current = current.previousState;
        }

        stackSolution.push(initial);
        return stackSolution;
    }

    // State - класс, экземпляры которого хранят в себе Board,
    // количество проделанных шагов и предыдущее состояние
    private class State implements Comparable<State> {
        private Board board;
        private int moves;
        private int priority;
        private State previousState;

        public State(Board board, int moves, State previousState) {
            this.board = board;
            this.moves = moves;
            priority = a * moves + b * board.manhattan();
            this.previousState = previousState;
        }

        public int compareTo(State that) {
            return (this.priority - that.priority);
        }
    }
}
