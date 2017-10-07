import java.io.*;
import java.util.*;

class DynamicProgramming {

  public static int fibonacci(int i) {
    if (i == 0) return 0;
    if (i == 1) return 1;
    return fibonacci(i - 1) + fibonacci(i - 2);
  }

  public static int memoized_fibonacci(HashMap<Integer, Integer> memo, int i) {
    if (memo == null) {
      memo = new HashMap<Integer, Integer>();
    }
    if (i == 0 || i == 1) return i;
    if (!memo.containsKey(i)) {
      memo.put(i, memoized_fibonacci(memo, i - 1) + memoized_fibonacci(memo, i-2));
    }
    return memo.get(i);
  }

  public static int bu_fib(int i) {
    if (i == 0 || i == 1) return i;

    int[] memo = new int[i];
    memo[0] = 0;
    memo[1] = 1;

    for (int j = 2; j < i; j++) {
      memo[j] = memo[j - 1] + memo[j - 2];
    }

    return memo[i - 1] + memo[i - 2];
  }

  // memo[i] is only used for memo[i + 1] and memo[i + 2] can just use 2 variables
  public static int bu_fib2(int i) {
    if (i == 0 || i == 1) return i;
    int a = 0;
    int b = 0;
    for (int j = 2; j < i; j++) {
      int c = a + b;
      a = b;
      b = c;
    }
    return a + b;
  }

  public static void main(String [] args) {
    HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();
    int test1 = 30;

    long startTime = System.currentTimeMillis();
    System.out.println("Fibonacci result: " + String.valueOf(fibonacci(test1)));
    long estimatedTime = System.currentTimeMillis() - startTime;
    System.out.println("Time elaped: " + String.valueOf(estimatedTime));

    startTime = System.currentTimeMillis();
    System.out.println("Memoized Fibonacci result: " + String.valueOf(memoized_fibonacci(memo, test1)));
    estimatedTime = System.currentTimeMillis() - startTime;
    System.out.println("Time elaped: " + String.valueOf(estimatedTime));
  }
}
