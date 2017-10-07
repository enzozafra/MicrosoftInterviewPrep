public class Searching {

  // Returns the index of value being searched from a sorted array
  public static int binarySearch(int[] arr, int item, int l, int r) {
    if (l > r) return -1; //not in array?

    int mid = l + ((r-l) / 2); // prevent overflow

    if (arr[mid] == item) {
      return mid;
    }
    else if (item > arr[mid]) {
      return binarySearch(arr, item, mid+1, r);
    }
    else {
      return binarySearch(arr, item, l, mid - 1);
    }
  }

  public static int iterative_binarySearch(int[] arr, int item) {
    int low = 0;
    int high = arr.length - 1;
    int mid;

    while (low <= high) {
      mid = low + ((high - low) / 2);
      if (arr[mid] > item) {
        high = mid - 1;
      }
      else if (arr[mid] < item) {
        low = mid + 1;
      }
      else {
        return mid;
      }
    }
    return -1; // not found
  }
}
