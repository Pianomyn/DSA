#include <cstddef>
#include <limits.h>
#include <queue>
#include <unordered_set>
#include <utility>
#include <vector>

class DjikstasAlgorithm {
  using pqElement = std::pair<int, std::pair<int, int>>;

public:
  int djikstrasAlgorithm(std::vector<std::vector<int>> &grid) {
    int n = grid.size();

    std::vector<std::vector<int>> dist(n, std::vector<int>(n, INT_MAX));
    dist[0][0] = 0;

    std::priority_queue<pqElement, std::vector<pqElement>,
                        std::greater<pqElement>>
        pq;
    pq.push({0, {0, 0}});

    pqElement current;
    int x, y;
    int prevCost;
    while (!pq.empty()) {
      current = pq.top();
      pq.pop();
      prevCost = current.first;
      x = current.second.first;
      y = current.second.second;

      if (x - 1 > 0 && prevCost + grid[x - 1][y] < dist[x - 1][y]) {
        dist[x - 1][y] = prevCost + grid[x - 1][y];
        pq.push({dist[x - 1][y], {x - 1, y}});
      }
      if (x + 1 < n && prevCost + grid[x + 1][y] < dist[x + 1][y]) {
        dist[x + 1][y] = prevCost + grid[x + 1][y];
        pq.push({dist[x + 1][y], {x + 1, y}});
      }
      if (y - 1 > 0 && prevCost + grid[x][y - 1] < dist[x][y - 1]) {
        dist[x][y - 1] = prevCost + grid[x][y - 1];
        pq.push({dist[x][y - 1], {x, y - 1}});
      }
      if (y + 1 < n && prevCost + grid[x][y + 1] < dist[x][y + 1]) {
        dist[x][y + 1] = prevCost + grid[x][y + 1];
        pq.push({dist[x][y + 1], {x, y + 1}});
      }
    }

    return dist[n - 1][n - 1];
  }
};
