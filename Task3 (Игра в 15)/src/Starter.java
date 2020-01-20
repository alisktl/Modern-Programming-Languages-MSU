import java.util.Scanner;

/**
 * @author alisher
 * @project Task3 (Игра в 15)
 */
public class Starter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("1. Решить задачу" +
                    "\n2. Проанализировать время программы при параметрах n, a и b" +
                    "\n3. Выйти");
            System.out.print("Ваш ответ (1, 2 или 3): ");

            String answer = in.next();

            if (answer.equals("1")) {
                System.out.print("Параметр n: ");
                int argN = in.nextInt();

                System.out.print("Параметр a: ");
                int argA = in.nextInt();

                System.out.print("Параметр b: ");
                int argB = in.nextInt();

                System.out.println();

                Solver solver = Solver.init(argN, argA, argB);
                Solver.start(solver);
                solver.showResult();
            } else if (answer.equals("2")) {
                System.out.print("Параметр n: ");
                int argN = in.nextInt();

                System.out.print("Параметр a: ");
                int argA = in.nextInt();

                System.out.print("Параметр b: ");
                int argB = in.nextInt();

                System.out.println();

                statistics(argN, argA, argB);
            } else if (answer.equals("3")) {
                return;
            }

            System.out.println("************************\n");
        }
    }

    private static double statistics(int argN, int argA, int argB) {
        double totalSeconds = 0;

        int testCount = 10;

        for (int i = 0; i < testCount; i++) {
            long startTime = System.currentTimeMillis();

            Solver solver = Solver.init(argN, argA, argB);
            Solver.start(solver);

            long stopTime = System.currentTimeMillis();

            double secondsForTest = (stopTime - startTime) / 1000.0;

            System.out.printf("Тест №%d занял %f секунд\n", i + 1, secondsForTest);

            totalSeconds += secondsForTest;
        }

        System.out.printf("\nСреднее время теста: %f секунд\n", totalSeconds / testCount);

        return totalSeconds / testCount;
    }
}
