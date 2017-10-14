import java.io.*;
import java.util.*;

class RecursionAndDynamicProgramming {

  public static int triplestep(int n) {
    HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();
    memo.put(0,1);
    int count = triplestep_helper(memo, n);
    return count;
  }

  public static int triplestep_helper(HashMap<Integer, Integer> memo, int n) {
    if (n < 0) {
      return 0;
    }
    else if (memo.containsKey(n)) {
      return memo.get(n);
    }
    else {
      memo.put(n, triplestep_helper(memo, n-1) + triplestep_helper(memo, n-2)
          + triplestep_helper(memo, n-3));
      return memo.get(n);
    }
  }

  ArrayList<Point> getPath(boolean[][] maze) {
    if (maze == null || maze.length = 0) return null;
    ArrayList<Point> path = new ArrayList<Point>();
    HashSet<Point> failedPoints = new HashSet<Point>();
    if (getPath(maze, maze.length - 1, maze[0].length - 1, path, failedPoints)) {
      return path;
    }
    return null;
  }

  boolean getPath(boolean[][]maze, int row, int col, ArrayList<Point> path,
      HashSet<Point> failedPoints) {
    // Out of bounds or off limit points
    if (col < 0 || row < 0 || !maze[row][col]) {
      return false;
    }
    Point p = new Point(row, col);

    // If already visited a point, return
    if (failedPoints.contains(p)) {
      return false;
    }

    boolean isAtOrigin = (row == 0) && (col == 0);

    // If theres a pth form start to my current location, add the location to path
    if (isAtOrigin || getPath(maze, row, col-1, path, failedPoints)
        || getPath(maze, row-1, col, path, failedPoints)) {
      path.add(p);
      return true;
    }
    failedPoints.add(p); // cache result
    return false;
  }

  // Brute force
  int magicindex(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if (i > arr[i]) {
        return -1;
      }
      else if (i == arr[i]) {
        return i;
      }
    }
    return -1;
  }

  // better sol - BST?
  int magicindex(int[] arr, int l, int r) {
    // if out of bounds (not found)
    if (r < l) {
      return -1;
    }
    int mid = ((r - l) / 2) + l;
    if (arr[mid] == mid) {
      return mid;
    }
    else if (arr[mid] < mid) {
      return magicindex(arr, mid+1, r);
    }
    else {
      return magicindex(arr, l, mid-1);
    }
  }

  // not distinct
  int magicindex(int[] arr) {
    return magicindex(arr, 0, arr.length - 1 );
  }

  int magicindex(int[] arr, int start, int end) {
    if (end < start) return -1;
    int mid = ((end - start) / 2) + start;
    int midValue = arr[mid];
    if (midValue == mid) {
      return mid;
    }

    // Search left
    int leftIndex = Math.min(mid - 1, midValue);
    int left = magicindex(arr, start, leftIndex);
    if (left >= 0) {
      return left;
    }

    // Search right
    int rightIndex = Math.max(mid +1, midValue);
    int right = magicindex(arr, rightIndex, end);
    if (right >= 0) {
      return right;
    }
    return -1;
  }

  ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {
    ArrayList<ArrayList<Integer>> allsubsets;
    // Empty set : basecase
    if (set.size() == index) {
      allsubsets = new ArrayList<ArrayList<Integer>>();
      allsubsets.add(new ArrayList<Integer>()); // Empty set
    }
    else {
      allsubsets = getSubsets(set, index - 1);
      int item = set.get(index);
      ArrayList<ArrayList<Integer>> moresubsets = new ArrayList<ArrayList<Integer>> ();
      for (ArrayList<Integer> subset: allsubsets) {
        ArrayList<Integer> newsubset = new ArrayList<Integer>();
        newsubset.addAll(subset);
        newsubset.add(item);
        moresubsets.add(newsubset);
      }
      allsubsets.addAll(moresubsets);
    }
    return allsubsets;
  }

  void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index) {
    if (leftRem < 0 || rightRem < leftRem) return; // Invalid state

    // Out of left and right, add to list
    if (leftRem == 0 && rightRem == 0) {
      list.add(String.copyValueOf(str));
    }
    else {
      str[index] = '(';
      addParen(list, leftRem - 1, rightRem, str, index + 1);

      str[index] = ')';
      addParen(list, leftRem, rightRem - 1, str, index + 1);
    }
  }

  ArrayList<String> generateParens(int count) {
    char[] str = new char[count * 2];
    ArrayList<String> list = new ArrayList<String>();
    addParen(list, count, count, str, 0);
    return list;
  }

  public static void main (String [] args) {
    System.out.println("triplestep(3): " + String.valueOf(triplestep(3)));
    System.out.println("triplestep(5): " + String.valueOf(triplestep(5)));
    System.out.println("triplestep(10): " + String.valueOf(triplestep(10)));
    System.out.println("triplestep(15): " + String.valueOf(triplestep(15)));
  }
}
