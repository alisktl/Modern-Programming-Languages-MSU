import java.util.Random;

/**
 * @author alisher
 * @project Task1
 */

/**
 * QuickSort с дополнениями:
 * 1. 3-частное разбиение
 * 2. один рекурсивный вызов вместо двух
 * 3. переключение на сортировку вставками на массивах небольшого размера
 * 4. выбор опорного элемента как медианы-из-3 (median-of-3)
 */
public class QuickSort {
    private static Random random = new Random();

    public static void sort(Comparable a[]) {
        quickSortThreeWay(a, 0, a.length - 1);
    }

    private static void quickSortThreeWay(Comparable a[], int low, int high) {
        while (high > low) {

            // Если длина массива меньше 40, то сортируем InsertionSort'ом
            if (high - low < 40) {
                InsertionSort.sort(a, low, high);
                return;
            } else {
                int i = low;
                int lt = low;
                int gt = high;

                // опорный элемент
                Comparable pivot = medianOfThree(a, low, high);

                // разбиваем на 3 части
                while (i <= gt) {
                    if (a[i].compareTo(pivot) < 0) {
                        swap(a, lt++, i++);
                    } else if (a[i].compareTo(pivot) > 0) {
                        swap(a, i, gt--);
                    } else {
                        i++;
                    }
                }

                // рекурсивная оптимизация
                if (lt - low < high - gt) {
                    quickSortThreeWay(a, low, lt - 1);
                    low = lt + 1;
                } else {
                    quickSortThreeWay(a, gt + 1, high);
                    high = gt - 1;
                }
            }
        }
    }

    // swap
    private static void swap(Comparable a[], int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // Выбор опорного элемента: медиана из трех (median-of-3)
    private static Comparable medianOfThree(Comparable a[], int low, int high) {
        if (high - low > 1) {
            Comparable first = a[random.nextInt(high + 1 - low) + low];
            Comparable second = a[random.nextInt(high + 1 - low) + low];
            Comparable third = a[random.nextInt(high + 1 - low) + low];

            if ((first.compareTo(second) > 0) != (first.compareTo(third) > 0))
                return first;
            else if ((second.compareTo(first) > 0) != (second.compareTo(third) > 0))
                return second;
            else
                return third;

        } else {
            return a[low];
        }
    }
}
