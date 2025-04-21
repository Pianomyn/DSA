// https://www.youtube.com/watch?v=Tr-xEGoByFQ&list=PL1gVuOYe08PT54itbrJmJbmLrk1W1DXxu&index=5

interface RangeQuery {
  int minimum(int start, int end);
}
interface RangeUpdate {
}
interface PointQuery {
}
interface PointUpdate {
}

class SegmentTree implements RangeQuery {
  int n;
  int[] rangeLow,  // For a point, the low end of the range its responsible for.
        rangeHigh, // For a point, the high end of the range its responsible for.
        min,  //
        max,  //
        sum,  //
        delta;  //

  public SegmentTree(int n) {
    this.n = n;
    // If n is a power of 2, only need 2n - 1 nodes (n leaves, n-1 internal).
    // For safety, pad to the next power of 2 to accommodate all nodes.
    this.rangeLow = new int[4 * n];
    this.rangeHigh = new int[4 * n];
  }

  void initRange(int nodeIndex, int low, int high) {
    this.rangeLow[nodeIndex] = low;
    this.rangeHigh[nodeIndex] = high;

    if (low == high) {
      return;
    }

    int mid = low + (high - low) / 2;
    initRange(2*nodeIndex, low, mid);  // Left
    initRange(2 * nodeIndex, mid+1, high);  // Right

  }

  @Override
  public int minimum(int start, int end) {
    return 0;
  }
}
