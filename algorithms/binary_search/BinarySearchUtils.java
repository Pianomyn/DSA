class BinarySearchUtils {
  public static int binarySearch(int[] arr, int target) {
    int left = 0;
    int right = arr.length;
    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (arr[mid] < target) {
        right = mid - 1;
      } else if (arr[mid] > target) {
        left = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  /*
  Index of first element >= target (insertion point for target)
  */
  public static int lowerBound(int[] arr, int target) {
    int left = 0;
    int right = arr.length;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (arr[mid] >= target) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  /*
  Index of the first element > target
  */
  public static int upperBound(int[] arr, int target) {
    int left = 0;
    int right = arr.length;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (arr[mid] > target) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }
}
