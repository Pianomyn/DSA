// https://www.youtube.com/watch?v=Tr-xEGoByFQ&list=PL1gVuOYe08PT54itbrJmJbmLrk1W1DXxu&index=5

interface RangeQuery {
  int minimum(int i, int start, int end);
}
interface RangeUpdate {
}
interface PointQuery {
}
interface PointUpdate {
}

class SegmentTree implements RangeQuery {
  int n;
  int[] rangeLow,  // For a point, the low end of the range it's responsible for.
        rangeHigh, // For a point, the high end of the range it's responsible for.
        min,  //
        max,  //
        sum,  //
        delta;  //  For lazy propagation

  /* If n is a power of 2, only need 2n - 1 nodes (n leaves, n-1 internal).
   * For safety, pad to the next power of 2 to accommodate all nodes if not power of 2.
   */
  public SegmentTree(int n) {
    this.n = n;
    this.rangeLow = new int[4 * n];
    this.rangeHigh = new int[4 * n];

    initRange(1, 0, n-1);  // 1-indexing
  }

  void initRange(int i, intlow, int high) {
    this.rangeLow[i] = low;
    this.rangeHigh[i] = high;

    if (low == high) {
      return;
    }

    int mid = low + (high - low) / 2;
    initRange(2*i, low, mid);  // Left
    initRange(2 * i, mid+1, high);  // Right
  }

  void propagate(int i) {
    this.delta[2 * i] += this.delta[i];
    this.delta[2 * i + 1] += this.delta[i];
    this.delta[i] = 0;
  }

  void rangeUpdate(int i, int start, int end, int change) {
    // Uncovered
    if(start < this.rangeLow[i] || this.rangeHigh[i] < end) {
      return;
    }

    // Fully covered
    if(start <= this.rangeLow[i] && this.rangeHigh[i] <= end) {
      this.delta[i] += change;
      return;
    }

    // Partially covered
    propagate(i);

    rangeUpdate(2 * i, start, end, change);
    rangeUpdate(2 * i + 1, start, end, change);
  }

  /* --------------------- MINIMUM ---------------------*/
  void updateMin(int i) {
    this.min[i] = Math.min(
      this.min[2 * i] + this.delta[2 * i],
      this.min[2 * i + 1] + this.delta[2 * i + 1]
    );
  }

  void rangeUpdateMin(int i, int start, int end, int change) {
    // Down
    rangeUpdate(i, start, end, change);
    // Up
    updateMin(i);
  }

  @Override
  public int minimum(int i, int start, int end) {
    // Uncovered
    if(end < this.rangeLow[i] || this.rangeHigh[i] < start) {
      return Integer.MAX_VALUE;
    }

    // Fully covered
    if(start <= this.rangeLow[i] && this.rangeHigh[i] <= end) {
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

}
