#include <iostream>
#include <vector>

using namespace std;

class BinaryIndexedTree {
private:
  vector<int> bit;
  vector<int> arr;

  // Helper function to perform point update
  void update_point_internal(int idx, int val) {
    while (idx < bit.size()) {
      bit[idx] += val;
      idx += idx & -idx;
    }
  }

  // Helper function to perform point query
  int query_point_internal(int idx) {
    int sum = 0;
    while (idx > 0) {
      sum += bit[idx];
      idx -= idx & -idx;
    }
    return sum;
  }

public:
  BinaryIndexedTree(const vector<int> &nums) {
    int n = nums.size();
    bit.resize(n + 1, 0);
    arr.resize(n + 1, 0);

    // Construct the BIT by performing point updates
    for (int i = 0; i < n; ++i) {
      arr[i + 1] = nums[i];
      update_point_internal(i + 1, nums[i]);
    }
  }

  // Perform point update: increment the value at index idx by val
  void update_point(int idx, int val) {
    int diff = val - arr[idx];
    arr[idx] = val;
    update_point_internal(idx, diff);
  }

  // Perform point query: return the value at index idx
  int query_point(int idx) {
    return query_point_internal(idx) - query_point_internal(idx - 1);
  }

  // Perform range update: increment the values within the range [start, end] by
  // val
  void update_range(int start, int end, int val) {
    update_point(start, val);
    update_point(end + 1, -val);
  }

  // Perform range query: return the sum of values within the range [start, end]
  int query_range(int start, int end) {
    return query_point_internal(end) - query_point_internal(start - 1);
  }
};

int main() {
  vector<int> nums = {1, 3, 5, 7, 9, 11};
  BinaryIndexedTree bit(nums);

  // Example usage
  cout << "Point query at index 3: " << bit.query_point(3) << endl; // Output: 5

  bit.update_point(3, 8); // Update value at index 3 to 8

  cout << "Point query at index 3 after update: " << bit.query_point(3)
       << endl; // Output: 8

  bit.update_range(2, 5, 2); // Add 2 to elements from index 2 to 5

  cout << "Range query from index 2 to 5: " << bit.query_range(2, 5)
       << endl; // Output: 15

  return 0;
}
