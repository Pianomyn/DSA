#include <algorithm>
#include <functional>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

class Solution {
public:
  unordered_map<int, vector<vector<int>>>
  createAdjacencyList(vector<vector<int>> &edges) {
    /*
     * Maps vertex to all neighbours and their weights.
     * Assumes the graph is undirected.
     *
     * :edges: Size 3. [Source, Target, Weight]
     */
    unordered_map<int, vector<vector<int>>> adjacencyList;
    for (vector<int> &edge : edges) {
      adjacencyList[edge[0]].push_back(edge);
      adjacencyList[edge[1]].push_back({edge[1], edge[0], edge[2]});
    }
    return adjacencyList;
  }

  static bool edgeComparison(const vector<int> &a, const vector<int> &b) {
    /*
     * Sorts the vector of edges based on the weight in ascending order.
     *
     * :edges: Size 3. [Source, Target, Weight]
     */
    return a[2] > b[2];
  }

  vector<vector<int>> primsAlgorithm(vector<vector<int>> &edges,
                                     vector<int> &vertices) {
    /*
     * Returns the edges that comprise the MST.
     *
     * :edges: edges.size()ach edge is size 3. [Source, Target, Weight].
     * :vertices: Assumes indexing starts at 1.
     */
    if (vertices.size() < 2) {
      return vector<vector<int>>();
    }

    // Indexing of vertices starts at 1. The 0th index won't be used.
    vector<bool> visited(vertices.size() + 1, false);
    int nodesInMST = 0;
    priority_queue<vector<int>, vector<vector<int>>,
                   function<bool(const vector<int>, const vector<int>)>>
        minHeap(edgeComparison);
    unordered_map<int, vector<vector<int>>> adjacencyList =
        createAdjacencyList(edges);
    vector<vector<int>> mst;

    visited[vertices[0]] = true;
    nodesInMST++;
    for (vector<int> &neighbour : adjacencyList[vertices[0]]) {
      minHeap.push(neighbour);
    }

    while (nodesInMST < vertices.size() && minHeap.size() > 0) {
      vector<int> currentNode = minHeap.top();
      minHeap.pop();
      if (visited[currentNode[1]]) {
        continue;
      }

      mst.push_back(currentNode);
      visited[currentNode[1]] = true;
      nodesInMST++;
      for (vector<int> &neighbour : adjacencyList[currentNode[1]]) {
        if (visited[neighbour[1]])
          continue;
        minHeap.push(neighbour);
      }
    }

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
    for (vector<int> &edge : s.primsAlgorithm(allEdges[i], allVertices[i])) {
      cout << edge[0] << " " << edge[1] << " " << edge[2] << endl;
    }
    cout << endl;
  }
  return 0;
}
