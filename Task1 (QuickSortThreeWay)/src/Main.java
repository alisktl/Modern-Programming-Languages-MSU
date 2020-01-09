import java.io.*;
import java.util.Arrays;

/**
 * @author alisher
 * @project Task1
 */
public class Main {
    public static void main(String args[]) {
        try {
            CompareCounter compareCounter1 = new CompareCounter();
            CompareCounter compareCounter2 = new CompareCounter();

            String fileName = "input10000000.txt";

            Worker[] workers1 = readFile(fileName, compareCounter1);
            Worker[] workers2 = readFile(fileName, compareCounter2);

            QuickSort.sort(workers1);
            System.out.printf("Количество сравнений QuickSort: %d\n", compareCounter1.getCount());

            Arrays.sort(workers2);
            System.out.printf("Количество сравнений Arrays.sort: %d\n", compareCounter2.getCount());

            for (int i = 0; i < workers1.length; i++) {
                if (workers1[i].getSalary() != workers2[i].getSalary()) {
                    System.out.println("Different sorted outputs");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.print("Exception: " + e.toString());
        }
    }

    public static Worker[] readFile(String fileName, CompareCounter compareCounter) throws IOException {
        File fin = new File(fileName);
        FileInputStream fis = new FileInputStream(fin);

        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String number_ = br.readLine();
        int length = Integer.parseInt(number_);
        Worker[] workers = new Worker[length];

        int count = 0;
        while ((number_ = br.readLine()) != null) {
            workers[count++] = new Worker(Integer.parseInt(number_), compareCounter);
        }

        br.close();

        return workers;
    }
}
