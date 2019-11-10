public class CompareCounter {
    private int count = 0;

    public void addCount() {
        ++count;
    }

    public int getCount() {
        return count;
    }

    public void reset() {
        count = 0;
    }
}
