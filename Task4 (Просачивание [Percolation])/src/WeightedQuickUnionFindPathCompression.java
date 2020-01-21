/**
 * @author alisher
 * @project Task4 (Просачивание [Percolation])
 */
public class WeightedQuickUnionFindPathCompression {
    private int[] id;
    private int[] weight;

    public WeightedQuickUnionFindPathCompression(int n) {
        id = new int[n];
        weight = new int[n];

        for (int i = 0; i < n; i++) {
            id[i] = i;
            weight[i] = 0;
        }
    }

    private int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];
            i = id[i];
        }

        return i;
    }

    public boolean find(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);

        if (weight[i] < weight[j]) {
            id[i] = j;
            weight[j] += weight[i];
        } else {
            id[j] = i;
            weight[i] += weight[j];
        }
    }
}
