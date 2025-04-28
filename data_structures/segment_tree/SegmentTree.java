// https://www.youtube.com/watch?v=Tr-xEGoByFQ&list=PL1gVuOYe08PT54itbrJmJbmLrk1W1DXxu&index=5

interface RangeQuery {
  int minimum(int i, int start, int end);
}
interface RangeUpdate {
  void rangeUpdate(int i, int start, int end, int change,
                   UpdateType updateType);
}
interface PointQuery {}
interface PointUpdate {}

enum UpdateType { MIN, MAX, SUM }

class FakeSegmentTree implements RangeQuery, RangeUpdate {
  int[] arr;
  public FakeSegmentTree(int[] arr) { this.arr = arr; }

  @Override
  public int minimum(int i, int start, int end) {
    int min = Integer.MAX_VALUE;
    for (; start <= end; start++) {
      min = Math.min(min, this.arr[start]);
    }
    return min;
  }

  @Override
  public void rangeUpdate(int i, int start, int end, int change,
                          UpdateType updateType) {
    for (; start <= end; start++) {
      this.arr[start] += change;
    }
  }
}

public class SegmentTree implements RangeQuery, RangeUpdate {
  int n;
  int[] rangeLow, // For a point, the low end of the range it's responsible for.
      rangeHigh, // For a point, the high end of the range it's responsible for.
      min,       //
      max,       //
      sum,       //
      delta;     //  For lazy propagation

  /* If n is a power of 2, only need 2n - 1 nodes (n leaves, n-1 internal).
   * For safety, pad to the next power of 2 to accommodate all nodes if not
   * power of 2.
   */
  public SegmentTree(int[] arr) {
    this.n = arr.length;
    this.rangeLow = new int[4 * n];
    this.rangeHigh = new int[4 * n];
    this.min = new int[4 * n];
    this.delta = new int[4 * n];

    build(1, 0, n - 1, arr); // 1-indexing
  }

  void build(int i, int low, int high, int[] arr) {
    this.rangeLow[i] = low;
    this.rangeHigh[i] = high;

    if (low == high) {
      this.min[i] = arr[low];
      return;
    }

    int mid = low + (high - low) / 2;
    build(2 * i, low, mid, arr);          // Left
    build(2 * i + 1, mid + 1, high, arr); // Right

    updateMin(i);
  }

  void propagate(int i) {
    this.delta[2 * i] += this.delta[i];
    this.delta[2 * i + 1] += this.delta[i];
    this.delta[i] = 0;
  }

  @Override
  public void rangeUpdate(int i, int start, int end, int change,
                          UpdateType updateType) {
    // Uncovered
    if (start < this.rangeLow[i] || this.rangeHigh[i] < end) {
      return;
    }

    // Fully covered
    if (start <= this.rangeLow[i] && this.rangeHigh[i] <= end) {
      this.delta[i] += change;
      return;
    }

    // Partially covered
    propagate(i);

    rangeUpdate(2 * i, start, end, change, updateType);
    rangeUpdate(2 * i + 1, start, end, change, updateType);

    update(i, updateType);
  }

  void update(int i, UpdateType updateType) {
    switch (updateType) {
    case MIN:
      updateMin(i);
      break;
    case MAX:
      // updateMax(i);
      break;
    case SUM:
      // updateSum(i);
      break;
    }
  }

  /* --------------------- MINIMUM ---------------------*/
  void updateMin(int i) {
    this.min[i] = Math.min(this.min[2 * i] + this.delta[2 * i],
                           this.min[2 * i + 1] + this.delta[2 * i + 1]);
  }

  @Override
  public int minimum(int i, int start, int end) {
    // Uncovered
    if (end < this.rangeLow[i] || this.rangeHigh[i] < start) {
      return Integer.MAX_VALUE;
    }

    // Fully covered
    if (start <= this.rangeLow[i] && this.rangeHigh[i] <= end) {
      return this.min[i] + delta[i];
    }

    // Partially covered
    propagate(i);
    int minLeft = minimum(2 * i, start, end);
    int minRight = minimum(2 * i + 1, start, end);

    updateMin(i);

    return Math.min(minLeft, minRight);
  }

  /* --------------------- MAXIMUM ---------------------*/

  /* --------------------- SUM ---------------------*/
  public static void main(String[] args) { SegmentTreeTest.main(); }
}

class SegmentTreeTest {
  public static void main() { overlappingTest(); }
  private static void overlappingTest() {
    int[] arr = {22, 9, 30, 24, 17, 23, 12, 1};
    SegmentTree st = new SegmentTree(arr);
    FakeSegmentTree fst = new FakeSegmentTree(arr);

    assert st.minimum(1, 0, 5) == fst.minimum(1, 0, 5);

    st.rangeUpdate(1, 2, 2, -29, UpdateType.MIN);
    fst.rangeUpdate(1, 2, 2, -29, UpdateType.MIN);

    assert st.minimum(1, 0, 5) == fst.minimum(1, 0, 5);

    st.rangeUpdate(1, 5, 5, -29, UpdateType.MIN);
    fst.rangeUpdate(1, 5, 5, -29, UpdateType.MIN);

    assert st.minimum(1, 0, 5) == fst.minimum(1, 0, 5);
  }
}
