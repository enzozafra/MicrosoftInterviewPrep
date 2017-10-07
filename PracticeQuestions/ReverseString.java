import java.lang.*;
import java.io.*;
import java.util.*;

class ReverseString {
  // With a left and right index, it is asymptotically the same as having one index
  // so the difference between the two is constant.
  public static String reverse(String input) {
    char[] temp_array = input.toCharArray();
    int left, right = 0;
    right = temp_array.length - 1;
    for (left = 0; left < right; left++, right--) {
      char temp = temp_array[left];
      temp_array[left] = temp_array[right];
      temp_array[right] = temp;
    }
    return String.valueOf(temp_array);
  }
}
