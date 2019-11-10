import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String args[]) {
        try {
            CompareCounter compareCounter = new CompareCounter();

            String fileName = "input1000.txt";

            Worker[] workers = readFile(fileName, compareCounter);

            QuickSort.sort(workers);
            System.out.printf("Количество сравнений QuickSort: %d\n", compareCounter.getCount());

            compareCounter.reset();

            workers = null;
            workers = readFile(fileName, compareCounter);

            Arrays.sort(workers);
            System.out.printf("Количество сравнений Arrays.sort: %d\n", compareCounter.getCount());
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
