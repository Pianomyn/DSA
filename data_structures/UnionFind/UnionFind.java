public class UnionFind {
    public int[] reps;
    public int[] rank;
 
    public UnionFind(int size) {
        reps = new int[size];
        rank = new int[size];

        for (int i = 0; i < reps.length; i++) {
            reps[i] = i;
        }
    }
 
    public int find(int i) {
        while (reps[i] != i) {
            reps[i] = reps[reps[i]];  // Path Compression
            i = reps[i];
        }
        return reps[i];
    }
 
    public void union(int i, int j) {
        int p1 = find(i);
        int p2 = find(j);
        if (p1 == p2) {
            return;
        }
 
        // Union by rank
        if (rank[p1] < rank[p2]) {
            reps[p1] = p2;
        } else if (rank[p1] > rank[p2]) {
            reps[p2] = p1;
        } else {
            reps[p1] = p2;
            rank[p2]++;
        }
    }
}

