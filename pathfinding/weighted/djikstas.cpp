#include <algorithm>
#include <cstddef>
#include <limits.h>
#include <queue>
#include <unordered_set>
#include <utility>
#include <vector>

class DjikstasAlgorithm {
  using pqElement = std::pair<int, std::pair<int, int>>;

private:
  struct SimpleHash {
    std::size_t operator()(const std::pair<int, int> &p) const {
      return p.first ^ p.second;
    }
  };

public:
  int djikstrasAlgorithm(std::vector<std::vector<int>> &grid) {
    int n = grid.size();

    std::vector<std::vector<int>> dist(n, std::vector<int>(n, INT_MAX));
    dist[0][0] = 0;

    std::priority_queue<pqElement, std::vector<pqElement>,
                        std::greater<pqElement>>
        pq;
    pq.push({0, {0, 0}});

    std::unordered_set<std::pair<int, int>, SimpleHash> visited;

    pqElement current;
    int x, y;
    int cost;
    while (!pq.empty()) {
      current = pq.top();
      pq.pop();
      cost = current.first;
      x = current.second.first;
      y = current.second.second;
      visited.insert({x, y});

      if (x - 1 > 0 && visited.find({x - 1, y}) == visited.end()) {
        dist[x - 1][y] = std::min(dist[x - 1][y], dist[x][y] + grid[x - 1][y]);
        pq.push({dist[x - 1][y], {x - 1, y}});
      }
      if (x + 1 < n && visited.find({x + 1, y}) == visited.end()) {
        dist[x + 1][y] = std::min(dist[x + 1][y], dist[x][y] + grid[x + 1][y]);
        pq.push({dist[x + 1][y], {x + 1, y}});
      }
      if (y - 1 > 0 && visited.find({x, y - 1}) == visited.end()) {
        dist[x][y - 1] = std::min(dist[x][y - 1], dist[x][y] + grid[x][y - 1]);
        pq.push({dist[x][y - 1], {x, y - 1}});
      }
      if (y + 1 < n && visited.find({x, y + 1}) == visited.end()) {
        dist[x][y + 1] = std::min(dist[x][y + 1], dist[x][y] + grid[x][y + 1]);
        pq.push({dist[x][y + 1], {x, y + 1}});
      }
    }

    return dist[n - 1][n - 1];
  }
};
