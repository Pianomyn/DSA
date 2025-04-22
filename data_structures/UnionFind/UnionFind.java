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
 
    public void union(int u, int v) {
        int repU = find(u);
        int repV = find(v);
        if (repU == repV) {
            return;
        }
 
        // Union by rank
        if (rank[repU] < rank[repV]) {
            reps[repU] = repV;
        } else if (rank[repU] > rank[repV]) {
            reps[repV] = repU;
        } else {
            reps[repU] = repV;
            rank[repV]++;
        }
    }
}

