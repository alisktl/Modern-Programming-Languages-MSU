/**
 * @author alisher
 * @project Task2
 */
public class Stack<T> {

    private static final int MIN_SIZE = 4;

    private T[] values;
    private int count;

    public Stack() {
        values = (T[]) new Object[MIN_SIZE];
    }

    private void resize(int N) {
        T[] newValues = (T[]) new Object[N];
        for (int i = 0; i < count; i++)
            newValues[i] = values[i];
        values = newValues;
    }

    public void push(T x) {
        if (count == values.length)
            resize(2 * values.length);
        values[count++] = x;
    }

    public T pop() {
        if (count == 0)
            throw new RuntimeException("Stack is empty");

        T x = values[--count];
        values[count] = null;
        if (count < values.length / 4 && count > MIN_SIZE)
            resize(values.length / 2);
        return x;
    }

    public int count() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public T peek() {
        if (count == 0)
            throw new RuntimeException("Stack is empty");

        return values[count - 1];
    }

    public void clear() {
        count = 0;
        resize(MIN_SIZE);
    }

    public int getCapacity() {
        return values.length;
    }
}
