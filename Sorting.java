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

  public static void bubble_sort(int[] unsorted) {
    int n = unsorted.length;
    for (int j = 0; j < n - 1; j++) {
      for (int i = 0; i < n - 1 - j; i++) {
        if (unsorted[i] <= unsorted[i+1]) {
          continue;
        } else {
          int temp = unsorted[i+1];
          unsorted[i+1] = unsorted[i];
          unsorted[i] = temp;
        }
      }
    }
  }

  public static void merge_sort(int[] unsorted, int left, int right) {
    if (right > left) {
      int middle = (left + right) / 2;
      merge_sort(unsorted, left, middle);
      merge_sort(unsorted, middle+1, right);
      merge(unsorted, left, middle, right);
    }
  }

  public static void merge(int[] unsorted, int left, int middle, int right) {
    // Find size of two subarrays that needs to be merged
    int s1 = middle - left + 1;
    int s2 = right - middle;

    // Create temp arrays for both halves
    int L[] = new int[s1];
    int R[] = new int[s2];

    // Copy items into their subarrays
    for (int i = 0; i < s1; i++) {
      L[i] = unsorted[left + i];
    }
    for (int j = 0; j < s2; j++) {
      R[j] = unsorted[middle + 1 + j];
    }

    // Index for the subarrays
    int i = 0, j = 0;

    // Initial index of the merged subarrays
    int k = left;

    // Iterate through the helper array. Compare the left and right half,
    // copying back the smaller element from the two halves into the orig array
    while (i < s1 && j < s2) {
      if (L[i] <= R[j]) {
        unsorted[k] = L[i];
        i++;
      }
      else {
        unsorted[k] = R[j];
        j++;
      }
      k++;
    }

    // Copy remaining elements of L[] if any
    while (i < s1) {
      unsorted[k] = L[i];
      i++;
      k++;
    }

    // Copy remaining elements of R[] if any
    while (j < s2) {
      unsorted[k] = R[j];
      j++;
      k++;
    }
  }



  public static void printArray(int arr[]) {
      int n = arr.length;
      for (int i=0; i<n; ++i)
          System.out.print(arr[i] + " ");
      System.out.println();
  }

  public static void main(String [] args) {
    int arr[] = {64, 34, 25, 12, 22, 11, 0, 123, 11111};
    // selection_sort(arr);
    // bubble_sort(arr);
    merge_sort(arr, 0, arr.length-1);
    System.out.println("Sorted array");
    printArray(arr);
  }
}
