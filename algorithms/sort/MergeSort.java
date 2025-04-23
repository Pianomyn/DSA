import java.util.ArrayList;
import java.util.List;

class MergeSort {
  public static List<Integer> mergeSort(List<Integer> list) {
    if (list.size() <= 1) {
      return list;
    }

    int mid = list.size() / 2;

    List<Integer> left = mergeSort(list.subList(0, mid));
    List<Integer> right = mergeSort(list.subList(mid, list.size()));

    return merge(left, right);
  }

  private static List<Integer> merge(List<Integer> left, List<Integer> right) {
    List<Integer> merged = new ArrayList<>();
    int l = 0, r = 0;

    while (l < left.size() && r < right.size()) {
      if (left.get(l) < right.get(r)) {
        merged.add(left.get(l++));
      } else {
        merged.add(right.get(r++));
      }
    }

    while (l < left.size()) {
      merged.add(left.get(l++));
    }
    while (r < right.size()) {
      merged.add(right.get(r++));
    }

    return merged;
  }

  public static void main(String[] args) {
    record MergeSortTestCase(List<Integer> list, List<Integer> expected) {}

    List<MergeSortTestCase> tests = List.of(
        new MergeSortTestCase(
          new ArrayList<Integer>(List.of(4, 1, 2, 5, 3)),
          new ArrayList<Integer>(List.of(1, 2, 3, 4, 5))),
        new MergeSortTestCase(
          new ArrayList<Integer>(List.of(2, 0, -1, -2, 1)),
          new ArrayList<Integer>(List.of(-2, -1, 0, 1, 2))));

    for (MergeSortTestCase t : tests) {
      System.out.println(mergeSort(t.list));
      System.out.println(t.expected);
      System.out.println();
    }
  }
}
