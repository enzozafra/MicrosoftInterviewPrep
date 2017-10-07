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

  public static void quick_sort(int[] unsorted, int left, int right) {
    int index = partition(unsorted, left, right);
    if (left < index - 1) {
      quick_sort(unsorted, left, index - 1);
    }
    if (index < right) {
      quick_sort(unsorted, index, right);
    }
  }

  public static int partition(int[] unsorted, int left, int right) {
    int middle = (left + right) / 2;
    int pivot = unsorted[middle];

    while (left <= right) {
      // Find an element on left that should be on the right
      while (unsorted[left] < pivot) {
        left++;
      }
      // Find an element on the right that should be on the left
      while (unsorted[right] > pivot) {
        right--;
      }

      // Swap elements and move left and right indices
      if (left <= right) {
        int temp = unsorted[left];
        unsorted[left] = unsorted[right];
        unsorted[right] = temp;
        left++;
        right--;
      }
    }
    return left;
  }

  public static void insertion_sort(int unsorted[]) {
    int n = unsorted.length;
    // Let number at index 0 be the "sorted array" and rest unsorted
    for (int i = 1; i < n; ++i) {
      int key = unsorted[i];
      int j = i - 1;

      // Move elements of arr[0..i-1] that are greater than key
      // to one position ahead of their position
      while (j >= 0 && unsorted[j] > key) {
        unsorted[j + 1] = unsorted[j];
        j = j - 1;        // going backwards because we want to shift all to right
      }

      // at the end, j is the index of the first number >= key.
      // so j+1 is the last item that has been moved
      unsorted[j + 1] = key; // this should always be arr[0]
    }
  }

  // At call, index should be unsorted.length
  public static void recursive_insertion_sort(int unsorted[], int index) {
    // Base
    if (index <= 1) {
      return;
    }

    // Sort first n-1 elements
    recursive_insertion_sort(unsorted, index-1);

    // Insert last element at its correct position in sorted array
    int last = unsorted[index - 1];
    int j = index - 2;

    while (j >= 0 && unsorted[j] > last) {
      unsorted[j+1] = unsorted[j];
      j--;
    }
    unsorted[j+1] = last;
  }


  // To heapify a subtree rooted with node i which is an index in arr[]
  // n is the size of the heap
  public static void heapify(int arr[], int n, int i) {
    // Initialize largest as root
    int largest = i;
    int l = (2*i) + 1;
    int r = (2*i) + 2;

    // if left child is larger than root
    if (l < n && arr[l] > arr[largest]) {
      largest = l;
    }

    // if right child is larger than the largest so far
    if (r < n && arr[r] > arr[largest]) {
      largest = r;
    }

    // If largest is not root, swap
    if (largest != i) {
      int temp = arr[i];
      arr[i] = arr[largest];
      arr[largest] = temp;

      // Recursively heapify the affected sub tree
      heapify(arr, n, largest);
    }

  }

  public static void heap_sort(int unsorted[]) {
    int length = unsorted.length;

    // Build heap (rearrange array)
    for (int i = (length/2) - 1; i >= 0; i--) {
      heapify(unsorted, length, i);
    }

    // one by one extract an element from heap
    for (int i = length - 1; i >= 0; i--) {
      // Move current root to end
      int temp = unsorted[0];
      unsorted[0] = unsorted[i];
      unsorted[i] = temp;

      // Call max heapify on the reduced heap
      heapify(unsorted, i, 0);
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
    // merge_sort(arr, 0, arr.length-1);
    // quick_sort(arr, 0, arr.length-1);
    insertion_sort(arr);
    System.out.println("Sorted array");
    printArray(arr);
  }
}
