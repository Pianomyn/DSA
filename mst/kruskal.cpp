#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
  int doFind(vector<int> &representatives, int n) {
    if (n == representatives[n])
      return n;

    int rep = this->doFind(representatives, representatives[n]);
    representatives[n] = rep;
    return rep;
  }

  void doUnion(vector<int> &representatives, vector<int> &ranks, int u, int v) {
    u = this->doFind(representatives, u);
    v = this->doFind(representatives, v);

    if (u == v) {
      return;
    }

    if (ranks[u] > ranks[v]) {
      representatives[v] = u;
    } else if (ranks[u] < ranks[v]) {
      representatives[u] = v;
    } else {
      representatives[u] = v;
      ranks[v]++;
    }
  }

  static bool edgeComparison(const vector<int> &a, const vector<int> &b) {

    /*
     * Sorts the vector of edges based on the weight in ascending order.
     *
     * :edges: Size 3. [Source, Target, Weight]
     */
    return a[2] < b[2];
  }

  vector<vector<int>> kruskalsAlgorithm(vector<vector<int>> &edges,
                                        vector<int> &vertices) {
    /*
     * Returns the edges that comprise the MST.
     *
     * :edges: edges.size()ach edge is size 3. [Source, Target, Weight].
     * :vertices: Assumes indexing starts at 1.
     */
    vector<vector<int>> mst;

    vector<int> ranks(vertices.size() + 1, 0);
    vector<int> reps(vertices.size() + 1);
    for (int i = 0; i < reps.size(); i++) {
      reps[i] = i;
    }

    sort(begin(edges), end(edges), edgeComparison);

    int u;
    int v;
    for (int i = 0; i < edges.size(); i++) {
      u = doFind(reps, edges[i][0]);
      v = doFind(reps, edges[i][1]);
      if (u == v)
        continue;

      doUnion(reps, ranks, u, v);
      mst.push_back(edges[i]);
    }

    // Should check that MST is valid here

    return mst;
  }
};

int main() {
  vector<vector<vector<int>>> allEdges{{{1, 2, 1}, {2, 3, 4}, {3, 1, 2}},
                                       {{1, 2, 3},
                                        {2, 3, 2},
                                        {3, 4, 3},
                                        {4, 5, 1},
                                        {5, 1, 5},
                                        {2, 4, 1},
                                        {2, 5, 2}}};
  vector<vector<int>> allVertices{{1, 2, 3}, {1, 2, 3, 4, 5}};
  Solution s;
  for (int i = 0; i < allEdges.size(); i++) {
    cout << "Test Case #" << i + 1 << endl;
    for (vector<int> &edge : s.kruskalsAlgorithm(allEdges[i], allVertices[i])) {
      cout << edge[0] << " " << edge[1] << " " << edge[2] << endl;
    }
    cout << endl;
  }
  return 0;
}
