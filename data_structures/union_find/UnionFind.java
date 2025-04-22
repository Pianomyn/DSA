public class UnionFind {
  public int[] reps;
  public int[] rank;

  public UnionFind(int size) {
    reps = new int[size];
    rank = new int[size];

    for (int u = 0; u < reps.length; u++) {
      reps[u] = u;
    }
  }

  public int find(int u) {
    while (reps[u] != u) {
      reps[u] = reps[reps[u]]; // Path Compression
      u = reps[u];
    }
    return reps[u];
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
