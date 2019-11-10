public class Worker implements Comparable<Worker> {

    private int salary;
    private CompareCounter compareCounter;

    public Worker(int salary, CompareCounter compareCounter) {
        this.salary = salary;
        this.compareCounter = compareCounter;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Worker o) {
        compareCounter.addCount();

        if (salary < o.salary) {
            return -1;
        } else if (salary > o.salary) {
            return 1;
        }

        return 0;
    }
}
