import java.text.NumberFormat;
import java.util.Scanner;

/**
 * @author alisher
 * @project Task2
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Напишите выражение для решения [Пример: (1+(2-3)^4)*5/6 ]:");

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(0);
        nf.setMaximumFractionDigits(16);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String expression = scanner.nextLine();
            System.out.println(nf.format(Calculator.calculate(expression)));
            System.out.println();
        }
    }
}
