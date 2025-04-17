// https://www.youtube.com/watch?v=kPaJfAUwViY
class BinaryIndexTree {
  int size;
  int[] arr;
  int[] tree;

  public BinaryIndexTree(int[] arr) {
    this.arr = arr;
    this.size = arr.length;
    this.tree = new int[this.size + 1]; // To fit bit rules and also to avoid out of bounds
    this.build();
  }

  public void build() {
    for (int i = 0; i < arr.length; i++) {
      update(i + 1, arr[i]);
    }
  }

  public void update(int i, int delta) {
    while (i <= this.size) {
      this.tree[i] += delta;
      int lsb = this.getLeastSignificantBit(i);
      i += lsb;
    }
  }

  // [1, i]
  public int sum(int i) {
    int sum = 0;
    while (i > 0) {
      sum += this.tree[i];
      int lsb = this.getLeastSignificantBit(i);
      i -= lsb;
    }
    return sum;
  }

  // [l, r]
  public int rangeQuery(int l, int r) { return sum(r) - sum(l - 1); }

  private int getLeastSignificantBit(int i) {
    return i & -i; // -i is represented with all bits flipped + 1.
  }
}
