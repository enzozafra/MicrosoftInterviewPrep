import java.io.*;
import java.util.*;

public class Sorting {

  public static void selection_sort(int[] unsorted) {
    // One by one move boundary of unsorted array
    for (int i = 0; i < unsorted.length - 1; i++) {
      int min_idx = i;
      // Find min element in unsorted array (imagine moving box to right)
      for (int j = i+1; j < unsorted.length; j++) {
        if (unsorted[j] < unsorted[min_idx]) {
          min_idx = j;
        }

      }
      // swap the found minimum element with the first element
      int temp = unsorted[min_idx];
      unsorted[min_idx] = unsorted[i];
      unsorted[i] = temp;
    }
  }
}
