import java.util.Random;

/**
 * @author alisher
 * @project Task4 (Просачивание [Percolation])
 */
public class PercolationStats {

    private Percolation pr;
    private double[] averages;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("числа n и trials должны превышать 0");
        }

        Random random = new Random();

        averages = new double[trials];

        for (int trial = 0; trial < trials; trial++) {
            pr = new Percolation(n);
            int openedCells = 0;

            while (!pr.percolates()) {
                int i = random.nextInt(n) + 1;
                int j = random.nextInt(n) + 1;

                if (!pr.isOpen(i, j)) {
                    pr.open(i, j);
                    ++openedCells;
                }
            }
            double average = (double) openedCells / (n * n);
            averages[trial] = average;
        }
    }

    private static double sum(double[] k) {
        double sum = 0;
        for (int i = 0; i < k.length; i++) {
            sum += k[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, trials);

        System.out.printf("Среднее значение:\t%f\n", ps.mean());
        System.out.printf("Дисперсия случайной величины:\t%f\n", ps.variance());
    }

    // среднее значение
    private double mean() {
        return mean(averages);
    }

    private double mean(double[] a) {
        double sum = sum(a);
        return sum / a.length;
    }

    // дисперсия случайной величины
    private double variance() {
        return variance(averages);
    }

    private double variance(double[] a) {
        double avg = mean(a);
        double sum = 0.0;

        for (int i = 0; i < a.length; i++) {
            sum += (a[i] - avg) * (a[i] - avg);
        }

        return sum / (a.length - 1);
    }
}
