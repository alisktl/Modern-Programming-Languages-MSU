public class InsertionSort {

    public static void sort(Comparable[] a, int low, int high) {
        int i = low + 1, j;

        while (i <= high) {
            j = i - 1;

            while (j >= low && a[j + 1].compareTo(a[j]) < 0) {
                Comparable swap = a[j + 1];
                a[j + 1] = a[j];
                a[j] = swap;

                --j;
            }

            ++i;
        }
    }
}
