class SortingAndSearchingPractice {

  public static void sortedMerge(int[] a, int[] b, int lastA, int lastB) {
    int indexA = lastA - 1; // Index of last element in a
    int indexB = lastB - 1; // Index of last element in b
    int indexMerged = lastB + last A - 1; // End of merged array

    // Merge a & b starting from last element of each
    while (indexB >= 0) {
      // End of a is > than end of B
      if (indexA >= 0 && a[indexA] > b[indexB]) {
        a[indexMerged] = a[indexA]; // Move that element to end of a
        indexA--;
      }
      else {
        a[indexMerged] = b[indexB];
        indexB--;
      }
      indexMerged--; // Move the indices of the final array
    }

  }

  void sort(String[] array) {
    HashMapList<String, String> mapList = new HashMapList<String, String>();

    // Group words by anagram
    for (String s : array) {
      String key = sortChars(s);
      mapList.put(key, s);
    }

    // Convert hashtable to array
    int index = 0;
    for (String key : mapList.keySet()) {
      ArrayList<String> list = mapList.get(key);
      for (String t : list) {
        array[index] = t;
        index++;
      }
    }
  }

  String sortChars(String s) {
    char[] content = s.toCharArray();
    Arrays.sort(content);
    return new String(content);
  }

  int search_rotated(int a[], int left, int right, int x) {
    int mid = left + ((right - left) / 2);
    if (x == a[mid]) {
      return mid;
    }
    if (right < left) {
      return -1;
    }

    /* Either left or right half must be normally ordered. Found out which
     * side and then use the normally ordered half to figure out which side
     * to search from to find x. */

    // Left is normally ordered
    if (a[left] < a[mid]) {
      if (x >= a[left] && x < a[mid]) {
        return search(a, left, mid - 1, x); // search left
      }
      else {
        return search(a, mid + 1, right, x); // search right
      }
    }
    // Right is normally ordered
    else if (a[left] > a[mid]) {
      if (x > a[mid] && x <= a[right]) {
        return search(a, mid + 1, right, x); // search right
      }
      else {
        return search(a, left, mid - 1, x); // search left
      }
    }
    // Left or right half is all repeats
    else if (a[left] == a[mid]) {
      // If right is different, search it
      if (a[mid] != a[right]) {
        return search(a, mid +1, right, x); // search right
      }
      // We have to search both halves
      else {
        int result = search(a, left, mid - 1, x);
        if (result == -1) {
          return search(a, mid + 1, right, x);
        }
        else {
          return result;
        }
      }
    }
  return -1;
  }


  int search_nosize(Listy list, int value) {
    int index = 1;
    while (list.elementAt(index) != -1 && list.elementAt(index) < value) {
      index *= 2;
    }
    return modifiedBinarySearch(list, value, index / 2, index);
  }

  int modifiedBinarySearch(Listy list, int value, int low, int high) {
    int mid;

    while (low <= high) {
      mid = low + ((high - low) / 2);
      int middle = list.elementAt(mid);
      // Search left
      if (middle > value || middle == -1) {
        high = mid - 1;
      }
      // Search right
      else if (middle < value){
        low = mid + 1;
      }
      else {
        return mid;
      }
    }
    return -1;
  }

  int sparse_search(String[] strings, String str, int first, int last) {
    if (first > last) return -1;
    // Move mid to the middle
    int mid = first + ((last - first) / 2);

    // If mid is empty, move it to the closest non empty
    if (strings[mid].isEmpty()) {
      int left = mid - 1;
      int right = mid + 1;
      while (true) {
        if (left < first && right > last) {
          return -1;
        }
        else if (right <= last && !strings[right].isEmpty()) {
          mid = right;
          break;
        }
        else if (left >= first && !strings[left].isEmpty()) {
          mid = left;
          break;
        }
        left--;
        right++;
      }
    }

    // Start BST

    // Search left
    if (strings[mid].compareTo(str) > 0) {
      return search(strings, str, first, mid - 1);
    }
    else if (strings[mid].compareTo(str) < 0) {
      return search(strings, str, mid + 1, last);
    }
    else {
      return mid;
    }
  }

  int sparse_search(String[] strings, String str) {
    if (strings == null || str == null || str == "") {
      return -1;
    }
    return search(strings, str, 0, strings.length - 1);
  }

}
